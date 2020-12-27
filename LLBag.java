/*
PSet 8 Part 2
Olivia Bene
livbene@bu.edu
CS112

*/



import java.util.*;

public class LLBag extends LLList implements Bag
{
    private LLList items;
    
     /* 
     * The number of items in the bag.
     */
    private int numItems;
    
    private class Node 
    {
        private Object item;
        private Node next;
        
        private Node(Object i, Node n) 
        {
            item = i;
            next = n;
        }
    }

    private Node head;     // dummy head node
    private int length;    // # of items in the list

    /*
     * Constructor with no parameters - creates a new, empty LLBag with 
     * the default maximum size.
     */
    public LLBag() 
    {
        items = new LLList();
        numItems = 0;
        length = 0;
    }

    public LLBag(Object[] nums) 
    {
       items = new LLList(nums);
       numItems = nums.length-1;
       length = nums.length;
    }

    /* 
     * addItem - adds the specified item at end of the list,
     * Always returns true, because the list is never full.
     */

    public boolean add(Object item)
    {
        items.addItem(item, length);
        numItems += 1;

        return true;
    }
    
    /** 
     * removes one occurrence of the specified item (if any) from the
     * Bag.  Returns true on success and false if the specified item
     * (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item)
    {
        int found = -1;
        for(int i = 0; i < length; i++)
        {
            if(items.getItem(i) == item)
            {
                found = i;
            }

        }
        if(found != -1)
        {
            items.removeItem(found);
            numItems -=1;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * returns true if the specified item is in the Bag, and false
     * otherwise.
     */
    public boolean contains(Object item)
    {
        for(int i = 0; i < length; i++)
        {
            if(items.getItem(i)== item)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * returns the number of items in the Bag.
     */
    public int numItems()
    {
        return length-1;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab()
    {
        if (numItems == 0) 
        {
            throw new IllegalStateException("the bag is empty");
        }

        int random = (int)(Math.random() * numItems);

        return items.getItem(random);
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray()
    {
        Object[] arr = new Object[length];

        for(int i = 0; i < numItems(); i++)
        {
            arr[i] = items.getItem(i);
        }
        return arr;
    }



    public String toString() {
    String str = "{";
    
    for (int i = 0; i < numItems; i++) 
    {
        str = str + items.getItem(i);
        if (i != numItems - 1) 
        {
            str += ", ";
        }
    }
    
    str = str + "}";
    return str;
    }

}
