package be.pxl.projects.exception;

public class ResearcherAlreadyAssignedToProjectException extends RuntimeException {
    public ResearcherAlreadyAssignedToProjectException(String message) {
        super(message);
    }
}
