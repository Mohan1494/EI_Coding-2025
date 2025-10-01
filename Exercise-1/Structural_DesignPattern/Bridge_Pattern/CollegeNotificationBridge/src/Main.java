import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Exam Alert message: ");
        String examMessage = scanner.nextLine();
        System.out.print("Send via (email/sms): ");
        String examChannel = scanner.nextLine().toLowerCase();

        EventNotification examNotification;
        if (examChannel.equals("sms")) {
            examNotification = new ExamAlert(new SMSSender());
        } else {
            examNotification = new ExamAlert(new EmailSender());
        }

        System.out.print("Enter Festival Reminder message: ");
        String festivalMessage = scanner.nextLine();
        System.out.print("Send via (email/sms): ");
        String festivalChannel = scanner.nextLine().toLowerCase();

        EventNotification festivalNotification;
        if (festivalChannel.equals("sms")) {
            festivalNotification = new FestivalReminder(new SMSSender());
        } else {
            festivalNotification = new FestivalReminder(new EmailSender());
        }

        System.out.println("\n=== Sending Notifications ===");
        examNotification.notifyUser(examMessage);
        festivalNotification.notifyUser(festivalMessage);

        scanner.close();
    }
}
