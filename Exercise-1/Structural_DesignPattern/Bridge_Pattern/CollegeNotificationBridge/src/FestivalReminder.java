public class FestivalReminder extends EventNotification {
    public FestivalReminder(NotificationSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String message) {
        sender.send("Festival Reminder: " + message);
    }
}
