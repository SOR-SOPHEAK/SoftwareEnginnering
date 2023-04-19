public class TP06_1_Circle {
    public static void main(String[] args) {
        Point c = new Point();
        Point p = new Point(5,0);
        Circle circle = new Circle(c, p);
        c.setX(5);  //change value x

        System.out.println("Radius: "+circle.radius());
        System.out.println("Perimeter: "+circle.perimeter());
        System.out.println("Surface: "+circle.surface());

    //create 2 point by using the same center called share point
    //create new circle, create new point
        Point p2 = new Point(15,10);
        Circle circle2 = new Circle(c, p2);
        //c.setX(5);  //change value x  (dak nv ng kor change ah ler not change ah circle2)

        System.out.println("\nRadius2: "+circle2.radius());
        System.out.println("Perimeter2: "+circle2.perimeter());
        System.out.println("Surface2: "+circle2.surface());
    }
}

//aggregation = 1 obj share to another obj  ex:have 1 access point and share to another to use it effect to yg
//how to know: (this.center = center) use existing obj
//when use: when want resource optimize resource

//composition = use own obj, create new obj (pel create new circle need to create new point)
//how to know: (this.center = new Point(center))  one center for one point only
//when use: when cannot share obj, don't care about resource want to be fast
