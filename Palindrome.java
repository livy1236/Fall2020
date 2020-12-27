/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Olivia Bene
 *     username: livbene
 */
   
public class Palindrome extends LLList
{
    // Add your definition of isPal here.

    public static boolean isPal(String s)
    {
        if(s == null)
        {
          throw new IllegalArgumentException("Cannot be null");
        }

        if(s.length() == 1 || s.length() == 0)
        {
            return true;
        }

        boolean answer = false;

        String str = s.toLowerCase();

        str = str.replace("!", "");
        str = str.replace(" ", "");
        str = str.replace("?", "");
        str = str.replace(".", "");
        str = str.replace("'", "");
        str = str.replace(",", "");
        str = str.replace("/", "");
        str = str.replace(":", "");
        str = str.replace("&", "");
        str = str.replace("(", "");
        str = str.replace(")", "");
        str = str.replace(";", "");

        int len = str.length()-1;
        Object [] arr = new Object[len];

        LLList pal = new LLList(arr);

        for(int i = 0; i < str.length(); i++)
        {
            pal.addItem(str.charAt(i), i);
        }
        
        int length = (pal.length() -1)/2;
        int temp = arr.length;

        for(int i = 0; i < temp; i++)
        {

            if(pal.getItem(i) == pal.getItem(length))
            {
                length --;
                answer = true;
            }
            else
            {
                return false;
            }
        }

        return answer;
    }
    
    public static void main(String[] args) 
    {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
        
        System.out.println();
        System.out.println("(1) Testing on \"Racecar\"");
        try {
            boolean results = isPal("Racecar");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }


        System.out.println();

        System.out.println("(2) Testing on \"How is your day?\"");
        try {
            boolean results = isPal("How is your day?");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(!results);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }



        System.out.println();

        System.out.println("(3) Testing on \"abcdeffedcba\"");
        try {
            boolean results = isPal("abcdeffedcba");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }




        System.out.println();

        System.out.println("(4) Testing on \"abba\"");
        try {
            boolean results = isPal("abba");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }




        System.out.println();

        System.out.println("(5) Testing on \"Go Terriers!\"");
        try {
            boolean results = isPal("Go Terriers!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(!results);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        
    }
}