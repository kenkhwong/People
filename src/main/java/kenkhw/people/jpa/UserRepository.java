package kenkhw.people.jpa;

import org.springframework.ken.jpa.EntityRepository;

import javax.persistence.UniqueConstraint;
import java.util.Optional;

public interface UserRepository extends EntityRepository<User> {
    Optional<User> findByPrincipalAndDirectory(String principal, Directory directory);
}
