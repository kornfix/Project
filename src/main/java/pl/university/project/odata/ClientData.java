package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaign;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClientData {
    private Long id;
    @NotNull(message = "Imię nie może być puste!")
    @NotEmpty(message = "Imię nie może być puste!")
    @Size(max = 50, message = "Imię może się składac tylko z 50 znaków!")
    private String firstName;
    @NotNull(message = "Nazwisko nie może być puste!")
    @NotEmpty(message = "Nazwisko nie może być puste!")
    @Size(max = 50, message = "Nazwisko może się składac tylko z 50 znaków!")
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
