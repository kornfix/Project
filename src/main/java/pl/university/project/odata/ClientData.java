package pl.university.project.odata;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClientData {
    private Long id;

    @NotNull(message = "Imię nie może być puste!")
    @NotBlank(message = "Imię nie może być puste!")
    @Size(max = 50, message = "Imię może się składać tylko z 50 znaków!")
    @Pattern(regexp = "[a-zA-Z]+", message = "Imię może składać się tylko z liter!")
    private String firstName;

    @NotNull(message = "Nazwisko nie może być puste!")
    @NotBlank(message = "Nazwisko nie może być puste!")
    @Size(max = 50, message = "Nazwisko może się składać tylko z 50 znaków!")
    @Pattern(regexp = "[a-zA-Z]+", message = "Nazwisko może składać się tylko z liter!")
    private String lastName;

    @NotNull(message = "Wiek nie może być pusty!")
    @Min(value = 18, message = "Wiek musi wynosić co najmniej 18 lat!")
    private Integer age;

    @NotNull(message = "Numer telefonu nie może być pusty!")
    @NotEmpty(message = "Numer telefonu nie może być pusty!")
    @Pattern(regexp = "([0-9]{3}-[0-9]{3}-[0-9]{3}|[+][1-9]([0-9])?-[0-9]{3}-[0-9]{3}-[0-9]{3})",
            message = "Prawidłowy zapis numeru telefonu to XXX-XXX-XXX lub +XX-XXX-XXX-XXX!")
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

    @NotNull(message = "Informacja o tym, czy zalega ze spłatami, nie może być pusta!")
    private Boolean hasDefaultCredit;
    @NotNull(message = "Informacja o tym, czy posiada kredyt hipoteczny, nie może być pusta!")
    private Boolean hasMortgage;
    @NotNull(message = "Informacja o tym, czy posiada kredyt konsumpcyjny,  nie może być pusta!")
    private Boolean hasConsumerCredit;
    @NotNull(message = "Saldo klienta nie może być puste!")
    private Double balance;

    private Set<ClientCampaignData> campaigns = new HashSet<>();
}
