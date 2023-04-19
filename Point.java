public class Point {
    private double x,y;
    private static double eps;
    public static final double MAX_X;
    public static final double MAX_Y = 800;
    static{
        MAX_X = 1024;
        eps = 1.0e-5;
    }
    public Point(double _x, double y){
        x = _x;
        this.y = y;
    }
    public Point(){ this(0,0); }
    public Point(Point p){ this(p.x, p.y); }
    public void translate(double dx, double dy){
        x += dx;//x = x + dx;
        this.y += dy;//this.y = this.y + dy;
    }
    public double distance(){
        return Math.sqrt(x*x + y*y);
    }
    public double distance(Point p){
        double x2 = this.x;
        double y2 = this.y;
        double x1 = p.x;
        double y1 = p.y;
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }
    public static void setEps(double ep){eps = ep;}
    public double getX(){return x;}
    public double getY(){return y;}
    public void setY(double _y){y = _y;}
    public void setX(double _x){x = _x;}
    public boolean equals(Point p){
        double dx = Math.abs(x - p.x);
        double dy = Math.abs(y - p.y);
        return (dx < eps && dy < eps);
    }

    public void finalize(){
        System.out.println("I am removed from memory.");
    }
}
