package pl.university.project.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity(name = "clientCampaigns")
@Getter
@Setter
@RequiredArgsConstructor
public class ClientCampaign {

    @EmbeddedId
    private ClientCampaignId clientCampaignId;

    @ManyToOne
    @MapsId("clientId")
    private Client client;
    @ManyToOne
    @MapsId("campaignId")
    private Campaign campaign;
    @Column(nullable = false)
    private Long callDurationInSeconds;
    @Column(nullable = false)
    private Long numberOfContactsDuringCampaign;
    @Column(nullable = false)
    private Date lastContactDate;
    private String campaignOutcome;

    @OneToMany(mappedBy = "clientCampaign", cascade=CascadeType.ALL)
    private Set<Forecast> forecasts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClientCampaign that = (ClientCampaign) o;
        return clientCampaignId != null && Objects.equals(clientCampaignId, that.clientCampaignId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientCampaignId);
    }
}
