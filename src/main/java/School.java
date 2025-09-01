import java.util.ArrayList;

public class School {
    private String name;
    private ArrayList<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
    
    // constructor
    public School(String initName) {
        this.name = initName;
    }
    
    // add grade levels
    public void addGradeLevel(GradeLevel newGradeLevel) {
        gradeLevels.add(newGradeLevel);
    }
    
    // print levels
    public void printGradeLevels() {
        for(int i = 0; i <= gradeLevels.size() - 1; i++) {
            System.out.println(gradeLevels.get(i));
        }
    }
    
    // name getter
    public String getName() {
        return this.name;
    }
    
    // name setter
    public void setName(String newName) {
        this.name = newName;
    }
}
