package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaignId;

@Data
public class ClientCampaignData {

    private ClientCampaignId clientCampaignId;

    private ClientData client;
    private CampaignData campaign;
    private CampaignData oldCamping;
    private Integer numberOfCalls;
    private String communicationForm;
    private Long callDurationInSeconds;
    private Integer numberOfContacts;
    private Integer lastContactDayOfYear;

}
