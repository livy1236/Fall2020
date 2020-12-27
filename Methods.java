/*
 * CS112
 *
 * Lab 2, Methods
 *
 * name: 
 * email:
 *
 *
 * Note: Because this class is simply a collection of static methods 
 * and it does not have a main method, you cannot run it.
 * 
 * You will need to write a main method that makes calls to each of the
 * methods to test them. Follow the instructions in the lab.
 */
import java.util.Scanner;

public class Methods {
    /*
     * print3Times - takes a string s and prints it 3 times
     */
    public static void print3Times(String s) 
    {
        for (int i = 0; i < 3; i++) 
        {
            System.out.println(s);
        }
    }
    
    //Write a static method called printNTimes that takes an integer n and a string
    public static void printNTimes(int a, string b)
    {
        for (int i = 0; i < a; i++) 
        {
            System.out.println(b);
        }
    }

    //Write a static method called greetMe that greets you. The method should issue a prompt asking for your name, display a polite (or not so polite) greeting message and then prompt you to enter your age.
    public static void greetMe()
    {
        System.out.print("What is your name?: ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        System.out.print("Hi " + name +"!");
        System.out.println("What's your age?: ");

        int age = scan.nextInt();
    }
}
