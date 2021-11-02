package br.com.guimsmendes.unit.usecase.mapper;

import br.com.guimsmendes.core.mapper.UserMapper;
import br.com.guimsmendes.core.mapper.UserMapperImpl;
import br.com.guimsmendes.unit.TestSupport;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class UserMapperTest extends TestSupport {

    @InjectMock
    UserMapper userMapper;

    @BeforeEach
    public void init() throws IOException {
        userMapper = new UserMapperImpl();
    }

    @Test
    void toUserValidTest() {
        var userRequest = mockUserRequest();
        var user = userMapper.toUser(userRequest);

        assertEquals(userRequest.getUsername(), user.getUsername());
        assertEquals(userRequest.getPassword(), user.getPassword());
    }

    @Test
    void toUserNull() { assertNotNull(userMapper.toUser(null)); }
}
