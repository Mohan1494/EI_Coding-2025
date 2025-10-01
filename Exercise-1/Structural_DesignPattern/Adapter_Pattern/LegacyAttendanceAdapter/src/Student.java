public class Student {
    private String id;
    private boolean present;

    public Student(String id, boolean present) {
        this.id = id;
        this.present = present;
    }

    public String getId() {
        return id;
    }

    public boolean isPresent() {
        return present;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "{ \"id\": \"" + id + "\", \"status\": \"" + present + "\" }";
    }
}
