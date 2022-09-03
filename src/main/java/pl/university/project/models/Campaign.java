package pl.university.project.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "campaigns")
@Getter
@Setter
@RequiredArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private Timestamp creationTime;
    @Column(nullable = false)
    private Date campaignStartDate;
    @Column(nullable = false)
    private Date campaignEndDate;


    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Set<ClientCampaign> clientCampaigns = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Campaign campaign = (Campaign) o;
        return id != null && Objects.equals(id, campaign.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
