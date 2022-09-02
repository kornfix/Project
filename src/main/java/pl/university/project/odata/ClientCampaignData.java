package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaignId;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class ClientCampaignData {

    private ClientCampaignId clientCampaignId;
    private ClientData client;
    private CampaignData campaign;
    private String campaignOutcome;

    @NotNull(message = "Czas trwania ostatniego kontaktu nie może być pusty!")
    @NotBlank(message = "Czas trwania ostatniego kontaktu nie może być pusty!")
    @Pattern(regexp = "[1-9]+[0-9]*[hmsHMS][^$]?([1-9]+[0-9]*[msMS][^$]?)?([1-9]+[0-9]*[s|S]?[^$])?",
            message = "Czas trwania ostatniego kontaktu musi mieć format XXs,XXm XXs lub XXh XXm XXs!")
    private String callDurationInSeconds;
    @NotNull
    @Min(value = 0)
    private Long numberOfContactsDuringCampaign;
    private Date lastContactDate;
    private String newestForecast;

}
