package kenkhw.people.jpa;

import org.springframework.ken.jpa.BaseEntity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"principal","directory_id"}))
public class User extends BaseEntity {

    @Column(nullable = false)
    private String principal;
    @Column(nullable = false)
    private String credential;

    @ManyToOne(optional = false)
    private Directory directory;

    public User() {}

    public User(String principal, String credential, Directory directory) {
        this.principal = principal;
        this.credential = credential;
        this.directory = directory;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
