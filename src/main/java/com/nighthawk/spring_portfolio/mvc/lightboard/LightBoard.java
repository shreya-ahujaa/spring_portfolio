package com.nighthawk.spring_portfolio.mvc.lightboard;
import java.util.Scanner;


import lombok.Data;

@Data  // Annotations to simplify writing code (ie constructors, setters)
public class LightBoard {
    private Light[][] lights;

    /* Initialize LightBoard and Lights */
    public LightBoard(int numRows, int numCols) {
        this.lights = new Light[numRows][numCols];
        // 2D array nested loops, used for initialization
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                lights[row][col] = new Light();  // each cell needs to be constructed
            }
        }
    }

    public void setBoardColor(int r, int g, int b) {
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                lights[row][col].setRGB(r, g, b);
            }
        }
    }

    public void randomizeBoard() {
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                lights[row][col].setRandomColor();
            }
        }
    }

    public void setCellColor(int row, int col, int r, int g, int b) {
        lights[row][col].setRGB(r, g, b);
    }

    /* Output is intended for API key/values */
    public String toString() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // data
                "{" + 
                "\"row\": " + row + "," +
                "\"column\": " + col + "," +
                "\"light\": " + lights[row][col] +   // extract toString data
                "}," ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1) + "]";
		return outString;
    }

    /* Output is intended for Terminal, effects added to output */
    public String toTerminal() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // reset
                "\033[m" +
                
                // color
                "\033[38;2;" + 
                lights[row][col].getRed() + ";" +  // set color using getters
                lights[row][col].getGreen() + ";" +
                lights[row][col].getBlue() + ";" +
                lights[row][col].getEffect() + "m" +
                // data, extract custom getters
                "{" +
                "\"" + "RGB\": " + "\"" + lights[row][col].getRGB() + "\"" +
                "," +
                "\"" + "Effect\": " + "\"" + lights[row][col].getEffectTitle() + "\"" +
                "}," +
                // newline
                "\n" ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 2) + "\033[m" + "]";
		return outString;
    }

    /* Output is intended for Terminal, draws color palette */
    public String toColorPalette(int cellWidth, int cellHeight) {


        // Build large string for entire color palette
        String outString = "";
        // find each row
        for (int row = 0; row < lights.length; row++) {
            // repeat each row for block size
            for (int i = 0; i < cellHeight; i++) {
                // find each column
                for (int col = 0; col < lights[row].length; col++) {
                    // repeat each column for block size
                    for (int j = 0; j < cellWidth; j++) {
                        // print single character, except at midpoint print color code
                        String c = (i == (int) (cellHeight / 2) && j == (int) (cellWidth / 2) ) 
                            ? lights[row][col].getRGB()
                            : (j == (int) (cellWidth / 2))  // nested ternary
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : " ";

                        outString += 
                        // reset
                        "\033[m" +
                        
                        // color
                        "\033[38;2;" + 
                        lights[row][col].getRed() + ";" +
                        lights[row][col].getGreen() + ";" +
                        lights[row][col].getBlue() + ";" +
                        "7m" +

                        // color code or blank character
                        c +

                        // reset
                        "\033[m";
                    }
                }
                outString += "\n";
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString += "\033[m";
		return outString;
    }
    
    static public void main(String[] args) {
        // initialize scanner 
        Scanner scanner = new Scanner(System.in);

        // take input
        System.out.print("Enter Cell Width: ");
        int cellWidth = scanner.nextInt();
        System.out.println(cellWidth);

        System.out.print("Enter Cell Height: ");
        int cellHeight = scanner.nextInt();
        System.out.println(cellHeight);
        

        LightBoard lightBoard = new LightBoard(5, 5);
        System.out.println(lightBoard);  // use toString() method
        System.out.println(lightBoard.toTerminal());
        System.out.println(lightBoard.toColorPalette(cellWidth, cellHeight));

        System.out.print("Choose Color of Board (Enter in Format - r g b): ");
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();

        lightBoard.setBoardColor(r, g, b);
        System.out.println(lightBoard.toColorPalette(cellWidth, cellHeight));


        System.out.print("Randomize Board Colors Again? (0 = no / 1 = yes): ");
        int answer = scanner.nextInt();
        if (answer == 1){
            lightBoard.randomizeBoard();
            System.out.println(lightBoard.toColorPalette(cellWidth, cellHeight));
        }
        else 
            System.out.println("Bye!");

        // close scanner
         scanner.close();
    }
}
