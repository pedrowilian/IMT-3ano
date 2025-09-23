package Ex2;

public class Circle extends TwoDimensionalShape {

    public Circle(double radius, double area, double perimeter) {
        super(radius, area, perimeter);
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(this.getMeasure(), 2);
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * this.getMeasure();
    }
}
