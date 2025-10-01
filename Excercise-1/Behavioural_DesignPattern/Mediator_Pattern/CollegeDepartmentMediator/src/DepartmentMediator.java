public class DepartmentMediator implements CollegeMediator {
    private AdminDepartment admin;
    private LibraryDepartment library;

    public void registerAdmin(AdminDepartment admin) {
        this.admin = admin;
    }

    public void registerLibrary(LibraryDepartment library) {
        this.library = library;
    }

    @Override
    public void routeRequest(String sender, String message) {
        if (sender.equals("Admin")) {
            System.out.println("Mediator routing Admin's request to Library...");
            library.receiveMessage("Clearance requested: " + message);
        } else if (sender.equals("Library")) {
            System.out.println("Mediator routing Library's response to Admin...");
            admin.receiveMessage("Clearance status: " + message);
        }
    }
}
