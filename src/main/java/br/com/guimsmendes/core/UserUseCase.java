package br.com.guimsmendes.core;

import br.com.guimsmendes.core.domain.User;
import br.com.guimsmendes.core.mapper.UserMapper;
import br.com.guimsmendes.dataprovider.repository.UserRepository;
import br.com.guimsmendes.entrypoint.model.UserRequest;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@ApplicationScoped
public class UserUseCase {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Transactional
    public void addUser(UserRequest userRequest) {
        var user = userMapper.toUser(userRequest);
        log.info(String.format("User request received for username: %s. Encrypting password.", user.getUsername()));
        User.encryptInfo(user);
        userRepository.persist(user);
    }

    public List<User> listAllUsers(){
        return userRepository.listAll();
    }
}
