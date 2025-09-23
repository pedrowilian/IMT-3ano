package Ex2;

public class ThreeDimensionalShape extends Shape {
    private double volume;
    private double surfaceArea;

    ThreeDimensionalShape(double measure) {
        super(measure);
        volume = 0;
        surfaceArea = 0;
    }

    public double getVolume() {
        return 0;
    }

    public double getSurfaceArea() {
        return 0;
    }


    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    @Override
    public String getData()
    {
        return "Volume: " + this.volume + "\nSurface Area: " + this.surfaceArea;
    }
}
