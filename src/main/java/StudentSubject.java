public class StudentSubject {
    private Subject subject;
    private double grade;
    
    // constructor
    public StudentSubject(Subject initSubject, double initGrade) {
        this.subject = initSubject;
        this.grade = initGrade;
    }
    
    // subject getter
    public Subject getSubject() {
        return this.subject;
    }
    

    // grade getter
    public double getGrade() {
        return this.grade;
    }
    
    // grade setter
    public void setGrade(double newGrade) {
        this.grade = newGrade;
    }
}
