import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DepartmentMediator mediator = new DepartmentMediator();

        AdminDepartment admin = new AdminDepartment(mediator);
        LibraryDepartment library = new LibraryDepartment(mediator);

        mediator.registerAdmin(admin);
        mediator.registerLibrary(library);

        System.out.println("=== Department Communication ===");

        // Admin request
        System.out.print("Admin, enter your request to Library: ");
        String adminRequest = scanner.nextLine();
        admin.sendRequest(adminRequest);

        // Library response
        System.out.print("Library, enter your response to Admin: ");
        String libraryResponse = scanner.nextLine();
        library.sendRequest(libraryResponse);

        scanner.close();
    }
}
