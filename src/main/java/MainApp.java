
public class MainApp {
    public static void main(String[] args) {

        while(true) {

            Menu.displayMenu();
            int userSelected = Menu.getSelection();

            // 5 is the menu option to exit program
            if(userSelected == 5) {
                break;
            }

            ProcessInput.process(userSelected);

        }

    }
}