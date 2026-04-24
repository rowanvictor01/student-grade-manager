import java.util.ArrayList;

public class Student {
    private String name;
    private double avgGrade;
    private boolean hasHonors;
    private String section;
    private String gradeLevel;
    public final ArrayList<StudentSubject> subjects = new ArrayList<StudentSubject>();

    // constructor
    public Student(String initName, String initLevel, String initSection) {
        this.name = initName;
        this.section = initSection;
        this.gradeLevel = initLevel;
    }

    // add a subject to subjects array list
    public void addSubject(Subject newSubject, double grade) {
        subjects.add(new StudentSubject(newSubject, grade));
    }
    
    // print subjects elements
    public void printSubjects() {
        for(int i = 0; i <= subjects.size() - 1; i++) {
            System.out.println(this.subjects.get(i).getSubject().getName());
            System.out.println(this.subjects.get(i).getGrade());
            System.out.println();
        }
    }
    

    // getters
    public String getName() {
        return this.name;
    }

    public double getAvg() {
        return this.avgGrade;
    }

    public boolean getHasHonors() {
        return this.hasHonors;
    }

    public String getSection() {
        return this.section;
    }

    public String getGradeLevel() {
        return this.gradeLevel;
    }

    // setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setAvg(double newAvg) {
        this.avgGrade = newAvg;
    }

    public void setHasHonors(boolean newHasHonors) {
        this.hasHonors = newHasHonors;
    }

    public void setSection(String newSection) {
        this.section = newSection;
    }

    public void setGradeLevel(String newGradeLevel) {
        this.gradeLevel = newGradeLevel;
    }
}
