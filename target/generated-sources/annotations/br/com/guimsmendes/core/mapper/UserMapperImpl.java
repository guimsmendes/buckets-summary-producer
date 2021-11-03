package br.com.guimsmendes.core.mapper;

import br.com.guimsmendes.core.domain.User;
import br.com.guimsmendes.entrypoint.model.UserRequest;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-03T17:23:35-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (GraalVM Community)"
)
@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest userRequest) {

        User user = new User();

        if ( userRequest != null ) {
            user.setUsername( userRequest.getUsername() );
            user.setPassword( userRequest.getPassword() );
        }

        return user;
    }
}
