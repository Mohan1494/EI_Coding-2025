import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LegacyAttendanceSystem legacySystem = new LegacyAttendanceSystem();

        System.out.print("Enter number of students: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ":");
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();

            System.out.print("Is the student present? (true/false): ");
            boolean present = Boolean.parseBoolean(scanner.nextLine());

            legacySystem.addStudent(id, present);
        }

        // Show legacy XML
        String xmlOutput = legacySystem.getAttendanceXML();
        System.out.println("\nLegacy Attendance Format (XML):");
        System.out.println(xmlOutput);

        // Use adapter to get JSON
        AttendanceAdapter adapter = new AttendanceAdapter(legacySystem);
        String jsonOutput = adapter.getFormattedAttendance();
        System.out.println("\nModern Attendance Format (JSON):");
        System.out.println(jsonOutput);

        scanner.close();
    }
}


/*
Example User Input:

Enter number of students: 3
Student 1:
Enter student ID: 2025A001
Is the student present? (true/false): true
Student 2:
Enter student ID: 2025A002
Is the student present? (true/false): false
Student 3:
Enter student ID: 2025A003
Is the student present? (true/false): true
*/
