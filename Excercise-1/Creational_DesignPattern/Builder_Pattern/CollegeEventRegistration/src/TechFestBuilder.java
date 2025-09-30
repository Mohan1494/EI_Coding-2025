import java.util.List;

public class TechFestBuilder implements Builder {
    private RegistrationForm form = new RegistrationForm();

    @Override
    public void buildEventName(String name) {
        form.setEventName(name);
    }

    @Override
    public void buildTeamSize(int size) {
        form.setTeamSize(size);
    }

    @Override
    public void buildFee(double fee) {
        form.setFee(fee);
    }

    @Override
    public void buildRequiredDocuments(List<String> docs) {
        form.setRequiredDocuments(docs);
    }

    @Override
    public void buildPerks(List<String> perks) {
        form.setPerks(perks);
    }

    @Override
    public RegistrationForm getResult() {
        return form;
    }
}
