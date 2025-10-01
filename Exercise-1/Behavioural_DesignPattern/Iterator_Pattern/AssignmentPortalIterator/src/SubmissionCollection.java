import java.util.List;

public class SubmissionCollection implements Aggregate<StudentSubmission> {
    private List<StudentSubmission> submissions;

    public SubmissionCollection(List<StudentSubmission> submissions) {
        this.submissions = submissions;
    }

    @Override
    public Iterator<StudentSubmission> createIterator() {
        return new SubmissionIterator(submissions);
    }
}
