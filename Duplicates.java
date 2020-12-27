/*
 * Olivia Bene
 *
 * Computer Science 112, Boston University
 * 
 * 
 * PS 6 Problem 7
 * 
 */

//import java.util.Arrays
import java.util.Arrays;

public class Duplicates 
{

    // remove dups then sort & return num of not zero nums
    public static int removeDups(int[] arr) // already sorted array given
    {

        int len = arr.length;

        for(int i = 0; i < len-1; i++)
        {
            if(arr[i] == arr[i+1]) // look for dups
            {
                arr[i] = 0; // set dup to zero
            }
        }

        //sort the array with zeros on the right 
        Sort.bubbleSort(arr);
       
        //move zeros to the left
        Sort.insertionSort(arr);

        //move zeros to right
        for(int k = 0; k < len; k++)
        {
            for(int l = len -1; l < len/2; l++)
            {
                int temp = arr[k];
                arr[k] = arr[l];
                arr[l] = temp;
            }
        }

        int nonzero = 0;

        for(int j = 0; j <len; j++)
        {
            if(arr[j] != 0)
            {
                nonzero++; // count nums that are NOT zero
            }
        }

        return nonzero;
    }


/*
    public static void main(String[] args) 
    {
    
        int[] arr = {2, 5, 5, 5, 10, 12, 12};
        int ret = removeDups(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(ret);
        //should print: [2, 5, 10, 12, 0, 0, 0]
        // 4
    }
*/


}
