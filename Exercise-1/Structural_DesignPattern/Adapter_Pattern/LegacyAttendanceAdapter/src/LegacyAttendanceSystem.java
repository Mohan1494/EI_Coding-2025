import java.util.ArrayList;
import java.util.List;

public class LegacyAttendanceSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String id, boolean present) {
        students.add(new Student(id, present));
    }

    // Legacy XML output
    public String getAttendanceXML() {
        StringBuilder xml = new StringBuilder("<attendance>");
        for (Student s : students) {
            xml.append("<student id='")
               .append(s.getId())
               .append("'>")
               .append(s.isPresent() ? "Present" : "Absent")
               .append("</student>");
        }
        xml.append("</attendance>");
        return xml.toString();
    }

    public List<Student> getStudents() {
        return students;
    }
}
