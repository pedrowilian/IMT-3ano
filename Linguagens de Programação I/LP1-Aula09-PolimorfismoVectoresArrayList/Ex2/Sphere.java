package Ex2;

public class Sphere extends ThreeDimensionalShape {
    Sphere(double radius) {
        super(radius);
    }

    @Override
    public double getVolume() {
        return (4.0/3.0) * Math.PI * Math.pow(getMeasure(), 3);
    }

    @Override
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(getMeasure(), 2);
    }


}
