package br.com.guimsmendes.core.domain;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Data;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RegisterForReflection
@UserDefinition
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Username
    private String username;

    @Password
    private String password;

    @Roles
    private String role;

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    public static void encryptInfo(User user) {
        user.password = BcryptUtil.bcryptHash(user.password);
        user.role = validateUsername(user.username);
    }


    private static String validateUsername(String username) {
        if (username.equals("guimsmendes")) {
            return "admin";
        }
        return "user";
    }
}
