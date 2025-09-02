import java.util.ArrayList;

public class Section {
    private String name;
    private int headCount = this.students.size();
    private ArrayList<Student> students = new ArrayList<Student>();
    
    // constructor
    public Section(String initName) {
        this.name = initName;
    }
    
    // add student to students
    public void addStudent(Student newStudent) {
        students.add(newStudent);
    }
    
    // headCount getter
    public int getHeadCount() {
        return this.headCount;
    }
    
    // name getter and setter
    public String getName() {
        return this.name;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }
}
