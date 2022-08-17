package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaign;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
public class CampaignData {
    private Long id;
    private String title;
    private String description;
    private Timestamp creationTime;
    private Date campaignStartDate;
    private Date campaignEndDate;

    private Set<ClientCampaign> campaigns = new HashSet<>();
}
