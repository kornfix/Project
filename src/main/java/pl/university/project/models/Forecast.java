package pl.university.project.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "forecasts")
@Getter
@Setter
@RequiredArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Timestamp creationTime;
    @Column(nullable = false)
    private String contactType;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String job;
    @Column(nullable = false)
    private String martialStatus;
    @Column(nullable = false)
    private String educationLevel;
    @Column(nullable = false)
    private Boolean hasDefaultCredit;
    @Column(nullable = false)
    private Boolean hasMortgage;
    @Column(nullable = false)
    private Boolean hasConsumerCredit;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private Long callDurationInSeconds;
    @Column(nullable = false)
    private Long numberOfContactsDuringCampaign;
    private Long numberOfContactsDuringPreviousCampaign;
    @Column(nullable = false)
    private Date lastContactDate;
    private Date lastContactDateFromPreviousCampaign;
    private String previousCampaignOutcome;
    @Column(nullable = false)
    private String forecastOutcome;
    @Column(nullable = false)
    private Double forecastProbability;

    @ManyToOne
    private ClientCampaign clientCampaign;
}
