/*
 * Student First Name: Olivia
 * Student Last Name: Bene
 * Student BU Number: U13064826
 * Purpose: PS 4 Q1
 */

public class Set  {
    private static final int SIZE = 10; // default size of initial set
                                
    private int[] set;      // array referece to the set
    private int size;    // current size of the set
    private int next;       // index to next available slot in the set array
    
    
    public Set() { //constructor
        this.size = size;
        this.set = set;
        this.next = next;
    }
    
    public Set(int[] A) 
    {
        this.size = A.length;
        this.set = A;
        this.next = A.length;

        int tempArr[] = new int[SIZE];
        int temp = 0;
        Set tempSet = new Set();
        tempSet.set = this.set;
        
        //fill temp array
        for(int i = 0; i < A.length; i++)
        {
            tempArr[i] = A[i];
        }


        //order Array
        for(int x = 0; x < tempArr.length-1; x++) 
        {
            for(int j = x + 1; j < tempArr.length-1; j++) 
            { 
                if (tempArr[x] > tempArr[j]) 
                {
                    temp = tempArr[x];
                    tempArr[x] = tempArr[j];
                    tempArr[j] = temp;
                }
            }
        }

        //check & skip dupicates
        for(int a = 0; a < tempArr.length; a++)
        {
          tempSet.insert(tempArr[a]);
        }

        this.size = tempSet.set.length - 1;
        this.set = tempSet.set;
        this.next = tempSet.size -1;

        
    }
    
    public Set clone() 
    {
        Set copy = new Set();
        copy.size = size;
        copy.next = next;
        copy.set = set;

        return copy;     
    }
    
    // This method reallocates the array set to twice as big and copies all the elements over.
    // It is called only by insert.
    //
    // Note that this is the reason that in this class
    // the member size is not a class variable (i.e. static)
    // and it is not final, because the set can grow and size
    // will be modified accordingly.
    
    private void resize() {
        size *= 2;
        int[] temp = new int[size];
        for(int i = 0; i < set.length; ++i) {
            temp[i] = set[i];
        }
        set = temp;
    }
        
    public  String toString()  {
        StringBuilder str = new StringBuilder();
        int length = this.size;

        if(this.size == 0)
        {
            str.append("[]");
            return str.toString();
        }

        str.append("[");
        for(int i = 0; i < length-2; i++)
        {
            str.append(this.set[i] + "");
            str.append(", ");
        }
        str.append(this.set[length-1]);
        str.append("]");


        return str.toString();  
    } 
     
    public int cardinality() {
        int num = 0;

        for(int i = 0; i < this.size; i++)
        {
          String set1 = this.set[i] + "";
            if(set1 != "")
            {
                num ++;
            }
        }

        return num;
    }
    
    public  boolean isEmpty() 
    {
        boolean status = false;
        if(this.size == 0 || this.set == null)
        {
            status = true;
        }
        return status;
    }
      
    public boolean member(int k) 
    {
        boolean stat = false;
        for(int i = 0; i < this.size; i++)
        {
            if(this.set[i] == k)
            {
                stat = true;
            }
        }
        return stat;   
    }    
   
    public  boolean subset(Set T) 
    {
        int total = 0;
        boolean status = false;

        for(int i = 0; i < T.size; i++)
        {
            if(this.member(T.set[i]))
            {
                total++;
            }
        }

        if(total == T.size)
        {
            status = true;
        }

        return status;  
    }
    
    public  boolean equal(Set T) 
    {
        boolean status = false;

        if(this.size == T.size && this.subset(T))
        {
            status = true;
        }
        return status;   
    }
       
    public void insert(int k) 
    {
      Set tempSet = new Set();
      tempSet = this.clone();
      tempSet.size = tempSet.size +1;
      

      boolean stat = false;
      for(int i = 0; i < this.size; i++)
      {
          if(tempSet.set[i] == k)
          {
              stat = true;
          }
      }
      if(stat)
      {
          return;
      }

      tempSet.set[next] = k;
      tempSet.size = tempSet.set.length;

      for(int x = 0; x < tempSet.size -1; x++)
      {
        this.set[x] = tempSet.set[x];
      }
  
    }
    
    public void delete(int k) 
    {
        if(!(this.member(k)))
        {
            return;
        }

        Set tempSet = new Set();
        tempSet.set = new int[this.size];
        int index = 0;

        for(int i = 0; i< this.size; i++)
        {
            if(this.set[i] != k)
            {
                tempSet.set[i] = this.set[i];
                index++;
            }
        }

        for(int x = 0; x < this.set.length; x++)
        {
            this.set[x] = tempSet.set[x];
        }
    }
  
    public Set union(Set T) 
    {
        Set newSet = new Set();
        newSet = T.clone();

        for(int i = 0; i < this.size;i++)
        {
            newSet.insert(this.set[i]);
        }

        return newSet;  
    }
   
    public Set intersection(Set T) 
    {
        Set newSet = new Set();
        newSet.set= new int[this.size];
        int index = 0;

        for(int i = 0; i <this.set.length; i++)
        {
            if(T.member(this.set[i]))
            {
                newSet.set[index] = this.set[i];
                index++;
            }
        }

        return newSet;   
    }
    
    public Set setdifference(Set T) 
    {
        Set newSet = new Set();
        int index = 0;
        newSet.set= new int[this.size];

        for(int i = 0; i < this.size - 1; i++)
        {
            if(!(T.member(this.set[i])))
            {
                newSet.set[index] = this.set[i];
                index++;
            }
        }

        return newSet;   
    }
      
}
