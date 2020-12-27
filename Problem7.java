import sun.awt.windows.WLightweightFramePeer;

/*
 * Problem7.java
 * 
 * A method for determining whether the complete tree represented by an array 
 * is a heap(interior >= children).
 */ 

public class Problem7 {
    public static <T extends Comparable<T>> boolean isHeap(T[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        
        return isHeapTree(arr, 0);
    }
    
    private static <T extends Comparable<T>> boolean isHeapTree(T[] arr, int i) 
    {
        //basecase
        if((2*i + 2) < arr.length)
        {
            return true;
        }

        //split the sides
        //right
        boolean right = false;
        if(2*i +2 != arr.length) //stay in bounds of array
        {
            if((arr[i].compareTo(arr[2*i+2]) >= 0 && isHeapTree(arr,(2*i+2))))
            {
                right = true;
            }
        }
        
        
        boolean left = false;
        if((arr[i].compareTo(arr[2*i+1]) >= 0 && isHeapTree(arr,(2*i+1))))
        {
            left = true;
        }


        if(left && right)
        {
            return true;
        }
                
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("--- testing isHeapTree() ---");
        System.out.println();
        System.out.println("(0) an array of integers that is a heap");
        try {
            // Note that we need to use the wrapper class (Integer)
            // instead of the primitive type (int).
            Integer[] arr1 = {50, 30, 35, 25, 23, 13, 18, 8, 20, 16};
            boolean results = isHeap(arr1);
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        System.out.println();
        System.out.println("(1) an array of integers that is a heap");
        try {
            // Note that we need to use the wrapper class (Integer)
            // instead of the primitive type (int).
            Integer[] arr2 = {10, 8, 5, 3, 2};
            boolean results = isHeap(arr2);
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        
        System.out.println();
        System.out.println("(2) an array of integers that is not a heap");
        try {
            // Note that we need to use the wrapper class (Integer)
            // instead of the primitive type (int).
            Integer[] arr3 = {12, 42};
            boolean results = isHeap(arr3);
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(false);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results != true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
    }
}
