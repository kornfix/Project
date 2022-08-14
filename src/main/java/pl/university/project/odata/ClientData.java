package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaign;

import java.util.HashSet;
import java.util.Set;

@Data
public class ClientData {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer age;
    private String job;
    private String martialStatus;
    private String education;
    private Boolean defaultCreditStatus;
    private Boolean hasMortgage;
    private Boolean hasConsumerCredit;
    private Double balance;

    private Set<ClientCampaign> campaigns = new HashSet<>();
}
