import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TechFestBuilder builder = new TechFestBuilder();
        RegistrationDirector director = new RegistrationDirector();

        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter Team Size: ");
        int teamSize = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Fee: ");
        double fee = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter number of required documents: ");
        int docCount = Integer.parseInt(scanner.nextLine());
        List<String> documents = new ArrayList<>();
        for (int i = 0; i < docCount; i++) {
            System.out.print("Document " + (i + 1) + ": ");
            documents.add(scanner.nextLine());
        }

        System.out.print("Enter number of perks: ");
        int perkCount = Integer.parseInt(scanner.nextLine());
        List<String> perks = new ArrayList<>();
        for (int i = 0; i < perkCount; i++) {
            System.out.print("Perk " + (i + 1) + ": ");
            perks.add(scanner.nextLine());
        }

        // Construct the form with user input
        director.constructTechFestForm(builder, eventName, teamSize, fee, documents, perks);

        RegistrationForm form = builder.getResult();
        form.displayInfo();

        scanner.close();
    }
}

/*
Example Input:

Enter Event Name: Tech Fest
Enter Team Size: 5
Enter Fee: 500
Enter number of required documents: 2
Document 1: College ID
Document 2: Photo
Enter number of perks: 2
Perk 1: T-shirt
Perk 2: Lunch Coupon
*/
