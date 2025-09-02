import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    public static void displayMenu() {
        System.out.println("\nWelcome to the Student Grade Management System!");
        System.out.println("[1]Set up school structure");
        System.out.println("[2]Add new student");
        System.out.println("[3]Edit existing student");
        System.out.println("[4]Update student grades");
        System.out.println("[5]Generate reports");
        System.out.println("[6]Import data");
        System.out.println("[7]Export data");
        // option for deleting a student
        // option for quitting program
    }
    
    public static int getSelection() {
        while(true) {
            System.out.print("\nEnter number of selected option >> ");
            try {
                int userOption = scanner.nextInt();
                scanner.nextLine();

                switch(userOption) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                    case 5:
                        return 5;
                    case 6:
                        return 6;
                    case 7:
                        return 7;
                    default:
                        System.out.println("\nYour input is not in the selection!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Only enter the number of your choice!");
                scanner.nextLine();
            }
        }
    }
}
