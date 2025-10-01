public class ExamAlert extends EventNotification {
    public ExamAlert(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.send("Exam Alert: " + message);
    }
}
