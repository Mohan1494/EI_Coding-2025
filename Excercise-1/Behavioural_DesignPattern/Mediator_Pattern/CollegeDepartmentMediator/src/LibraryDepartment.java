public class LibraryDepartment implements Department {
    private CollegeMediator mediator;

    public LibraryDepartment(CollegeMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void sendRequest(String message) {
        mediator.routeRequest("Library", message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("Library Department received: " + message);
    }
}
