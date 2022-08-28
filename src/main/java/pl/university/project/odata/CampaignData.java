package pl.university.project.odata;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
public class CampaignData {
    private Long id;

    @NotNull(message = "Tytuł kamapanii nie może być pusty!")
    @NotBlank(message = "Tytuł kamapanii nie może być pusty!")
    @Size(max = 50, message = "Tytuł kamapanii może się składać tylko z 50 znaków!")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Tytuł kamapanii może składać się tylko z liter oraz cyfr!")
    private String title;
    @Size(max = 2000, message = "Opiz może się składać tylko z 2000 znaków!")
    private String description;
    private Timestamp creationTime;
    private Date campaignStartDate;
    private Date campaignEndDate;
    private String forecast;

    private Set<ClientCampaignData> campaigns = new HashSet<>();
}
