package br.com.guimsmendes.unit.usecase;

import br.com.guimsmendes.core.UserUseCase;
import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.domain.User;
import br.com.guimsmendes.dataprovider.repository.UserRepository;
import br.com.guimsmendes.unit.TestSupport;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
public class UserUseCaseTest extends TestSupport {

    @Inject
    UserUseCase userUseCase;

    @InjectMock
    UserRepository userRepository;

    @Test
    void createValidUser() {
        var mockUserRequest = mockUserRequest();
        doNothing().when(userRepository).persist(any(User.class));

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        userUseCase.addUser(mockUserRequest);

        verify(userRepository, timeout(100)).persist(userArgumentCaptor.capture());
        var user = userArgumentCaptor.getValue();

        Assertions.assertEquals(mockUserRequest.getUsername(), user.getUsername());
        Assertions.assertNotEquals(mockUserRequest.getPassword(), user.getPassword());
        Assertions.assertEquals("user", user.getRole());
    }

    @Test
    void listValidUser() {
        var mockUser = mockUser();
        when(userRepository.listAll()).thenReturn(List.of(mockUser));

        var users = userUseCase.listAllUsers();

        Assertions.assertEquals(mockUser.getId(), users.get(0).getId());
        Assertions.assertEquals(mockUser.getRole(), users.get(0).getRole());
        Assertions.assertEquals(mockUser.getUsername(), users.get(0).getUsername());
        Assertions.assertEquals(mockUser.getPassword(), users.get(0).getPassword());
    }
}
