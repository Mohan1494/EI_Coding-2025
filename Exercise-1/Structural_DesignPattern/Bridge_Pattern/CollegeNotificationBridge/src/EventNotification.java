public abstract class EventNotification {
    protected NotificationSender sender;

    public EventNotification(NotificationSender sender) {
        this.sender = sender;
    }

    public abstract void notifyUser(String message);
}
