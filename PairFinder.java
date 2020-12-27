/*
 * Olivia Bene
 *
 * Computer Science 112, Boston University
 * 
 * 
 * PS 6 Problem 8
 * 
 */

public class PairFinder 
{

    public static void findPairSums(int k, int[] arr) // find all pairs of values in the array (if any) that sum to a given integer k using 0(n^2) steps
    {
        int len = arr.length;

        //put array in order
        Sort.selectionSort(arr);

        //loop through array to find pairs
        for(int i = 0; i < len/2; i++)
        {
            for(int j = 0; j < len; j++)
            {
                if((arr[i] + arr[j]) == k )
                {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k ); //print the pair
                }
            }
        }

    }


    public static void findPairSumsFaster(int k, int[] arr)// find all pairs of values in the array (if any) that sum to a given integer k using 0(nlogn) steps
    {
        int len = arr.length;

        //order array
        Sort.mergeSort(arr);

        for(int i = 0; i < len/2; i++)
        {
            for(int j = 0; j < len; j++)
            {
                if((arr[i] + arr[j]) == k )
                {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k ); //print the pair
                }
            }
        }    
    }
    
    public static void main(String[] args) 
    {
        /*
        int k = 12;
        int[] arr = {10, 4, 7, 7, 8, 5, 15};
        findPairSums(k, arr);


        int[] arr1 = {10, 4, 7, 7, 8, 5, 15, 30, 10, 2, 0, 18};
        findPairSumsFaster(k, arr1);
        */
    }
}
