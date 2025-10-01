import java.util.List;

public class RegistrationDirector {
    // Allows director to construct the form using user input
    public void constructTechFestForm(Builder builder, String eventName, int teamSize,
                                      double fee, List<String> documents, List<String> perks) {
        builder.buildEventName(eventName);
        builder.buildTeamSize(teamSize);
        builder.buildFee(fee);
        builder.buildRequiredDocuments(documents);
        builder.buildPerks(perks);
    }
}
