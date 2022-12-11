package com.nighthawk.spring_portfolio.mvc.lightboard;

public class FRQLightBoard
{
 /** The lights on the board, where true represents on and false represents off.
 */
 private boolean[][] lights;
 /** Constructs a LightBoard object having numRows rows and numCols columns.
 * Precondition: numRows > 0, numCols > 0
 * Postcondition: each light has a 40% probability of being set to on.
 */
    public FRQLightBoard(int numRows, int numCols)
    { 
        lights = new boolean [numRows][numCols];

        for(int i=0; i < lights.length; i++ ){
            for (int j = 0; j < lights[i].length; j++ ){
                if (Math.random()<= 0.4){
                    lights[i][j] = true;
                }
                else {
                    lights[i][j] = false;
                }
            }
        }
    }
    /** Evaluates a light in row index row and column index col and returns a status
     * as described in part (b).
     * Precondition: row and col are valid indexes in lights.
     */
    public boolean evaluateLight(int row, int col) { 
        int count = 0;
        for (int i = 0; i < lights.length; i++){
            if (lights[i][col] == true)
                count ++;
        }

        if (lights[row][col] == true && count % 2 == 0)
            return false;
        if (lights[row][col] == false && count % 3 == 0)
            return true;
        return lights [row][col];
    }

    public String toString()
    {
        String outString = "";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            outString += "[ ";
            for (int col = 0; col < lights[row].length; col++) {
                outString += lights[row][col] + " ";
            }
            outString += "]\n";
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1);
		return outString;
    }

    static public void main(String[] args) {
        // create and display LightBoard
        FRQLightBoard lightBoard = new FRQLightBoard(5, 5);
        System.out.println(lightBoard.toString());
        System.out.println("Evaluate Result: " + lightBoard.evaluateLight (0,0));
    }}
    
 // There may be additional instance variables, constructors, and methods not shown.


