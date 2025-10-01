public class AttendanceAdapter implements Attendance {
    private LegacyAttendanceSystem legacySystem;

    public AttendanceAdapter(LegacyAttendanceSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public String getFormattedAttendance() {
        // Convert legacy student list (XML data) into JSON format
        StringBuilder json = new StringBuilder("{ \"attendance\": [");
        for (Student s : legacySystem.getStudents()) {
            json.append("{ \"id\": \"")
                .append(s.getId())
                .append("\", \"status\": \"")
                .append(s.isPresent())
                .append("\" },");
        }
        if (!legacySystem.getStudents().isEmpty()) {
            json.deleteCharAt(json.length() - 1); 
        }
        json.append("] }");
        return json.toString();
    }
}
