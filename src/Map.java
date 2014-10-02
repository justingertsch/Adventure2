import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Justin on 10/1/2014.
 */
public class Map
{
    protected final int MAX_ROW;
    protected final int MAX_COL;
    protected final int MIN_ROW = 0;
    protected final int MIN_COL = 0;
    protected char[][] map = null;
    private final char OUT_OF_BOUNDS = 'X';

    public void Map(String f)
    {

        try
        {
            Scanner in  = new Scanner(new File(f));

            String line = in.nextLine();

            String[] coords = line.split(" +");
            if(isNumeric(coords[0]) && isNumeric(coords[1]))
            {
                this.MAX_ROW = Integer.parseInt(coords[0] + 1);
                this.MAX_COL = Integer.parseInt(coords[1] + 1);
                this.map = new char[MAX_ROW][MAX_COL];
                // populate out of bounds border
                for( int i = MIN_COL; i <= MAX_COL; i++)
                {
                    map[MIN_ROW][i] = OUT_OF_BOUNDS;
                    map[MAX_ROW][i] = OUT_OF_BOUNDS;
                }
                for( int i = MIN_COL + 1; i < MAX_ROW; i++)
                {
                    map[i][MIN_COL] = OUT_OF_BOUNDS;
                    map[i][MAX_COL] = OUT_OF_BOUNDS;
                }

                // populate terrain
                for(int i = MAX_ROW + 1; i < MAX_ROW; i++)
                {
                    char[] ter = in.nextLine().trim().toCharArray();
                    int charArrayIncr = 0;
                    for(int j = MAX_COL + 1; j < MAX_COL; j++)
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

    public char[][] getTerrain(int row, int col, int sight)
    {
        //implement this
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
