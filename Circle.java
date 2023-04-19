public class Circle {
    private Point center, p;    //p on the border of circle   (avoid null value by using private)

    public Circle(Point center, Point p){
        this.setCenter(center); //setCenter will check center is null or not
        this.setP(p);
    }

    // private void setP(Point p2) {
    // }

    public double radius(){
        return center.distance(p);
    }

    public double perimeter(){
        //P = 2*PI*R
        return 2*Math.PI*radius();
    }

    public double surface(){
        //S = PI*R*R
        return Math.PI*radius()*radius();   //Math.pow(radius(), 2)   //radius^2
    }

    public Point getCenter(){
        return center;
    }

    public void setCenter(Point center){
        if(center != null)
            this.center = center;
        else System.out.println("ERROR: center cannot be null");
    }

    public Point getP(Point p){
        return p;
    }

    public void setP(Point p){
        this.p = p;
    }
}
