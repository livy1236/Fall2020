public class ArrayMethods {

     public static final String[] DAYS =
          {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
   
     public static void swapAdjacent(int [] values)
     {
       int length = values.length;
       if(length % 2 != 0)
       {
         length -= 1;
       }
   
       for(int i = 0; i < length; i += 2)
       {
         int throwaway = values[i];
   
         values[i] = values[i+1];
         values[i+1] = throwaway;
       }
     }
    
     public static int[] copyReplace(int[] values, int oldVal, int newVal)
     {
       int [] newArray = {};
       for(int i = 0; i < values.length; i++)
       {
         newArray[i] = values[i];
       }
       for(int i = 0; i < values.length; i++)
       {
         if(newArray[i] == oldVal)
         {
           newArray[i] = newVal;
         }
       }
       return newArray;
     }
   
     public static int maxSorted(int[] values)
     {
       int count = 1;
       int num = values[0];
       if(values.length == 0)
       {
         return 0;
       }
   
       for(int i = 1; i < values.length; i++)
       {
         if(num <= values[i])
         {
           count +=1;
         }
         else
         {
           count = 0;
         }
       }
   
       return count;
     }
   
    public static int getIndexOfDay(String day)
    {
       int index = -1;
       for(int i = 0; i < DAYS.length; i++)
       {
         if(DAYS[i] == day)
         {
           index = i;
         }
       }
      return index;
    }
     
     public static int[] reverseInterchange( int[] arr1, int [] arr2)
     {
       
       int array1 = arr1.length;
       int array2 = arr2.length;
       int array3; // length of new array
       if(array1 >= array2)
       {
         array3 = array2;
       }
       else
       {
         array3 = array1;
       }
       int [] a3 = {};
   
       for(int i = 0; i < array3; i++)
       {
         if(i % 2 == 0)
         {
          a3[i] = arr1[i];
         }
         else
         {
            a3[i] = arr2[array2];
         }

         array2 -=1;
       }
   
       return a3;
     }
   
   
   
   }