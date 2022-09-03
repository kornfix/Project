package pl.university.project.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "clients")
@Getter
@Setter
@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String contactType;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String job;
    @Column(nullable = false)
    private String martialStatus;
    @Column(nullable = false)
    private String educationLevel;
    @Column(nullable = false)
    private Boolean hasDefaultCredit;
    @Column(nullable = false)
    private Boolean hasMortgage;
    @Column(nullable = false)
    private Boolean hasConsumerCredit;
    @Column(nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<ClientCampaign> clientCampaigns = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
