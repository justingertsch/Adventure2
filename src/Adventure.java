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

    private Scanner inputScanner = new Scanner(System.in);
    private String[] cmds;

    private GameChar gc = null;

    public Adventure(GameChar gc)
    {
        this.gc = gc;
    }

    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Error: No map file specified. Usage: Adventure mapfile");
            System.exit(0);
        }

        Adventure adv = new Adventure( new GameChar(new Map(args[0])));
        //adv.gc.m.printMap();
        //System.exit(0);
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
                    adv.printLocation();
                    break;

                case "i":

                    adv.showInventory();
                    break;

                case "q":
                    System.out.println("Farewell. Thanks for playing");
                    break;

                default:
                    System.out.println("Invalid command");
                    adv.printLocation();
                    break;
            }


        }while(!adv.cmds[0].substring(0,1).toLowerCase().equals("q"));
    }

    // go method
    public void go(String direction)
    {
        this.gc.go(direction);

    }

    // inventory method
    public void showInventory()
    {
        this.gc.showInventory();
    }

    public void printLocation()
    {
        this.gc.printLocation();
    }


}
