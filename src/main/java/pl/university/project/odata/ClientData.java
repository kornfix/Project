package pl.university.project.odata;

import lombok.Data;
import pl.university.project.models.ClientCampaign;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClientData {
    private Long id;

    @NotNull(message = "Imię nie może być puste!")
    @NotEmpty(message = "Imię nie może być puste!")
    @Size(max = 50, message = "Imię może się składac tylko z 50 znaków!")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Imię nie może składac się ze specjalnych znaków")
    private String firstName;

    @NotNull(message = "Nazwisko nie może być puste!")
    @NotEmpty(message = "Nazwisko nie może być puste!")
    @Size(max = 50, message = "Nazwisko może się składac tylko z 50 znaków!")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Nazwisko nie może składac się ze specjalnych znaków")
    private String lastName;

    @NotNull(message = "Wiek nie może być pusty!")
    @Min(value = 18, message = "Wiek musi wynosić co najmniej 18 lat")
    private Integer age;

    @NotNull(message = "Numer telefonu nie może być pusty!")
    @NotEmpty(message = "Numer telefonu nie może być pusty!")
    @Pattern(regexp = "([0-9]{3}-[0-9]{3}-[0-9]{3}|[+][1-9]([0-9])?-[0-9]{3}-[0-9]{3}-[0-9]{3})",
            message = "Prawidłowy zapis numeru telefonu to XXX-XXX-XXX lub +XX-XXX-XXX-XXX")
    private String phoneNumber;

    @NotNull(message = "Zawód nie może być pusty!")
    @NotEmpty(message = "Zawód nie może być pusty!")
    private String contactType;

    @NotNull(message = "Zawód nie może być pusty!")
    @NotEmpty(message = "Zawód nie może być pusty!")
    private String job;

    @NotNull(message = "Stan cywilny nie może być pusty!")
    @NotEmpty(message = "Stan cywilny nie może być pusty!")
    private String martialStatus;

    @NotNull(message = "Poziom wykształcenia nie może być pusty!")
    @NotEmpty(message = "Poziom wykształcenia nie może być pusty!")
    private String educationLevel;

    private Boolean defaultCreditStatus;
    private Boolean hasMortgage;
    private Boolean hasConsumerCredit;
    private Double balance;

    private Set<ClientCampaign> campaigns = new HashSet<>();
}
