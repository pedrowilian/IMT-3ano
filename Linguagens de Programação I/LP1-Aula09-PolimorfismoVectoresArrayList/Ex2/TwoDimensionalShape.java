package Ex2;

public class TwoDimensionalShape extends Shape {
    private double area;
    private double perimeter;
    public TwoDimensionalShape(double measure, double area, double perimeter) {
        super(measure);
        this.area = area;
        this.perimeter = perimeter;
    }

    public double calculateArea() {
        return 0;
    }

    public double calculatePerimeter() {
        return 0;
    }

    @Override
    public String getData()
    {
        return "Area: " + this.area + "\nPerimeter: " + this.perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }
}
