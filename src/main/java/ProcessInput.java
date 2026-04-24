import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class ProcessInput {
    public static void process(int input) {
        switch(input) {
            case 1:
                // returns 1 if file was created successfully, 0 otherwise
                int ret = SetupSchool.setup();
                break;
            case 2:
                if(!checkJsonExists()) {
                    System.out.println("No config file yet. Please create one first!");
                    return;
                }
                Edit.createStudent();
                break;
            case 3:
                if(!checkJsonExists()) {
                    System.out.println("No config file yet. Please create one first!");
                    return;
                }
                Edit.editStudentDetails();
                break;
            case 4:
                if(!checkJsonExists()) {
                    System.out.println("No config file yet. Please create one first!");
                    return;
                }
                Edit.editStudentGrades();
        }

    }

    public static boolean checkJsonExists() {
        String filePath = "data/school.json";
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}
