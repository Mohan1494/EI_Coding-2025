public class ExperimentClient {
    private LabExperiment prototype;

    public ExperimentClient(LabExperiment prototype) {
        this.prototype = prototype;
    }

    public LabExperiment createExperiment() {
        return prototype.clone();
    }
}
