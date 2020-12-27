/*
 * Olivia Bene
 *
 * Computer Science 112, Boston University
 * 
 * 
 * PS 6 Problem 9
 * 
 */
import java.util.Arrays;

public class MergeIntersect 
{
    

    public static int [] intersect(int[]arr1, int[] arr2)
    {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int newLen;

        if(len1 > len2) //find new array size
        {
            newLen = len2;
        }
        else
        {
            newLen = len1;
        }

        int [] finalArr = new int[newLen]; //make new array

        //sort arrays
        Sort.bubbleSort(arr1);
        Sort.bubbleSort(arr2);


        //find the crosses between the arrays
        mergeHelp(arr1, arr2, 0, newLen-1); //-1?


        return  mergeHelp(arr1, arr2, 0, newLen-1);
    }

    private static int [] mergeHelp(int [] arr1, int [] arr2, int zero, int last ) // clean up main function
    {
        if (zero >= last) //base case
        { 
            return arr2;
        }

        int mid = (zero + last)/2;

        //divide up the array in reciursion
        mergeHelp(arr1, arr2, zero, mid);
        mergeHelp(arr1, arr2, mid + 1, last);


        //heavy lifting anf actually compairing nums
        return collectInts(arr1, arr2, zero, mid, mid+1, last);
        //System.out.println( "1 " + Arrays.toString(arr1) );
        //System.out.println( "2 " + Arrays.toString(arr2) );
    }

    private static int [] collectInts(int[] arr1, int[] arr2, 
    int left1, int leftStop, int right1, int rightStop)
    {
        //find starting points for arrays
        int a = left1;    
        int b = right1;   
        int c = left1;    
        
        int [] newArr = new int [arr1.length];

        while (a <= leftStop && b <= rightStop) 
        {
            if (arr1[a] == arr2[a] && arr1[a] != arr1[c] ) 
            {
                newArr[a] = arr2[a];
                a++;
                c++;
            } 
            else 
            {
                b++;
                c++;
            }
        }
        
        while (a <= leftStop) 
        {
            a++; 
            c++;
        }

        return arr2;
    }


    public static void main(String[] args) 
    {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result = MergeIntersect.intersect(a1, a2);
        System.out.println(Arrays.toString( result) ); //[5, 7, 9, 10, 0, 0]

        System.out.println("Now #2");

        int[] a11 = {0, 2, -4, 6, 10, 8};
        int[] a21 = {12, 0, -4, 8};
        int[] result1 = MergeIntersect.intersect(a11, a21);
        System.out.println( Arrays.toString(result1) ); // [-4, 0, 8, 0]
    }

}
