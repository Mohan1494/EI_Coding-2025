import java.util.List;

public interface Builder {
    void buildEventName(String name);
    void buildTeamSize(int size);
    void buildFee(double fee);
    void buildRequiredDocuments(List<String> docs);
    void buildPerks(List<String> perks);
    RegistrationForm getResult();
}
