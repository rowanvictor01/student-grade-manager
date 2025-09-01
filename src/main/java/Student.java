import java.util.ArrayList;

public class Student {
    private String name;
    private double avgGrade;
    private boolean hasHonors;
    private ArrayList<StudentSubject> subjects = new ArrayList<StudentSubject>();
    private Section section;
    private GradeLevel gradeLevel;

    // constructor
    public Student(String initName, double initAvgGrade, boolean initHasHonors) {
        this.name = initName;
        this.avgGrade = initAvgGrade;
        this.hasHonors = initHasHonors;
    }
    
    // add a subject to subjects array list
    public void addSubject(Subject newSubject, double grade) {
        subjects.add(new StudentSubject(newSubject, grade));
    }
    
    // print subjects elements
    public void printSubjects() {
        for(int i = 0; i <= subjects.size() - 1; i++) {
            System.out.println(this.subjects.get(i));
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
    
    public Section getSection() {
        return this.section;
    }
    
    public GradeLevel getGradeLevel() {
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
    
    public void setSection(Section newSection) {
        this.section = newSection;
    }
    
    public void setGradeLevel(GradeLevel newGradeLevel) {
        this.gradeLevel = newGradeLevel;
    }
}
