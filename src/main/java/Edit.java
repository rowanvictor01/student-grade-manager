// for editing existing .json file

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Edit {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Gson gson = new Gson();
    private static final Gson gsonWrite = new GsonBuilder().setPrettyPrinting().create();

    public static void createStudent() {
        // read json file
        School schoolData;
        try {
            schoolData = getJsonData();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        // create the default subjects for student
        Subject[] defaultSubjects = new Subject[5];
        String[] subjectNames = {"Math", "Science", "English", "History", "Physical Education"};

        for(int i = 0; i <= defaultSubjects.length - 1; i++) {
            if(i == 4) {
                defaultSubjects[4] = new Subject(subjectNames[4], false);
                break;
            }
            defaultSubjects[i] = new Subject(subjectNames[i], true);
        }

        System.out.print("Student's name >> ");
        String name = scanner.nextLine().trim();

        // refactor into a helper method
        System.out.println("\nGrade Levels:");
        schoolData.printGradeLevels();
        System.out.print("Grade Level >> ");
        String level = scanner.nextLine();

        // refactor into a helper method
        System.out.println("\nSections:");
        schoolData.gradeLevels.get(0).printSections();
        System.out.print("Section >> ");
        String section = scanner.nextLine();

        Student newStudent = new Student(name, "Grade " + level, section);

        for(int i = 0; i <= defaultSubjects.length - 1; i++) {
            newStudent.addSubject(defaultSubjects[i], 0.0);
        }

        // store student to specified section
        storeStudent(newStudent, schoolData);
    }

    public static School getJsonData() throws IOException {
        try(FileReader reader = new FileReader("data/school.json")) {
            School jsonData = gson.fromJson(reader, School.class);
            return jsonData;
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
            throw new IOException("An error occurred while trying to read file\n" + e);
        }
    }

    public static void storeStudent(Student student, School schoolObj) {
        int gradeLevelIndex = 0;

        for(int i = 0; i < schoolObj.gradeLevels.size(); i++) {
            if(Objects.equals(student.getGradeLevel(), schoolObj.gradeLevels.get(i).getLevel())) {
                gradeLevelIndex = schoolObj.gradeLevels.indexOf(schoolObj.gradeLevels.get(i));
            }
        }

        ArrayList<Section> sections = schoolObj.gradeLevels.get(gradeLevelIndex).sections;

        for (int i = 0; i <= sections.size() - 1 ; i++) {
            if(Objects.equals(sections.get(i).getName(), student.getSection())) {
                System.out.println("Section Found!");
                sections.get(i).addStudent(student);

                try (FileWriter writer = new FileWriter("data/school.json")) {
                    sections.get(i).updateHeadCount();
                    gsonWrite.toJson(schoolObj, writer);
                }
                catch (IOException e) {
                    System.err.println("\n Failed to add student\n" + e);
                }
                return;
            }
        }
        System.out.println("Section not found");
    }
}
