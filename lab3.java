/* 
 * Lab  2 - Debugging Exercise
 * 
 * name:
 * email:
 *
 * Fix all the bugs in this file! Then run it and add some extra test to make
 * sure everything works
 */
public class lab3 {

    public static double triArea(int b, int h) {
	   double area = b/2.0*h;
	    return area;
    }
	
    public static void main(String[] args) {
        System.out.println("Running lab3.java");
        double a = triArea(5,3);
        System.out.print("Area is: " + a);
    }
}