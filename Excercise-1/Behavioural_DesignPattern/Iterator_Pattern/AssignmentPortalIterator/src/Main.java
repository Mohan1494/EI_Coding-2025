import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<StudentSubmission> submissions = new ArrayList<>();

        System.out.print("Enter number of submissions: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Submission " + (i + 1) + ":");
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter file type (PDF/DOCX/ZIP/etc.): ");
            String fileType = scanner.nextLine();

            System.out.print("Is it graded? (true/false): ");
            boolean graded = Boolean.parseBoolean(scanner.nextLine());

            submissions.add(new StudentSubmission(name, fileType, graded));
        }

        SubmissionCollection collection = new SubmissionCollection(submissions);
        Iterator<StudentSubmission> iterator = collection.createIterator();

        System.out.println("\nUngraded Submissions:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getDetails());
        }

        scanner.close();
    }
}
