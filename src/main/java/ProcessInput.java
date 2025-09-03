import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class ProcessInput {
    public static void process(int input) {
        switch(input) {
            case 1:
                SetupSchool.setup();
                break;
            case 2:
                if(!checkJsonExists()) {
                    System.out.println("No config file yet. Please create one first!");
                    return;
                }
                Edit.createStudent();
                break;
            case 3:
                // trying out method .findStudent
                try {
                    System.out.println(Edit.findStudent().getGradeLevel());
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
        }
    }

    public static boolean checkJsonExists() {
        String filePath = "data/school.json";
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}
