import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Create Chemistry Experiment Prototype ===");

        System.out.print("Enter experiment name: ");
        String name = scanner.nextLine();

        System.out.print("Enter experiment objective: ");
        String objective = scanner.nextLine();

        System.out.print("Enter number of materials: ");
        int numMaterials = Integer.parseInt(scanner.nextLine());
        List<String> materials = new ArrayList<>();
        for (int i = 0; i < numMaterials; i++) {
            System.out.print("Material " + (i + 1) + ": ");
            materials.add(scanner.nextLine());
        }

        System.out.print("Enter number of steps: ");
        int numSteps = Integer.parseInt(scanner.nextLine());
        List<String> steps = new ArrayList<>();
        for (int i = 0; i < numSteps; i++) {
            System.out.print("Step " + (i + 1) + ": ");
            steps.add(scanner.nextLine());
        }

        // Original prototype
        LabExperiment prototype = new ChemistryExperiment(name, objective, materials, steps);

        ExperimentClient client = new ExperimentClient(prototype);

        // Clone for Batch A
        System.out.println("\n--- Batch A Experiment ---");
        LabExperiment batchA = client.createExperiment();
        batchA.display();

        // Clone for Batch B
        System.out.println("\n--- Batch B Experiment ---");
        LabExperiment batchB = client.createExperiment();
        batchB.display();

        scanner.close();
    }
}

/*
Example User Input (for easy testing):

Enter experiment name: Acid-Base Titration
Enter experiment objective: To determine the concentration of an unknown acid using NaOH.
Enter number of materials: 5
Material 1: Burette
Material 2: Pipette
Material 3: Indicator
Material 4: NaOH
Material 5: Unknown Acid
Enter number of steps: 3
Step 1: Fill burette with NaOH
Step 2: Add indicator to acid
Step 3: Titrate until color changes
*/
