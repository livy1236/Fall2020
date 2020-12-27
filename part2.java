import java.util.*;
package Programs;

public class part2 
{
    
    public static void mystery(int[] arr) 
    {
    for (int i = 0; i < arr.length / 2; i++) 
    {
        int n = arr.length - 1 - i;
        int val = arr[n];
        arr[n] = arr[i];
        arr[i] = val;
        // What values do the variables have here,
        // for each value of the loop variable `i`?
    }

    int[] values = {0, 1, 2, 3, 4, 5, 6, 7};
    mystery(values);
    System.out.println("new array: " + Arrays.toString(values));

}





}
