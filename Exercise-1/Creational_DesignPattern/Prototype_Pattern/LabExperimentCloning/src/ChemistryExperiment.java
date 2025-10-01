import java.util.List;

public class ChemistryExperiment implements LabExperiment {
    private String title;
    private String objective;
    private List<String> materials;
    private List<String> procedures;

    public ChemistryExperiment(String title, String objective,
                               List<String> materials, List<String> procedures) {
        this.title = title;
        this.objective = objective;
        this.materials = materials;
        this.procedures = procedures;
    }

    @Override
    public LabExperiment clone() {
        return new ChemistryExperiment(title, objective,
                                       List.copyOf(materials),
                                       List.copyOf(procedures));
    }

    @Override
    public void display() {
        System.out.println("Experiment Title: " + title);
        System.out.println("Objective: " + objective);
        System.out.println("Materials: " + materials);
        System.out.println("Procedures: " + procedures);
    }
}
