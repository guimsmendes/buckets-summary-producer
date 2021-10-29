package br.com.guimsmendes.dataprovider.repository;

import br.com.guimsmendes.core.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
