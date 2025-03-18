package dev.jonkursani.restapigr2.exceptions.employee;

public class EmailAlreadyExistsException extends IllegalArgumentException {
    public EmailAlreadyExistsException(String email) {
        super("Employee with email " + email + " already exists.");
    }
}
