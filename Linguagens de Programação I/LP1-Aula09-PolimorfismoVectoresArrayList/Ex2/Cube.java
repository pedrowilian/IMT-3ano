package Ex2;

public class Cube extends ThreeDimensionalShape {
    Cube(double side) {
        super(side);
    }

    @Override
    public double getVolume() {
        return Math.pow(getMeasure(), 3);
    }

    @Override
    public double getSurfaceArea() {
        return 6 * Math.pow(getMeasure(), 2);
    }
}
