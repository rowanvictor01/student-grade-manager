// for editing existing .json file

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.InputMismatchException;
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

    // helper method for finding section
    public static int findGradeLevelIndex(Student student, School schoolObj) throws IllegalArgumentException {
        int gradeLevelIndex = 0;

        // checks student's grade level based on what's set on their field
        for(int i = 0; i < schoolObj.gradeLevels.size(); i++) {
            if(Objects.equals(student.getGradeLevel(), schoolObj.gradeLevels.get(i).getLevel())) {
                gradeLevelIndex = schoolObj.gradeLevels.indexOf(schoolObj.gradeLevels.get(i));
                return gradeLevelIndex;
            }
        }
        throw new IllegalArgumentException("Grade level not found");
    }

    public static Section findSection(Student student, School schoolObj) throws IllegalArgumentException {
        int gradeLevelIndex = 0;
        try {
            gradeLevelIndex = findGradeLevelIndex(student, schoolObj);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        ArrayList<Section> sections = schoolObj.gradeLevels.get(gradeLevelIndex).sections;

        // checks student's section based on what's set on their field
        for (int i = 0; i < sections.size(); i++) {
            if(Objects.equals(sections.get(i).getName(), student.getSection())) {
                System.out.println("Section Found!");
                return sections.get(i);
            }
        }
        throw new IllegalArgumentException("Section not found");
    }

    public static void createStudent() {
        // read json file
        School schoolData;
        try {
            schoolData = getJsonData();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
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

    // always use on top of methods to update their school data
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
        Section section = new Section();
        try {
            section = findSection(student, schoolObj);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        section.addStudent(student);

        try (FileWriter writer = new FileWriter("data/school.json")) {
            section.updateHeadCount();
            gsonWrite.toJson(schoolObj, writer);
        }
        catch (IOException e) {
            System.err.println("\n Failed to add student\n" + e);
        }
    }

    public static Student findStudent(School schoolData) throws IOException {
        /*
        since multiple methods are using the same procedure
        to get updated school data, might be good to write a method for it
        */

        System.out.print("Enter student name >> ");
        String name = scanner.nextLine().trim();

       for(int i = 0; i < schoolData.gradeLevels.size(); i++) {
           for(int j = 0; j < schoolData.gradeLevels.get(i).sections.size(); j++) {
               for(int k = 0; k < schoolData.gradeLevels.get(i).sections.get(j).students.size(); k++) {
                   if(Objects.equals(name, schoolData.gradeLevels.get(i).sections.get(j).students.get(k).getName())) {
                       return schoolData.gradeLevels.get(i).sections.get(j).students.get(k);
                   }
               }
           }
       }
        throw new IOException("Student not found");
    }

    public static void editStudentDetails() {
        School schoolData;
        try {
            schoolData = getJsonData();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        Student student;
        int editOption;
        try {
            student = findStudent(schoolData);
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        while(true) {
            System.out.println("\nStudent's Information:");
            System.out.println("[1]Name: " + student.getName());
            System.out.println("[2]Grade Level & Section: " + student.getGradeLevel() + ", " + student.getSection());
            System.out.print("Enter the option's number for editing >> ");

            try {
                editOption = scanner.nextInt();
                scanner.nextLine();
                break;
            }
            catch (InputMismatchException e) {
                System.err.println("Failed to capture input: " + e.getMessage());
                System.err.println("Select input based on the displayed options!");
            }
        }

        switch (editOption) {
            case 1:
                System.out.print("Enter new name >> ");
                String newName = scanner.nextLine().trim();
                student.setName(newName);
                break;
            case 2:
                // delete student in section first before getting new details
                Section section;
                try {
                    section = findSection(student, schoolData);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                    return;
                }
                section.deleteStudent(student);
                section.updateHeadCount();

                // get new grade level and section from user
                System.out.print("Enter new grade level >> Grade: ");
                String newLevel = scanner.nextLine().trim();
                student.setGradeLevel("Grade " + newLevel);

                System.out.print("Enter student's new assigned section name >> ");
                String newSection = scanner.nextLine().trim();
                student.setSection(newSection);
                break;
            default:
                System.out.println("Error: Input not from the options");
        }

        storeStudent(student, schoolData);

        try (FileWriter writer = new FileWriter("data/school.json")) {
            // section.updateHeadCount();
            gsonWrite.toJson(schoolData, writer);
            System.out.println("Changes Saved");
        }
        catch (IOException e) {
            System.err.println("Failed to save changes: " + e.getMessage());
        }
    }
}