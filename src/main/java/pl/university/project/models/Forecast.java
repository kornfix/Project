package pl.university.project.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "forecasts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp creationTime;
    private String contactType;
    private Integer age;
    private String job;
    private String martialStatus;
    private String educationLevel;
    private Boolean defaultCreditStatus;
    private Boolean hasMortgage;
    private Boolean hasConsumerCredit;
    private Double balance;
    private Long callDurationInSeconds;
    private Long numberOfContactsDuringCampaign;
    private Long numberOfContactsDuringPreviousCampaign;
    private Date lastContactDate;
    private Date lastContactDateFromPreviousCampaign;
    private String previousCampaignOutcome;
    private String forecastOutcome;
    private Double forecastProbability;

    @ManyToOne
    private ClientCampaign clientCampaign;
}
