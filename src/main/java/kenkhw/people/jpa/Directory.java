package kenkhw.people.jpa;

import org.springframework.ken.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Directory extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    public Directory() {}

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
