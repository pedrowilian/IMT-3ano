package Ex2;


class ShapesTest {

public static void main(String[] args) {

        System.out.println("Circle");
        Circle circle = new Circle(5, Math.PI*5*5, 2*Math.PI*5);
        System.out.println(circle.getData());
        Circle circle2 = new Circle(1., Math.PI*1.5*1.5, 2*Math.PI*1.5);
        System.out.println(circle2.getData());

        System.out.println("------------------");
        System.out.println("Square");
        Square square = new Square(5, 25, 25);
        System.out.println(square.getData());
        Square square2 = new Square(1.5, 1.5*1.5, 2*1.5);
        System.out.println(square2.getData());

        System.out.println("------------------");
        System.out.println("Triangle equilátero");
        Triangle triangle = new Triangle(5,  5*Math.sqrt(3)/4, 15);
        System.out.println(triangle.getData());
        Triangle triangle2 = new Triangle(1.5, 1.5/Math.sqrt(3)/4, 3*1.5);
        System.out.println(triangle2.getData());


        System.out.println("------------------");
        System.out.println("Sphere");
        Sphere sphere = new Sphere(5);
        System.out.println(sphere.getData());
        Sphere sphere2 = new Sphere(1.5);
        System.out.println(sphere2.getData());

        System.out.println("------------------");
        System.out.println("Cube");
        Cube cube = new Cube(5);
        System.out.println(cube.getData());
        Cube cube2 = new Cube(1.5);
        System.out.println(cube2.getData());

        System.out.println("------------------");
        System.out.println("Tetraedro regular");
        Tetrahedron tetrahedron = new Tetrahedron(5);
        System.out.println(tetrahedron.getData());
        Tetrahedron tetrahedron2 = new Tetrahedron(1.5);
        System.out.println(tetrahedron2.getData());


}
}
