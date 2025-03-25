package dev.jonkursani.restapigr2.schedulers;

import dev.jonkursani.restapigr2.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//        ┌───────────── second (0-59)
//        │ ┌───────────── minute (0 - 59)
//        │ │ ┌───────────── hour (0 - 23)
//        │ │ │ ┌───────────── day of the month (1 - 31)
//        │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
//        │ │ │ │ │ ┌───────────── day of the week (0 - 7)
//        │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
//        │ │ │ │ │ │
//        * * * * * *

// https://docs.spring.io/spring-framework/reference/integration/scheduling.html#scheduling-cron-expression
// https://crontab.guru/

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeReportScheduler {
    private final EmployeeRepository repository;

    // get employee report
    // every monday 8 AM
//    @Scheduled(cron = "0 0 8 * * MON")
    // every 10 second
//    @Scheduled(cron = "*/10 * * * * *")
//    @Scheduled(fixedRate = 10000)
    public void getEmployeeReport() {
        log.info("Getting employee report...");
        var employees = repository.findAll();
        log.info("Total employees: {}", employees.size());
    }

    // every 8 march 12 PM
//    @Scheduled(cron = "0 0 12 8 3 *")
//    @Scheduled(fixedRate = 10000)
    public void happyWomenDay() {
        var employees = repository.findAll();

        for (var employee : employees) {
//            if (employee.getGender() == 'F') {
                log.info("Happy Womans Day {}!", employee.getFirstName());
//            }
        }
    }

    // new hire report
    // every friday 9 AM
//    @Scheduled(cron = "0 0 9 * * FRI")
//    @Scheduled(fixedRate = 5000)
    @Transactional
    public void newHireReport() {
        var oneMonthAgo = LocalDate.now().minusMonths(1);

        var employees = repository.findAll()
                .stream()
                .filter(e -> e.getHireDate() != null && e.getHireDate().isAfter(oneMonthAgo))
                .toList();

        employees.forEach(e -> log.info("Employee {} {} | " +
                "Hire date {} | " +
                "Department {}", e.getFirstName(), e.getLastName(), e.getHireDate(),
                e.getDepartment() != null ? e.getDepartment().getName() : "N/A"));
    }
}