import java.util.ArrayList;
import java.util.Objects;

public class Section {
    private String name;
    private int headCount;
    ArrayList<Student> students = new ArrayList<Student>();

    // constructor
    public Section(String initName) {
        this.name = initName;
    }

    public Section() {}
    
    // add student to students
    public void addStudent(Student newStudent) {
        students.add(newStudent);
    }

    // remove student to students
    public void deleteStudent(Student student) {
        for(int i = 0; i < students.size(); i++) {
            if(Objects.equals(student.getName(), students.get(i).getName())) {
                students.remove(i);
                return;
            }
        }
    }
    
    // headCount getter
    public int getHeadCount() {
        return this.headCount;
    }

    // for every add/delete of student in a section
    public void updateHeadCount() {
        this.headCount = students.size();
    }

    // name getter and setter
    public String getName() {
        return this.name;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }
}
