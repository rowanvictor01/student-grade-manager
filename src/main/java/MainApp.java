package src.main.java;

public class MainApp {
    public static void main(String[] args) {
        Menu.displayMenu();
        int userSelected = Menu.getSelection();
        
        System.out.println(userSelected);
    }
}