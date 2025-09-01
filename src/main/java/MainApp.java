// create one array that has all of the subject objects and loop through that and feed to the .addSubject method in the Student class

public class MainApp {
    public static void main(String[] args) {
        Menu.displayMenu();
        int userSelected = Menu.getSelection();
        
        System.out.println(userSelected);
    }
}