package org.medTechSolutions.platform.profiles_service.User.Domain.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("Entity with id " + userId + " not found");
    }
}