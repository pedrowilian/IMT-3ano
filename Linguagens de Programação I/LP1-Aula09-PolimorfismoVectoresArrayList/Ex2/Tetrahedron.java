package Ex2;

public class Tetrahedron extends ThreeDimensionalShape {
    Tetrahedron(double side) {
        super(side);
    }

    @Override
    public double getVolume() {
        return (Math.pow(getMeasure(), 3) / (6 * Math.sqrt(2)));
    }

    @Override
    public double getSurfaceArea() {
        return Math.pow(getMeasure(), 2) * Math.sqrt(3);
    }
}
