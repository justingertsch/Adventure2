/**
 * Created by Justin Gertsch on 9/27/14.
 * Adventure1
 * CS 3250
 * A class that represents a simple text adventure game.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Adventure
{
    final int MIN_ROW = 0;
    final int MAX_ROW = 4;
    final int NO_CHANGE = 0;
    final int NORTH = -1;
    final int SOUTH = 1;
    final int EAST = 1;
    final int WEST = -1;
    int eastWest = 0;
    int northSouth = 0;
    Scanner inputScanner = new Scanner(System.in);
    String[] inventory = {"brass lantern","rope","rations","staff"};
    String[] cmds;
    Map m = null;
    GameChar gc = null;

    public void Adventure(Map m, GameChar gc)
    {
        this.m = m;
        this.gc = gc;
    }

    public static void main(String[] args)
    {
        Adventure adv = new Adventure();
        do
        {
            System.out.print("> ");
            String input = adv.inputScanner.nextLine();
            adv.cmds = input.trim().split(" +");

            if(adv.cmds[0].length() < 1)
            {
                adv.cmds[0] = adv.cmds[0] + " ";
            }

            // Switch statement to process commands from the user
            switch (adv.cmds[0].substring(0,1).toLowerCase())
            {
                case "g":
                    if(adv.cmds.length == 1)
                    {
                        adv.cmds = Arrays.copyOf(adv.cmds, 2);
                        adv.cmds[1] = " ";
                    }

                    if(adv.cmds[1].length() < 1)
                    {
                        adv.cmds[1] = adv.cmds[1] + " ";
                    }

                    adv.go(adv.cmds[1].substring(0, 1).toLowerCase());
                    break;

                case "i":

                    adv.showInventory();
                    break;

                case "q":
                    System.out.println("Farewell. Thanks for playing");
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }
            adv.printLocation();

        }while(!adv.cmds[0].substring(0,1).toLowerCase().equals("q"));
    }

    // go method
    public void go(String direction)
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
    public void showInventory()
    {
        System.out.println("You are carrying:");
        for (String item : inventory)
            System.out.println(item);
    }

    // print location method
    public void printLocation()
    {
        System.out.println("You are at location "+ northSouth+","+eastWest);
    }

    // private methods to be used by go
    private boolean moveAllowed(int ns, int ew)
    {
        if(ns != NO_CHANGE)
        {
            return( (northSouth + ns) <= MAX_ROW && (northSouth + ns) >= MIN_ROW );
        }
        else
        {
            return ( (eastWest + ew) <= MAX_ROW && (eastWest + ew) >= MIN_ROW );
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
