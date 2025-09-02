public class Subject {
    private String name;
    private boolean isMajor;
    
    // constructor
    public Subject(String initName, boolean initIsMajor) {
        this.name = initName;
        this.isMajor = initIsMajor;
    }
    
    // getter for name
    public String getName() {
        return this.name;
    }
    
    // getter and setter for isMajor
    public boolean getIsMajor() {
        return this.isMajor;
    }
    
    public void setIsMajor(boolean newDesc) {
        this.isMajor = newDesc;
    }
}
