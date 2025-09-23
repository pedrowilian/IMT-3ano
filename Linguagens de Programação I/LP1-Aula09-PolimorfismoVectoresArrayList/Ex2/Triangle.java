package Ex2;

public class Triangle extends TwoDimensionalShape {

    Triangle(double side, double area, double perimeter) {
        super(side, area, perimeter);
    }

    @Override
    public double calculateArea() {
        return (Math.sqrt(3) / 4) * Math.pow(getMeasure(), 2);
    }

    @Override
    public double calculatePerimeter() {
        return 3 * getMeasure();
    }
}
