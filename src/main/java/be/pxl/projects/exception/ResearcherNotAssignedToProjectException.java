package be.pxl.projects.exception;

public class ResearcherNotAssignedToProjectException extends RuntimeException {
    public ResearcherNotAssignedToProjectException(String message) {
        super(message);
    }
}
