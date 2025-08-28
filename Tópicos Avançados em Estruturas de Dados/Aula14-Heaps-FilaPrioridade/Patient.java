public class Patient {
    String name;
    int age;
    boolean pregnant;
    boolean postpartum;
    int priority;
    // Constructor
    public Patient(String name, int age, boolean pregnant, boolean postpartum) {
        this.name = name;
        this.age = age;
        this.pregnant = pregnant;
        this.postpartum = postpartum;
        this.priority = definePriority();
    }
    private int definePriority() {
        if (age > 80) return 0;          
        if (age > 60) return 1;         
        if (pregnant || postpartum) return 2; 
        return 3;                        
    }

        @Override
    public String toString() {
        return name + " (age: " + age + ", priority: " + priority + ")";
    }
        public String getName() {
            return name;
        }
}
