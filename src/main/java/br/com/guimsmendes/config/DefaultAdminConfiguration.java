package br.com.guimsmendes.config;

import br.com.guimsmendes.core.UserUseCase;
import br.com.guimsmendes.entrypoint.model.UserRequest;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class DefaultAdminConfiguration {

    @Inject
    UserUseCase userUseCase;

    @ConfigProperty(name = "default.username")
    String defaultUsername;

    @ConfigProperty(name = "default.password")
    String defaultPassword;

    public void onStart(@Observes StartupEvent startupEvent) {
        var userRequest = new UserRequest();
        userRequest.setUsername(defaultUsername);
        userRequest.setPassword(defaultPassword);
        userUseCase.addUser(userRequest);
    }
}
