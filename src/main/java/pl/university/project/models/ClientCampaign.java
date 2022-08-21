package pl.university.project.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "clientCampaigns")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ClientCampaign {

    @EmbeddedId
    private ClientCampaignId campaignParticipantId = new ClientCampaignId();

    @ManyToOne
    @MapsId("clientId")
    private Client client;
    @ManyToOne
    @MapsId("campaignId")
    private Campaign campaign;

    private Long callDurationInSeconds;

    private Long numberOfContactsDuringCampaign;

    private Date lastContactDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClientCampaign that = (ClientCampaign) o;
        return campaignParticipantId != null && Objects.equals(campaignParticipantId, that.campaignParticipantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignParticipantId);
    }
}
