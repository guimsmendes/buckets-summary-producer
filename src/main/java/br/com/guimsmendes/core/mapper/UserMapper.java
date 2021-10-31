package br.com.guimsmendes.core.mapper;

import br.com.guimsmendes.core.domain.User;
import br.com.guimsmendes.entrypoint.model.UserRequest;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "cdi", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, imports = {ObjectId.class, LocalDateTime.class})
public interface UserMapper {

    User toUser(UserRequest userRequest);
}
