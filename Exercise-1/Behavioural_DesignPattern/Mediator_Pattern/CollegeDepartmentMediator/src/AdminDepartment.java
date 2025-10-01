public class AdminDepartment implements Department {
    private CollegeMediator mediator;

    public AdminDepartment(CollegeMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void sendRequest(String message) {
        mediator.routeRequest("Admin", message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("Admin Department received: " + message);
    }
}
