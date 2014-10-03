import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Justin on 10/1/2014.
 */
public class Map
{
    protected  int MAX_ROW;
    protected  int MAX_COL;
    protected final int MIN_ROW = 0;
    protected final int MIN_COL = 0;
    private final int ONE = 1;
    protected char[][] map = null;
    private final char OUT_OF_BOUNDS = 'X';

    public Map(String f)
    {

        try
        {
            Scanner in  = new Scanner(new File(f));

            String line = in.nextLine();

            String[] coords = line.split(" +");
            if(isNumeric(coords[0]) && isNumeric(coords[1]))
            {
                this.MAX_ROW = Integer.parseInt(coords[0]) - this.ONE;
                this.MAX_COL = Integer.parseInt(coords[1]) - this.ONE ;
                this.map = new char[this.MAX_ROW + this.ONE][this.MAX_COL + this.ONE];

                // populate terrain
                for(int i = this.MIN_ROW; i <= this.MAX_ROW; i++)
                {
                    char[] ter = in.nextLine().trim().toCharArray();
                    int charArrayIncr = 0;
                    for(int j = this.MIN_COL; j <= this.MAX_COL; j++)
                    {
                        this.map[i][j] = ter[charArrayIncr];
                        charArrayIncr++;
                    }
                }
            }
            else
            {
                System.out.println("Invalid Map file.");
                System.exit(1);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Map file not found.");
            System.exit(1);
        }
    }


    protected void printTerrain(int row, int col, int sight)
    {
        for(char[] terRow: getTerrain(row, col, sight))
            printRow(terRow);
        System.out.println();
    }

    private void printRow(char[] row)
    {
        for(char c: row)
            System.out.print(c);

        System.out.println();
    }

    private char[][] getTerrain(int row, int col, int sight)
    {
        // calculate sightArea
        int sightArea = (2 * sight) + this.ONE;
        char[][] terrain = new char[sightArea][sightArea];

        // get starting position from current position
        row -= sight;
        col -= sight;
        for(int i = 0; i < sightArea; i++)
        {
            for(int j = 0; j < sightArea; j++)
            {
                terrain[i][j] = getChar(row,col);
                col++;
            }
            col = col - sightArea;
            row++;
        }
        return terrain;
    }

    private char getChar(int row, int col)
    {
        try
        {
            char c = this.map[row][col];
            return c;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return this.OUT_OF_BOUNDS;
        }
    }

    private boolean isNumeric(String s)
    {
        try
        {
            Double d = Double.parseDouble(s);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;

    }
}
