public class Student {

    private String name;
    private String surname;
    private String ra;
    private double p1;
    private double p2;
    private double p3;
    private double p4;

    public Student(String ra, String name, String surname, double p1, double p2, double p3, double p4){
        this.ra = ra;
        this.name = name;
        this.surname = surname;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Student()
    {
        this.ra = "";
        this.name = "";
        this.surname = "";
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.p4 = 0;
    }

    public double getAverage(){
        return (p1 + p2 + p3 + p4) / 4;
    }

    public String toLine(){
        return ra + ";" + name + ";" + surname + ";" + p1 + ";" + p2 + ";" + p3 + ";" + p4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP4() {
        return p4;
    }

    public void setP4(double p4) {
        this.p4 = p4;
    }

    @Override
    public String toString(){
        return ra + " - " + name + " " + surname + " - " + getAverage();
    }
}
