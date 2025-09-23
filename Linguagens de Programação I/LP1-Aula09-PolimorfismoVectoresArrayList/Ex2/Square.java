package Ex2;

public class Square extends TwoDimensionalShape {

    Square(double side, double area, double perimeter) {
        super(side, area, perimeter);
    }

    @Override
    public double calculateArea() {
        return Math.pow(this.getMeasure(), 2);
    }

    @Override
    public double calculatePerimeter() {
        return 4 * this.getMeasure();
    }
}
