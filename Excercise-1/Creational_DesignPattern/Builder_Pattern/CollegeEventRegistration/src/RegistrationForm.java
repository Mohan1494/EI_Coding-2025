import java.util.List;

public class RegistrationForm {
    private String eventName;
    private int teamSize;
    private double fee;
    private List<String> requiredDocuments;
    private List<String> perks;

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setRequiredDocuments(List<String> requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public void displayInfo() {
        System.out.println("\nEvent Registration Form");
        System.out.println("Event Name: " + eventName);
        System.out.println("Team Size: " + teamSize);
        System.out.println("Fee: " + fee);
        System.out.println("Required Documents: " + requiredDocuments);
        System.out.println("Perks: " + perks);
    }
}
