package pl.university.project.utils;

import pl.university.project.models.Campaign;
import pl.university.project.odata.ClientData;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static List<String> getOutcomesCategories() {
        List<String> outcomes = new ArrayList<>();
        outcomes.add("Nie wiadomo");
        outcomes.add("Porażka");
        outcomes.add("Sukces");
        outcomes.add("Inny");
        return outcomes;
    }

    public static boolean validateClient(ClientData clientData) {
        return clientData != null && clientData.getId() != null
                && clientData.getFirstName() != null && clientData.getLastName() != null;
    }

    public static boolean clientNotWithIDs(ClientData clientData, Collection<Long> exceptIDs) {
        return exceptIDs.stream().allMatch(id -> clientNotWithID(clientData, id));
    }

    public static boolean clientNotWithID(ClientData clientData, Long exceptIDs) {
        return !clientData.getId().equals(exceptIDs);
    }


    public static boolean validateCampaign(Campaign campaign) {
        return campaign != null && campaign.getId() != null
                && campaign.getTitle() != null && campaign.getCreationTime() != null;
    }

    public static boolean validateOldCampaign(Campaign comparedCampaign, Campaign thisCampaign) {
        return validateCampaign(comparedCampaign) &&
                comparedCampaign.getCampaignEndDate().before(thisCampaign.getCampaignEndDate());
    }


    private static final Pattern periodPattern = Pattern.compile("([0-9]+)([dhms])");

    public static Long parsePeriod(String period) {
        if (period == null) return null;
        period = period.toLowerCase(Locale.ENGLISH);
        Matcher matcher = periodPattern.matcher(period);
        Instant instant = Instant.EPOCH;
        while (matcher.find()) {
            int num = Integer.parseInt(matcher.group(1));
            String typ = matcher.group(2);
            switch (typ) {
                case "d":
                    instant = instant.plus(Duration.ofDays(num));
                    break;
                case "h":
                    instant = instant.plus(Duration.ofHours(num));
                    break;
                case "m":
                    instant = instant.plus(Duration.ofMinutes(num));
                    break;
                case "s":
                    instant = instant.plus(Duration.ofSeconds(num));
                    break;
            }
        }
        return instant.toEpochMilli() / 1000L;
    }

    public static String getDurationStringFormat(Long allSeconds) {
        String result = "";
        Long hours = allSeconds / 3600;
        if (hours > 0) {
            result += String.format("%dh ", hours);
        }
        Long minutes = (allSeconds % 3600) / 60;
        if (minutes > 0) {
            result += String.format("%dm ", minutes);
        }
        Long seconds = allSeconds % 60;
        if (seconds > 0) {
            result += String.format("%ds", seconds);
        }
        return result;
    }
}
