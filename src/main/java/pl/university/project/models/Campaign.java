package pl.university.project.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "campaigns")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue
    private Long id;
    private Date creationTime;
    private Date campaignStartDate;
    private Date campaignEndDate;

    @OneToMany(mappedBy = "campaign")
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
