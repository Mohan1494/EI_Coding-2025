import java.util.List;
import java.util.NoSuchElementException;

public class SubmissionIterator implements Iterator<StudentSubmission> {
    private int currentIndex = 0;
    private List<StudentSubmission> submissions;

    public SubmissionIterator(List<StudentSubmission> submissions) {
        this.submissions = submissions;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < submissions.size()) {
            if (!submissions.get(currentIndex).isGraded()) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public StudentSubmission next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return submissions.get(currentIndex++);
    }
}
