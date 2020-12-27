
/**
 * Sudoku.java
 * 
 * Implementation of a class that represents a Sudoku puzzle and solves
 * it using recursive backtracking.
 *
 * Computer Science 112, Boston University
 *
 * your name: Olivia Bene
 *
 */

import java.io.*;   // allows us to read from a file
import java.util.*;


public class Sudoku 
{    

    private int[][] grid;  // The current contents of the cells of the puzzle. 

    private boolean[][] valIsFixed; //Is it fixed? True = yes
    
   //check if num is in 3x3 section
    private boolean[][][] subgridHasVal;
    
  /////////////////////////////////////////////

    //returns true if row has num already
    private boolean[][] rowCheck;

    //returns true if col has num already
    private boolean[][] colCheck;

    private int numCell;
    

    public Sudoku()  // constructor
    {
        this.grid = new int[9][9];
        this.valIsFixed = new boolean[9][9];     
        
        /* 
         * Note that the third dimension of the following array is 10,
         * because we need to be able to use the possible values 
         * (1 through 9) as indices.
         */
        this.subgridHasVal = new boolean[3][3][10];   

        this.rowCheck = new boolean[9][10];
        this.colCheck = new boolean[9][10];

        this.numCell = 9*9 ; 

    
    }
    
  
    public void placeVal(int val, int row, int col) 
    {
        this.grid[row][col] = val;
        this.subgridHasVal[row/3][col/3][val] = true;

        //this.valIsFixed[row][col] = false;                  
        this.rowCheck[row][val] = true;
        this.colCheck[col][val] = true;

        //System.out.println("Hi from placeVal");
    }
        

    public void removeVal(int val, int row, int col) 
    {
        this.grid[row][col] = 0;
        this.subgridHasVal[row/3][col/3][val] = false;
        
        this.valIsFixed[row][col] = false;                  
        this.rowCheck[row][val] = false;
        this.colCheck[col][val] = false;
        //System.out.println("Hi from removeVal");
    }  
        
    //Don't touch
    public void readConfig(Scanner input) 
    {
        for (int r = 0; r < 9; r++) 
        {
            for (int c = 0; c < 9; c++) 
            {
                int val = input.nextInt();
                this.placeVal(val, r, c);
                if (val != 0) 
                {
                    this.valIsFixed[r][c] = true;
                }
            }
            input.nextLine();
        }
    }
                
   // dont touch
    public void printGrid() 
    {
        for (int r = 0; r < 9; r++) 
        {
            printRowSeparator();
            for (int c = 0; c < 9; c++) 
            {

                System.out.print("|");
                if (this.grid[r][c] == 0) 
                {

                    System.out.print("   ");
                } else 
                {
                    System.out.print(" " + this.grid[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        printRowSeparator();
    }
        
    // A private helper method used by display() 
    // to print a line separating two rows of the puzzle.
    private static void printRowSeparator() 
    {
        for (int i = 0; i < 9; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }


    private boolean isVal(int row, int col, int value) //can I place this num?
    {
        //System.out.println("Hi from isVal. Value is: " + value);
        //System.out.println("Hi from isVal. row is: " + row);
        //System.out.println("Hi from isVal. col is: " + col);
        return 
        (
            !this.rowCheck[row][value] 
            && !this.colCheck[col][value] 
            && !this.subgridHasVal[row/3][col/3][value]
            && !this.valIsFixed[row][col]
            && this.grid[row][col] == 0
        );
    }

    public void test()
    {
        System.out.println(this.isVal(2,6,4)); //not valid
        System.out.println(this.isVal(0,1,3)); // not valid
        System.out.println(this.isVal(0,3,2)); // valid
    }
         
    /*
     * This is the key recursive-backtracking method.  Returns true if
     * a solution has already been found, and false otherwise.
     * 
     * Each invocation of the method is responsible for finding the
     * value of a single cell of the puzzle. The parameter n
     * is the number of the cell that a given invocation of the method
     * is responsible for. We recommend that you consider the cells
     * one row at a time, from top to bottom and left to right,
     * which means that they would be numbered as follows:
     *
     *     0  1  2  3  4  5  6  7  8
     *     9 10 11 12 13 14 15 16 17
     *    18 ...
     */
    private boolean solveRB(int n) 
    {
        //start with basecase
        if(n == numCell) // -1?
        {
            this.printGrid();
            return true;
        }

        int row;
        int col;

        if(n == 0)
        {
            row = 0;
            col = 0;
        }
        else if(n < 9)
        {
            row = 0;
            col = n % 9;
        }
        else
        {
            row = n % 9; // sus
            col = n / 9;
        }
/*
        System.out.println("n currently is: " + n);
        System.out.println("Row is: " + row);
        System.out.println("Col is " + col);
*/
        for(int value = 1; value < 10; value++)
        {
            if(this.isVal(row, col, value)) //get row & col  //feeding wrong col
            {
               // System.out.println("n before place is: " + n);
                this.placeVal(value, row, col);
                //System.out.println("PLACED!");
                //this.printGrid();
                //System.out.println("n after place is: " + n);
                if(this.solveRB(n + 1)) // this is messing w n if it's false it won't skip past it
                {
                    return true;
                }

                this.removeVal(value, row, col);
                //this.printGrid();
               // System.out.println("BYEEEEE");
            }
            
        }

        return false;

    } 
    
  
    public boolean solve() 
    {

        boolean foundSol = this.solveRB(0);
        return foundSol;
    }
    
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        Sudoku puzzle = new Sudoku();
        
        System.out.print("Enter the name of the puzzle file: ");
        String filename = scan.nextLine();
        
        try 
        {
            Scanner input = new Scanner(new File(filename));
            puzzle.readConfig(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + filename);
            System.out.println(e);
            System.exit(1);
        }
        
        System.out.println();
        System.out.println("Here is the initial puzzle: ");
        puzzle.printGrid();
        System.out.println();

        //puzzle.test();   // test
        
        if (puzzle.solve()) 
        {
            System.out.println("Here is the solution: ");
        } else {
            System.out.println("No solution could be found.");
            System.out.println("Here is the current state of the puzzle:");
        }
        puzzle.printGrid();  
    }    
}
