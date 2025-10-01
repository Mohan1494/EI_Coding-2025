public class StudentSubmission {
    private String studentName;
    private String fileType;
    private boolean isGraded;

    public StudentSubmission(String studentName, String fileType, boolean isGraded) {
        this.studentName = studentName;
        this.fileType = fileType;
        this.isGraded = isGraded;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public String getDetails() {
        return studentName + " submitted a " + fileType + " file. Graded: " + isGraded;
    }
}
