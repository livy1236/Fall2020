/* File: RecursiveGraphics.java
 * Author: Olivia Bene
 * Date: 10/20/2020
 * Purpose/: This is the template for PS5, Problem 6
 */

import java.awt.Color;
import java.awt.Canvas;
import java .awt.Graphics;
import javax.swing.JFrame;

public class RecursiveGraphics extends Canvas  {

    private static final int windowSize = 800;     // height and width of window in pixels
     
    
    
    
    private void drawHTree(int depth, Graphics g) {
        drawHTreeHelper(windowSize/2, windowSize/2, windowSize/2, true, depth, g);
    }

    // (x,y) is center of line of length s
    private void drawHTreeHelper(double x, double y, double s, boolean horizontal, int depth, Graphics g) {
 
	if(depth == 0)
            return;
        if(horizontal) {
            drawLine(x-s/2, y, x+s/2, y, g);
            drawHTreeHelper(x-s/2, y, s*(2.0/3.0),false, depth-1, g);     // alternate horizontal and vertical
            drawHTreeHelper(x+s/2, y, s*(2.0/3.0), false, depth-1, g);    // center next line at endpoint of previous
        }                                                                 // and smaller by 2/3. 
        else {  // vertical
            drawLine(x, y-s/2, x, y+s/2, g);
            drawHTreeHelper(x, y-s/2, s*(2.0/3.0), true, depth-1, g);
            drawHTreeHelper(x, y+s/2, s*(2.0/3.0), true, depth-1, g);
        }
        
    }

    
   // Generalization of an H Tree in which can have any number of branches, with
   //    a fixed delta (how much smaller to make it)  for each length and each angle. 

   private void drawTree(int depth, Graphics g) {
	drawTreeHelper(windowSize/2, windowSize*0.85, windowSize/4, Math.PI/2, depth, g);
   }

    // You could try changing these two parameters if you feel adventurous!
    
   private double[] lenDelta = {2.0/3, 2.0/3 };             // multiply length of branch by this factor each time
   private double[] thetaDelta = {Math.PI/5, -Math.PI/5};   // add this delta to angle each time -- in radians
    
    private void drawTreeHelper(double x0, double y0, double len, double angle, int depth, Graphics g) {
       if(depth == 0) {
           return;
       }
       
       // draw lines
       
       double x1 = x0 - len * Math.cos(angle);
       double y1 = y0 - len * Math.sin(angle);
 
       drawLine(x0,y0,x1,y1,g);
       
       for (int i = 0; i < lenDelta.length; ++i) { 
          drawTreeHelper(x1, y1, len * lenDelta[i], angle + thetaDelta[i], depth-1,g);
       }
   }



    //  Problem 6.1    Your Turn!
    //  For this one, you have to figure out how to do the recursive case only.
  
    private void drawFractalLine(double x1, double y1, double x5, double y5, int depth, Graphics g) {
       if (depth <= 1) {
	    drawLine(x1, y1, x5, y5, g);
       }
       else 
       {	  
            double deltaX = x5 - x1;     // distance between the two x's
            double deltaY = y5 - y1;     // distance between the two y's
           
            double x2 = x1 + deltaX/3.0;
            double y2 = y1 + deltaY/3.0;

	        double x4 = x5 - deltaX/3.0;
            double y4 = y5 - deltaY/3.0;
	   
            double x3 = ((x1 + x5)/2.0 - (Math.sqrt(3.0)/6.0) * (y1 - y5));
            double y3 = ((y1 + y5)/2.0 - (Math.sqrt(3.0)/6.0) * (x5 - x1));
	   
	   
            // connect all the dots!  You have to call the method recursively on
	        // each of the line segments:
	        //                          (x3,y3) 
	        //                           /  \
	        //                          /    \   
	        //       (x1,y1) --- (x2,y2)     (x4,y4) --  (x5,y5) 

            drawFractalLine(x1, y1, x2, y2, depth - 1, g);   //1st line
            drawFractalLine(x2, y2, x3, y3, depth - 1, g);   //2nd line
            drawFractalLine(x3, y3, x4, y4, depth - 1, g);   //3rd line
            drawFractalLine(x4, y4, x5, y5, depth - 1, g);   //final line

       }
   }

    // Problem 6.2      Supposing you have a square with left corner (x,y) and each side of length s
    //                  Now draw the square with fractal lines instead of normal lines!
    //                  Draw them in the order top, right, bottom, left sides (clockwise from top).

    //                  Note: This is not recursive, it just calls drawFractalLines 4 times. 
   
    private void drawFractalSquare(double x, double y, double s, int depth, Graphics g) 
    {
	
        drawFractalLine(x, y,(x+s), y, depth, g);                      //top
        drawFractalLine((x+s), y, (x+s), (y+s), depth, g);             //right side
        drawFractalLine((x+s), (y+s), (x), (y+s), depth, g);           //bottom
        drawFractalLine((x), (y+s), x, y, depth, g);                   //left side

    }   
   
    


    // Problem 6.3: Draw a Sierpinski Triangle.    The basic method uses drawFilledPolygon, which
    //      draws a filled shape enclosed by the points in X and Y.
    //      The three points are (X[0],Y[0]), (X[1],Y[1]), (X[2],Y[2]). 
    

    
    private void drawSierpinskiTriangle(double X[], double Y[], int depth, Graphics g) 
    {
		
        if (depth==0) 
        {
            return;
        }
        
        g.setColor(Color.BLACK); 

        // Draw a filled triangle using the points in X and Y
        drawFilledPolygon(X, Y, g);

        // Now calculate the midpoints in each side, as described in the video
        double y_mid = (Y[1] + Y[2])/ 2.0;
        double x_mid = X[0];

        double x_left = (X[0] + X[2])/ 2.0;
        double y_left = (Y[0] + Y[2])/ 2.0;

        double x_right = (X[0] + X[1])/ 2.0; 
        double y_right = (Y[0] + Y[1])/ 2.0;
        
        g.setColor(Color.WHITE);
        
        // Now recursively fill in middle sub-triangle in white
        double [] A = {x_mid, x_left, x_right}; //new x
        double []B = {y_mid, y_left, y_right}; //new y

        drawFilledPolygon(A, B, g);

        double triangleAX [] = {X[0],x_left,x_right};
        double triangleAY [] = {Y[0], y_left, y_right};

        double triangleBX [] = {x_right,x_mid, X[1]}; 
        double triangleBY [] = {y_right,y_mid, Y[1]};

        double triangleCX [] = {x_left, X[2], x_mid}; 
        double triangleCY [] = {y_left, Y[2], y_mid};

        // Draw the remaining sub-triangles recursively, doing something like this 3 times:
        drawSierpinskiTriangle(triangleAX, triangleAY, depth-1, g);
        drawSierpinskiTriangle(triangleBX, triangleBY, depth-1, g);
        drawSierpinskiTriangle(triangleCX, triangleCY, depth-1, g);         
    }
  

 
    // Problem 6.4:

    // Draw a Sierpinski Carpet with upper-left corner (x,y) and side of length s
    //     Imagine the S Carpet is a TicTacToe board: draw the whole board blue, then
    //     draw the center square in green, then a circle in red, then recursively draw S Carpets 1/3 the size in
    //     each of the other squares.

    // You can modify the color of the next thing to be drawn on g by using
    //       	g.setColor(Color.BLACK);
    //          g.setColor(Color.WHITE);
    //	        g.setColor(Color.RED);
    //	        g.setColor(Color.GREEN);
    //	        g.setColor(Color.BLUE);
    // Just put one of these lines before the call to a drawing method. 
    
    private void drawSierpinskiCarpet(double X[], double Y[], int depth, Graphics g) 
    {
        if (depth==0) 
        {
            return;
        }

        double s = X[1] - X[0];
        /*
        double oneThirdX = sX/3.0;
        double twoThirdX = oneThirdX/(2.0/3.0);

        double sY = Y[1] - X[0];
        double oneThirdY = sY /3.0;
        double twoThirdY = oneThirdY/(2.0/3.0);
        double rSideY = twoThirdY - sY ; //maybe plus
        double lSideY = rSideY + sY;
        */

        //Make board blue
        g.setColor(Color.BLUE);
        drawFilledPolygon(X,Y,g); //draws blue box

        //make green square
        //X values of 4 points
        double aX = X[0] + (s/3.0);
        double bX = X[0] + 2*(s/3.0);
        double cX = X[0] + 2*(s/3.0);
        double dX = X[0] + (s/3.0);

        //Y values of 4 points
        double aY = Y[0] + (s/3.0);
        double bY = Y[0] + (s/3.0);
        double cY = Y[0] + 2*(s/3.0);
        double dY = Y[0] + 2*(s/3.0);


        double boxX [] = {aX, bX, cX, dX};
        double boxY [] = {aY, bY, cY, dY};

        g.setColor(Color.GREEN);
        drawFilledPolygon(boxX, boxY, g); //draws green box

        //Red Circle
        g.setColor(Color.RED);
        drawFilledCircle(aX, aY, s/3, g);

        //Recursion for 8 edges

        //one
        double oneX [] = {X[0], aX, aX, X[0]};
        double oneY [] = {Y[0], Y[0], aY, aY};

        drawSierpinskiCarpet(oneX, oneY, depth - 1, g);

        //two
        double twoX [] = {aX, bX, bX, aX};
        double twoY [] = {Y[0], Y[0],bY, bY};

        drawSierpinskiCarpet(twoX, twoY, depth - 1, g);

        //three
        double threeX [] = {bX, X[1], X[1], bX};
        double threeY [] = {Y[0], Y[0], bY, bY};

        drawSierpinskiCarpet(threeX, threeY, depth - 1, g);

        //four
        double fourX [] = {bX, X[1], X[1], bX};
        double fourY [] = {bY, bY, cY, cY};

        drawSierpinskiCarpet(fourX, fourY, depth - 1, g);

        //five
        double fiveX [] = {cX, X[1], X[2], cX};
        double fiveY [] = {cY, cY, Y[2], Y[2]};

        drawSierpinskiCarpet(fiveX, fiveY, depth - 1, g);

        //six
        double sixX [] = {dX, cX, cX, dX};
        double sixY [] = {dY, dY, Y[2], Y[2]};

        drawSierpinskiCarpet(sixX, sixY, depth - 1, g);

        //seven
        double sevenX [] = {X[0], dX, dX, X[0]};
        double sevenY [] = {dY, dY, Y[3], Y[3]};

        drawSierpinskiCarpet(sevenX, sevenY, depth - 1, g);

        //eight
        double eightX [] = {X[0], aX, dX, X[0]};
        double eightY [] = {aY, aY, dY, dY};

        drawSierpinskiCarpet(eightX, eightY, depth - 1, g);
	
    }
      

    /*****    These are the methods can can be used to draw on the pop-up window.  
     *****    They are just wrappers around methods from Graphics, which expect ints in units of pixels. 
     *****/

    // my version of round, using doubles and ints
    
    private int round(double x) {
	return (int) (x+0.5);
    }

    private int[] round(double x[]) {
	int[] x1 = new int[x.length];
	for (int i = 0; i < x.length; ++i)
	    x1[i] = round(x[i]);
	return x1;
    }

    private double avg(double x, double y) {
	return (x+y)/2.0;
    }

    //    drawLine(x1,y1,x2,y2,g);  draw a line from (x1,y1) to (x2,y2) on g
    //    this is just a wrapper around the Graphics method (which expects ints in units of pixels)

    public void drawLine(double x1, double y1, double x2, double y2, Graphics g) {
	g.drawLine(round(x1),round(y1),round(x2), round(y2));
    }

    //    drawCircle(x,y,x2,y2,g);  draw a circle with a bounding box with upper-left corner (x,y) and side s

    public void drawCircle(double x, double y, double s, Graphics g) {
	g.drawOval(round(x),round(y),round(s),round(s));
    }

    //    drawFilledCircle(x,y,x2,y2,g); draw a circle with a bounding box with upper-left corner (x,y) and side s 

     public void drawFilledCircle(double x, double y, double s, Graphics g) {
	g.fillOval(round(x),round(y),round(s),round(s));
    }   
    
    //    drawPoly(x,y,g); draw a polygon connecting the points (x[0],y[0]), (x[1],y[1]), ...
    
    public void drawPolygon(double X[], double Y[], Graphics g) {
	if (X.length != Y.length) {
	    throw new IllegalArgumentException ("drawPolygon expects two arrays of same length!");
	}
        g.drawPolygon(round(X),round(Y),X.length); 
    }

    //    drawFilledPoly(x,y,g); draw a filled polygon connecting the points (x[0],y[0]), (x[1],y[1]), ...
    
    public void drawFilledPolygon(double X[], double Y[], Graphics g) {
	if (X.length != Y.length) {
	    throw new IllegalArgumentException ("drawFilledPolygon expects two arrays of same length!");
	}
      	g.fillPolygon(round(X),round(Y),X.length); 
    }




   
    /*****      Must override the paint method from Graphics.
     *****      Here is where you put anything you want to draw on
     *****      the pop-up window. You must refer to the Graphics class
     *****      g in your method calls.
     *****
     *****      These start the drawings in an appropriate place in the window; you should
     *****      not have to change anything here except for the maximum recursion depth. 
     *****
     *****      WHEN YOU SUBMIT YOUR SOLUTION, LEAVE ALL OF THESE COMMENTED OUT EXCEPT FOR LAST ONE;
     *****      WHEN WE RUN YOUR PROGRAM IT SHOULD DISPLAY THE S CARPET. 
     *****      THE GRADER WILL UNCOMMENT AND TRY THE REST AS WELL. 
     *****/ 
    
    public void paint(Graphics g) {
	
        // drawHTree(3,g);       // Problem 6.0.1                                                                               DONE
	    // drawTree(3,g);        // Problem 6.0.2                                                                               DONE
        //drawFractalLine(windowSize*0.1, windowSize*0.3, windowSize*0.8, windowSize*0.6, 3, g);     // Problem 6.1	            DONE   
	    // drawFractalSquare(windowSize*0.5,windowSize* 0.5,windowSize* 0.2, 3, g);                  // Problem 6.2             DONE
	    //drawSierpinskiTriangle(new double[] { windowSize*0.5,   windowSize, 0          },          // Problem 6.3             DONE
        //                       new double[] { windowSize*0.134, windowSize, windowSize },
       	//                     3,  g);        
	      drawSierpinskiCarpet(new double[] { 0, windowSize, windowSize, 0          },               // Problem 6.4             DONE
	                        new double[] { 0, 0,          windowSize, windowSize },
	                        3,  g);           
 
    }
    


    // The main method just creates the window and renders whatever
    // is in the paint method above.  Don't change this!
    
    public static void main(String[] args) {
    
      JFrame frame = new JFrame("CS 112 Recursive Graphics");
      Canvas canvas = new RecursiveGraphics(); 
      canvas.setSize(windowSize,windowSize);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.WHITE);
      frame.add(canvas);
      frame.pack();
      frame.setVisible(true);
      
    }
}