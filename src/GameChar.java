/**
 * Created by Justin on 10/1/2014.
 */
public class GameChar
{

    private final int NO_CHANGE = 0;
    private final int NORTH = -1;
    private final int SOUTH = 1;
    private final int EAST = 1;
    private final int WEST = -1;
    private int eastWest = 0;
    private int northSouth = 0;
    private String[] inventory = {"brass lantern","rope","rations","staff"};
    private Map m = null;
    private int sight = 1;



    public GameChar(Map m)
    {
        this.m = m;
    }


    // go method
    protected void go(String direction)
    {
        switch(direction)
        {
            case "n":
                makeMove(NORTH,NO_CHANGE,"north");
                break;

            case "s":
                makeMove(SOUTH,NO_CHANGE,"south");
                break;

            case "e":
                makeMove(NO_CHANGE,EAST,"east");
                break;

            case "w":
                makeMove(NO_CHANGE,WEST,"west");
                break;

            default:
                System.out.println("You can't go that way.");
                break;
        }

    }

    // inventory method
    protected void showInventory()
    {
        System.out.println("You are carrying:");
        for (String item : inventory)
            System.out.println(item);
    }

    // print location method
    protected void printLocation()
    {
        System.out.println("You are at location "+ northSouth+","+eastWest);
        printTerrain();
    }

    private void printTerrain()
    {
        this.m.printTerrain(this.northSouth, this.eastWest, this.sight);
    }
    // private methods to be used by go
    private boolean moveAllowed(int ns, int ew)
    {
        if(ns != NO_CHANGE)
        {
            return( (northSouth + ns) <= m.MAX_ROW && (northSouth + ns) >= m.MIN_ROW );
        }
        else
        {
            return ( (eastWest + ew) <= m.MAX_COL && (eastWest + ew) >= m.MIN_COL );
        }
    }

    private void makeMove(int ns, int ew, String direction)
    {
        if(moveAllowed(ns,ew))
        {
            System.out.println("Moving "+direction+"...");
            northSouth += ns;
            eastWest += ew;
        }
        else
        {
            System.out.println("You can't go that far "+direction+".");
        }

    }

}
