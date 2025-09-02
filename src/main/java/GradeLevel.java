import java.util.ArrayList;

public class GradeLevel {
    private String level;
    ArrayList<Section> sections = new ArrayList<Section>();
    
    // constructor
    public GradeLevel(String initLevel) {
        this.level = initLevel;
    }
    
    // add sections to a grade level
    public void addSection(Section newSection) {
        sections.add(newSection);
    }
    
    // print sections in a grade level
    public void printSections() {
        for(int i = 0; i <= this.sections.size() - 1; i++) {
            System.out.println(this.sections.get(i).getName());
        }
    }
    
    // level getters and setters
    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String newLevel) {
        this.level = newLevel;
    }
}
