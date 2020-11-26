package kenkhw.people.jpa;

import org.springframework.ken.jpa.EntityRepository;

import java.util.Optional;

public interface DirectoryRepository extends EntityRepository<Directory> {
    Optional<Directory> findByName(String name);
}
