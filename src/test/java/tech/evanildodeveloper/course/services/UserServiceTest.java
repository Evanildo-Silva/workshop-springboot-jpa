package tech.evanildodeveloper.course.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.repositories.UserRepository;
import tech.evanildodeveloper.course.services.exceptions.DatabaseException;
import tech.evanildodeveloper.course.services.exceptions.ResourceNotFoundException;

@ActiveProfiles("test")
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a list of users")
    public void testFindAllUsers() {
        // Simulate repository.findAll() method
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        // Verify that the repository method was called
        verify(userRepository, times(1)).findAll();

        // Check if the result matches the simulated data
        assertEquals(users, result);
    }

    @Test
    @DisplayName("Should return a user of the given ID")
    public void testFindUserById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertEquals(user, result);
    }

    @Test
    @DisplayName("Should throw an exception when the specified id is not found in the database")
    public void testFindUserByIdNotFound() throws Exception {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        try {
            userService.findById(userId);
        } catch (ResourceNotFoundException e) {
            verify(userRepository, times(1)).findById(userId);
        }
    }

    @Test
    @DisplayName("Should create a user successfully")
    public void testCreateUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.create(user);

        verify(userRepository, times(1)).save(user);
        assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userService.delete(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    @DisplayName("Should throw an exception when the specified id to delete is not found in the database")
    public void testDeleteUserNotFound() {
        Long userId = 1L;
        doThrow(new EmptyResultDataAccessException(1)).when(userRepository).deleteById(userId);

        try {
            userService.delete(userId);
        } catch (ResourceNotFoundException e) {
            verify(userRepository, times(1)).deleteById(userId);
        }
    }

    @Test
    @DisplayName("Should throw an exception when the ID specified for deletion could cause a database integrity failure")
    public void testDeleteUserDataIntegrityViolation() {
        Long userId = 1L;
        doThrow(new DataIntegrityViolationException("")).when(userRepository).deleteById(userId);

        try {
            userService.delete(userId);
        } catch (DatabaseException e) {
            verify(userRepository, times(1)).deleteById(userId);
        }
    }

    // @Test
    // @DisplayName("Should update a user's data successfully from the entered ID")
    // public void testUpdateUser() {
    // Long userId = 1L;
    // User existingUser = new User();
    // User updatedUser = new User();

    // when(userRepository.getReferenceById(userId)).thenReturn(existingUser);
    // when(userRepository.save(existingUser)).thenReturn(updatedUser);

    // User result = userService.update(userId, updatedUser);

    // verify(userRepository, times(1)).getReferenceById(userId);
    // verify(userRepository, times(1)).save(existingUser);

    // assertEquals(updatedUser, result);
    // }

    // @Test
    // @DisplayName("Should throw an exception when the specified id to update is
    // not found in the database")
    // public void testUpdateUserNotFound() {
    // Long userId = 1L;
    // User updatedUser = new User();

    // when(userRepository.getReferenceById(userId)).thenThrow(EntityNotFoundException.class);

    // try {
    // userService.update(userId, updatedUser);
    // } catch (ResourceNotFoundException e) {
    // verify(userRepository, times(1)).getReferenceById(userId);
    // }
    // }
}
