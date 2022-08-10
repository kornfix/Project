package pl.university.project.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientCampaignId implements Serializable {

    private Long campaignId;
    private Long clientId;

    public ClientCampaignId() {
    }

    public ClientCampaignId(Long campaignId, Long clientId) {
        this.campaignId = campaignId;
        this.clientId = clientId;
    }
}
