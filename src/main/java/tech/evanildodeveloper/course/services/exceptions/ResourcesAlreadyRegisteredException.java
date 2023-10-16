package tech.evanildodeveloper.course.services.exceptions;

public class ResourcesAlreadyRegisteredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourcesAlreadyRegisteredException() {
        super("Email already registered");
    }

}
