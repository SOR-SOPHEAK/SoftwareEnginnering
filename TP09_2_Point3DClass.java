import java.util.Scanner;

public class TP09_2_Point3DClass {
    private double x;
    private double y;
    private double z;
    private static double eps;
    public static final double MAX_X;
    public static final double MAX_Y;
    public static final double MAX_Z;
    static{
        MAX_X = 1024;
        MAX_Y = 800;
        MAX_Z = 800;
        eps = 1.0e-5;
    }

    public TP09_2_Point3DClass(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public double getZ() {return z;}
    public void setZ(double z) {this.z = z;}

    public TP09_2_Point3DClass(){ this(0,0,0); }
    public TP09_2_Point3DClass(TP09_2_Point3DClass point){ this(point.x, point.y, point.z); }
    
    public String translate(double dx, double dy, double dz){
        x += dx;
        y += dy;
        z += dz;
        return "("+x+ "," +y+ "," +z+")";
    }
    public static void setEps(double ep){eps = ep;}
    public boolean equals(TP09_2_Point3DClass point){
        double dx = Math.abs(x - point.x);
        double dy = Math.abs(y - point.y);
        double dz = Math.abs(z - point.z);
        return (dx<eps && dy<eps && dz<eps);
    }
    public double distance(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public static TP09_2_Point3DClass dataInputPoint3dPoint(){
        Scanner sc = new Scanner(System.in);
        TP09_2_Point3DClass p1 = new TP09_2_Point3DClass();
        
        System.out.println("\nInput coordinates x, y, z");
        System.out.print("\tx: ");
        p1.setX(sc.nextDouble());
        System.out.print("\ty: ");
        p1.setY(sc.nextDouble());
        System.out.print("\tz: ");
        p1.setZ(sc.nextDouble());

        return p1;
    }

    public void dataOutputPoint3d(){
        TP09_2_Point3DClass p1 = new TP09_2_Point3DClass();
        System.out.println("Coordinaates of point "+p1.getX()+","+p1.getY()+","+p1.getZ());
        System.out.println("Translate coordinaates of point "+p1.translate(2, 4, 7));
    }
    

    public static void main(String[] args) {
        TP09_2_Point3DClass point = TP09_2_Point3DClass.dataInputPoint3dPoint();
       
        point.dataOutputPoint3d();
    }
   
}
