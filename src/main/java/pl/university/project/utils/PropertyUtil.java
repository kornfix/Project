package pl.university.project.utils;

import pl.university.project.models.Campaign;
import pl.university.project.odata.ClientData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PropertyUtil {


    private static final String DIVIDER = ",";

    private PropertyUtil() {
    }

    public static List<String> splitProperty(String value) {
        return splitProperty(value, DIVIDER);
    }

    public static List<String> splitProperty(String value, String divider) {
        return List.of(value.split(divider));
    }

    public static List<String> getJobCategories() {
        List<String> jobs = new ArrayList<>();
        jobs.add("Pracownik na stanowisku kierowniczym");
        jobs.add("Pracownik fizyczny");
        jobs.add("Technik");
        jobs.add("Pracownik administracyjny");
        jobs.add("Pracownik w sektorze usług");
        jobs.add("Emeryt");
        jobs.add("Samozatrudniony");
        jobs.add("Student");
        jobs.add("Bezrobotny");
        jobs.add("Przedsiębiorca");
        jobs.add("Pokojówka");
        jobs.add("Nie wiadomo");
        return jobs;
    }

    public static List<String> getMartialStatusCategories() {
        List<String> martialStatuses = new ArrayList<>();
        martialStatuses.add("Zamężny");
        martialStatuses.add("Niezamężny");
        martialStatuses.add("Rozwiedziony");
        return martialStatuses;
    }

    public static List<String> getEducationLevelCategories() {
        List<String> educationLevels = new ArrayList<>();
        educationLevels.add("Wykształcenie wyższe");
        educationLevels.add("Wykształcenie średnie");
        educationLevels.add("Wykształcenie podstawowe");
        educationLevels.add("Nie wiadomo");
        return educationLevels;
    }

    public static List<String> getContactTypeCategories() {
        List<String> contactTypes = new ArrayList<>();
        contactTypes.add("Komórkowy");
        contactTypes.add("Telefoniczny");
        contactTypes.add("Nie wiadomo");
        return contactTypes;
    }

    public static boolean validateClient(ClientData clientData) {
        return clientData != null && clientData.getId() != null
                && clientData.getFirstName() != null && clientData.getLastName()!=null;
    }

    public static boolean clientNotWithIDs(ClientData clientData, Collection<Long> exceptIDs) {
        return exceptIDs.stream().allMatch( id -> clientNotWithID(clientData,id));
    }

    public static boolean clientNotWithID(ClientData clientData, Long exceptIDs) {
        return !clientData.getId().equals(exceptIDs);
    }



    public static boolean validateCampaign(Campaign campaign) {
        return campaign != null && campaign.getId() != null
                && campaign.getTitle() != null && campaign.getCreationTime()!=null;
    }

    public static boolean validateOldCampaign(Campaign comparedCampaign, Campaign thisCampaign) {
        return validateCampaign(comparedCampaign) &&
                comparedCampaign.getCampaignEndDate().before(thisCampaign.getCampaignEndDate());
    }

}
