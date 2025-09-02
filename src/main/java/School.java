import java.util.ArrayList;

public class School {
    private String name;
    private String type;
    ArrayList<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
    
    // constructor
    public School(String initName, String initType) {
        this.name = initName;
        this.type = initType;
    }

    // add grade levels
    public void addGradeLevel(GradeLevel newGradeLevel) {
        gradeLevels.add(newGradeLevel);
    }
    
    // print levels
    public void printGradeLevels() {
        for(int i = 0; i <= gradeLevels.size() - 1; i++) {
            System.out.println(gradeLevels.get(i).getLevel());
        }
    }
    
    // name getter
    public String getName() {
        return this.name;
    }
    
    // type getter
    public String getType() {
        return this.type;
    }
    
    // name setter
    public void setName(String newName) {
        this.name = newName;
    }
}
