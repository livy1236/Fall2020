package Submitted.Labs;

public class MyMethods 
{
    public static void printDecreasing(String s)
    {
        int length = 0;
        length = s.length();

        for (int i = length; i > 0; i--)
        {
            System.out.println(s.substring(0,i));
        }


    }

}
