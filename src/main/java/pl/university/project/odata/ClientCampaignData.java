package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaignId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class ClientCampaignData {

    private ClientCampaignId clientCampaignId;
    private ClientData client;
    private CampaignData campaign;

    @NotNull
    @Min(value = 0)
    private Long callDurationInSeconds;
    @NotNull
    @Min(value = 0)
    private Long numberOfContactsDuringCampaign;
    private Date lastContactDate;

}
