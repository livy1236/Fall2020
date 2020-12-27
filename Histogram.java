/* File: Histogram.java
 * Author: CS112 Instructor
 *  Olivia Bene
 * Purpose: This is a potential solution to the
 * Histogram problem.
 */

import java.util.Scanner;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]
    private static final int BIN_SIZE = 10;             // max size of each bin
   
    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */


    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	    me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	return (name+" ").split(" ")[0]; 
    }

    /* 
     * local main test driver
     *
     */
    public static void main(String[] args) {  

	// Connect to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );

        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

	    String heading = getHeaderAsString( user );

        // Print out welcome message
        System.out.println( heading ); 
        
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers(userInput);

	// Call the method to reate the array histogram
	    int[] histogram = calculateHistogram(numbers);

	// Print the historgram
	    System.out.println(toString(histogram));
    }

    public static int [] calculateHistogram(double [] numbers)    //This method must determine the appropriate bin of the histogram to update
    {

        int [] histo = new int[NUM_BINS];                       //store how much in bin
        
        for(int i = 0; i < histo.length; i++)
        {
            for(int j = 0; j < numbers.length; j++)
            {
                if(numbers[j] > (i * BIN_SIZE) && numbers[j] <= ((i+1) * BIN_SIZE))
                {
                    histo[i]++;
                } 
            }

        }
       

        return histo; 
    }

    public static String toString( int [] histogram ) 
    {

        StringBuilder toString = new StringBuilder();
        toString.append(System.getProperty("line.separator"));
        String star = "*";
        //use histogram to figure out num of * per row
        toString.append("[0..10]:    "+ star.repeat(histogram[0]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(10..20]:   "+ star.repeat(histogram[1]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(20..30]:   "+ star.repeat(histogram[2]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(30..40]:   "+ star.repeat(histogram[3]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(40..50]:   "+ star.repeat(histogram[4]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(50..60]:   "+ star.repeat(histogram[5]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(60..70]:   "+ star.repeat(histogram[6]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(70..80]:   "+ star.repeat(histogram[7]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(80..90]:   "+ star.repeat(histogram[8]));
        toString.append(System.getProperty("line.separator"));
        toString.append("(90..100]:  "+ star.repeat(histogram[9]));
        toString.append(System.getProperty("line.separator"));
        
        return toString.toString();
    }

    public static boolean validInput( double num ) //make sure num is in range
    {
        boolean answer = true;

        if(num < LOWER_BOUND || num > UPPER_BOUND)
        {
            answer = false;
        }
        return answer;
    }

    public static double[] inputNumbers( Scanner scan ) 
    {

        double userArray[] = new double [MAX_NUMBERS];
        boolean nextLine = scan.hasNextLine();
        int numOfValues = 0;
        double input = scan.nextDouble();

        if(numOfValues < MAX_NUMBERS) //stay under 20
        {
            for(int j = 0; j < NUM_BINS; j++)
            {
                userArray[j] = input;
                input = scan.nextDouble();
            

                if(input == SENTINAL) // stop with -999
                {
                    
                    return userArray;
                }

                if(!(validInput(input)))
                {
                    System.out.println("Error, number not in range.");
                }

            }

            numOfValues+=1;
        }

        return userArray;
    }

} // end of class
