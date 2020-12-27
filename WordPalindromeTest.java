import java.util.Scanner;

public class WordPalindromeTest 
{ 
  public static void main(String[] args)
  {
     //inputString();
    //inputStringPalindrome();
  } 

  public static void inputString() 
  {
    System.out.println("\nWelcome to the Palindrone Program!");

    Scanner userInput = new Scanner(System.in);

    System.out.println("\nType in a String to test if it's a Palindrome or \"quit\" to end:");

    while (userInput.hasNextLine()) 
    {
      String line = userInput.nextLine();
      if (line.equals("quit"))
      {
        break;
      }
      else
      {
        System.out.println("Your input: " + line);
        System.out.println(isPalindrome(line));
      }
    }

    System.out.println("bye!");
    userInput.close();
  } 

  public static boolean isPalindrome(String s)
  {
   boolean isPal = false;
   int length = s.length()-1;
   int reverse = length;
   String s1 = s.toLowerCase();
   for(int i = 0; i < length; i++)
   {
     if(s1.charAt(i) == s1.charAt(reverse))
     {
       isPal = true;
     }
     else
     {
       isPal = false;
     }
     reverse -= 1;
    }
   return isPal;
  }

  public static int[] inputStringPalindrome()
  {
    System.out.println("Welcome Palindrones!");

    Scanner takeIn = new Scanner(System.in);

    System.out.println("Type in a String to test if it's a Palindrome or \"quit\" to end:");
    int calls = 1;
    int pali = 0;
    int[] full = {0,0};
    while (takeIn.hasNextLine()) 
    {
      String line = takeIn.nextLine();
      if (line.equals("quit"))
      {
        full[0] = calls;
        full[1] = pali;
        break;
      }
      else
      {
        System.out.println("Your input: " + line);
        System.out.println("Your input is a " + isPalindrome(line) + " Palindrome!");
        calls += 1;
        if(isPalindrome(line) == true)
        {
          pali +=1;
        }
      }
    }
    System.out.println("The method was called " + full[0] + " times and " + full[1] +" inputs were Palindromes.");
    System.out.println("Adios.");
    takeIn.close();
    return full;
  }

  public static boolean isWordPalindrome(String s)
  {
    boolean fin = false;
    String str = s.toLowerCase();
    String [] sent;
    int words = 1;
    int sub = 0;
    for(int i = 0; i < str.length(); i++)
    {
        char letter = str.charAt(i);
        if(letter == ' ')
        {
            sent[words] = str.substring(sub, i);
            sub = 1 + i;
            words += 1;
        }
    }

    int length = words;
    int back = 0;

    while(length != 0)
    {
        if(sent[back].equals(sent[length]))
        {
        fin = true;
        back += 1;
        length -=1;
        }

        else
        {
        fin = false;
        }
    }

    return fin;
    }

} 