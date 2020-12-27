/* 
 * BigInt.java
 * Olivia bene
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() 
    {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }

    /*
     *
     * YOUR METHODS
     *
     */

    public BigInt(int[] arr) //additional constructor
    {
        if(arr.length > SIZE || arr == null )
        {
            throw new IllegalArgumentException();
        }

        this.digits = new int[SIZE];
        this.numSigDigits = this.getNumSigDigits();
     }

     public int getNumSigDigits() //returns the value of the numSigDigits field
     {
        int num = 0;
        int index;


        for(int i = 0; i < this.digits.length; i++)
        {
            while(digits[i] == 0) //get past leading zeros
            {
                index ++;
                break;
            }
        }

        for(int i = index; i < digits.length; i++) // collect the rest of the nums
            {
                num++;
            }

        return num;
     }


    public String toString()
     {
        int index;
        StringBuilder answer = new StringBuilder();

        for(int i = 0; i < this.digits.length; i++)
        {
            while(digits[i] == 0) //get past leading zeros
            {
                index ++;
                break;
            }
        }

        for(int i = index; i < digits.length; i++) // make the string
            {
            answer.append(Integer.toString(digits[i]));
        }

        return answer.toString();
     }

    
    public BigInt(int n) //A constructor that creates a BigInt object representing the integer n. If n is negative, throw an IllegalArgumentException.
     {
         int length = (int) (Math.log10(n) + 1); // find length of n

        if(n < 0)
        {
            throw new IllegalArgumentException();
        }

        String number = String.valueOf(n);
        for(int i = 0; i < length; i++) // fill array
        {
            this.digits[i] = Character.getNumericValue(number.charAt(i));
        }

        this.numSigDigits = this.getNumSigDigits();
     }

    public int compareTo(BigInt other) //This method should compare the called BigInt object to the parameter
    {
        int length = this.digits.length;
        int length1 = other.digits.length;
        int finalLength;
        int answer = 0;
        int score;
        int score1;

        if(length >= length1)
        {
            finalLength = length;
        }
        else
        {
            finalLength = length1;
        }


        for(int i = finalLength; i >= 0; i--)
        {
            if(this.digits[i] > other.digits[i])
            {
                score+=1;
            }

            if(this.digits[i] < other.digits[i])
            {
                score1 += 1;
            }
        }

        if(score > score1)
        {
            answer = -1;
        }
        if(score1 > score)
        {
            answer =1;
        }

        return answer;
    }

    public BigInt add(BigInt other) // add two together
    {
        int length = this.digits.length;
        int length1 = other.digits.length;
        int finalLength;
        int carry = 0;
        BigInt answer = new BigInt();

        if(length >= length1)
        {
            finalLength = length;
        }
        else
        {
            finalLength = length1;
        }


        for(int i = finalLength; i >= 0; i--)
        {

            answer.digits[i] = this.digits[i] + other.digits[i];

            if(carry == 1)
            {
                answer.digits[i] +=1;
            }

            if(this.digits[i] + other.digits[i] > 9)
            {
                answer.digits[i] %= 10;
                carry = 1;
            }
            else
            {
                 carry = 0;
            }

        }

        return answer;
    }

    public BigInt mul(BigInt other) //multiply two together
    {
        int length = this.digits.length;
        int length1 = other.digits.length;
        int finalLength;

        String this1 = "";
        String other1 = "";

        int thisOne = 0;
        int otherOne = 0;

        for(int i =0; i < length; i++)
        {
          this1 += Integer.toString(this.digits[i]);
        }

        for(int i =0; i < length1; i++)
        {
          other1 += Integer.toString(other.digits[i]);
        }

        thisOne = Integer.valueOf(this1);
        otherOne = Integer.valueOf(other1);

        BigInt answer = new BigInt();

        int product = thisOne * otherOne;
        String productForAnswer;

        for(int i = 0; i < finalLength; i++) // fill array
        {
            answer.digits[i] = Character.getNumericValue(productForAnswer.charAt(i));
        }

        return answer;
    }

     /*
     * Unit Test: Following is a local main method where we put some sample tests
     * you can use to verify that this class works properly; This code should only
     * be used except for debugging and testing purposes.
     *
     */
 
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         *
         * Note that these unit tests are not complete and you should
         * think of additional tests that you should run to ensure
         * that all your methods work as expected.
         */

        /*
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        */
    }
}
