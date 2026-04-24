import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetupSchool {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern typePattern = Pattern.compile("private|public", Pattern.CASE_INSENSITIVE);
    public static int setup() {
        if(doesJsonExists()) {
            System.out.println("A json file has already been initialized");
            return 0;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        System.out.println("This action can only be done once and can't be done again when a json file has been initialized");
        
        School mySchool = getUserInput();
        ArrayList<GradeLevel> manufactured = gradeLevelFactory();
        
        for(int i = 0; i <= manufactured.size() - 1; i++) {
            mySchool.addGradeLevel(manufactured.get(i));
        }
        
        
        try (FileWriter writer = new FileWriter("data/school.json")) {
            gson.toJson(mySchool, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
    
    public static School getUserInput() {
        boolean matchFound;
        String type;
        
        System.out.print("Set school's name >> ");
        String name = scanner.nextLine().trim();
        
        do {
            System.out.print("\nPrivate or Public? >> ");
            type = scanner.nextLine().trim();
            Matcher typeMatcher = typePattern.matcher(type);
            matchFound = typeMatcher.find();

            if(!matchFound) {
                System.out.println("\nPlease choose between Private or Public!");
            }
        } while(!matchFound);

        return new School(name, type);
    }
    
    public static ArrayList<GradeLevel> gradeLevelFactory() {
        String[] levels = {"Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12"};
        ArrayList<GradeLevel> manufactured = new ArrayList<GradeLevel>();

        // create the GradeLevel instances and store them
        for(int i = 0; i <= levels.length - 1; i++) {
            manufactured.add(new GradeLevel(levels[i]));
        }

        // then create and store Section objects into the GradeLevels objects' ArrayList in manufactured
        for(int i = 0; i <= manufactured.size() - 1; i++) {
            manufactured.get(i).addSection(new Section("First"));
        }
        
        return manufactured;
    }

    public static boolean doesJsonExists() {
        String filePath = "data/school.json";
        Path path = Paths.get(filePath);

        return Files.exists(path);
    }
}
