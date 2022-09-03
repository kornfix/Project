package pl.university.project.odata;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ForecastData {
    private Long id;
    private Timestamp creationTime;
    private String contactType;
    private Integer age;
    private String job;
    private String martialStatus;
    private String educationLevel;
    private Boolean hasDefaultCredit;
    private Boolean hasMortgage;
    private Boolean hasConsumerCredit;
    private Double balance;
    private String callDurationInSeconds;
    private Long numberOfContactsDuringCampaign;
    private Long numberOfContactsDuringPreviousCampaign;
    private Date lastContactDate;
    private Date lastContactDateFromPreviousCampaign;
    private String previousCampaignOutcome;
    private String forecastOutcome;
    private String forecastProbability;
    private String campaignTitle;
    private String clientFullName;
}
