package lasers.ptui;

import lasers.model.Safe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LasersPTUI {

    //reads a file
    public static String[][] readNormalFile(File file) throws FileNotFoundException {
        int lines = 0;
        Scanner in = new Scanner(file);
        String first = in.nextLine();
        //splits the file
        String[] dim = first.split(" ");
        //creates rows and columns from each line
        int rows = Integer.parseInt(dim[0]);
        int columns = Integer.parseInt(dim[1]);
        //adds the rows and columns to an array
        String[][] safeArray = new String[rows][columns];
        //does the same while the file still has lines
        while (in.hasNextLine()) {
            String line = in.nextLine();
            lines++;
            String[] parts = line.split(" ");
            if (parts[0].isEmpty()) {
                break;
            }
            for (int i = 0; i < columns; i++) {
                safeArray[lines - 1][i] = parts[i];
            }
        }
        //returns the safe in array form
        return safeArray;
    }

    //reads the user input
    public static void UserInput(Safe safe){
        Scanner userInput = new Scanner(System.in);
        //prompt the user
        System.out.print("> ");
        String inputLine = userInput.nextLine();
        String[] inputSplit = inputLine.split(" ");
        //runs as long as the user doesn't quit the program
        while (inputSplit[0].charAt(0) != 'q') {
            //checks if the user put add
            if (inputSplit[0].charAt(0) == 'a') {
                if (inputSplit.length != 3){
                    System.out.println("Incorrect coordinates");
                }
                else {
                    //adds laser
                    safe.safeAdd(Integer.parseInt(inputSplit[1]), Integer.parseInt(inputSplit[2]));
                    System.out.println("Laser added at: (" + inputSplit[1] + ", " + inputSplit[2] + ")");
                }
            }
            //checks if the user put remove
            else if (inputSplit[0].charAt(0) == 'r') {
                if (inputSplit.length != 3){
                    System.out.println("Incorrect coordinates");
                }
                else {
                    //removes laser
                    safe.safeRemove(Integer.parseInt(inputSplit[1]), Integer.parseInt(inputSplit[2]));
                    System.out.println("Laser removed at: (" + inputSplit[1] + ", " + inputSplit[2] + ")");
                }
            }
            //checks if the user put verify
            else if (inputSplit[0].charAt(0) == 'v') {
                //verifies the safe
                safe.safeVerify();

            }
            //checks if the user put display
            else if (inputSplit[0].charAt(0) == 'd') {
                //prints the safe
                safe.safeDisplay();
            }
            //checks if the user put help
            else if (inputSplit[0].charAt(0) == 'h') {
                //prints the help message
                System.out.println("a|add r c: Add laser to (r,c) \nd|display: Display safe \nh|help: Print this help message \nq|quit: Exit program \nr|remove r c: Remove laser from (r,c) \nv|verify: Verify safe correctness");
            } else {
                System.out.println("Invalid input: " + inputLine);
            }
            System.out.print("> ");
            inputLine = userInput.nextLine();
            inputSplit = inputLine.split(" ");
        }

    }
    /**
     * The main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // check sanity of input
        try {
            if (args.length < 1 || args.length > 2) {
                System.out.println("Usage: java LasersPTUI safe-file [input]");
            } else {
                //runs if there is only the file to create the safe
                if (args.length == 1) {
                    File file = new File(args[0]);
                    String[][] safeArray = readNormalFile(file);
                    //creates a new safe with the array
                    Safe safe = new Safe(safeArray);
                    //displays safe
                    safe.safeDisplay();
                    //runs user input
                    UserInput(safe);
                }
                //runs if there is a file with input
                else {
                    File file = new File(args[0]);
                    String[][] safeArray = readNormalFile(file);
                    File file2 = new File(args[1]);
                    Scanner in = new Scanner(file2);
                    Safe safe = new Safe(safeArray);
                    while (in.hasNextLine()) {
                        String first = in.nextLine();
                        //splits the file
                        String[] com = first.split(" ");
                        safe.safeDisplay();
                        //reads and prints out what the input file says
                        System.out.print("> ");
                            if (com[0].charAt(0) == 'a') {
                                if (com.length != 3){
                                    System.out.println("Incorrect coordinates");
                                }
                                //adds a laser
                                else {
                                    System.out.println("add " + com[1] + " " + com[2]);
                                    safe.safeAdd(Integer.parseInt(com[1]), Integer.parseInt(com[2]));
                                    System.out.println("Laser added at: (" + com[1] + ", " + com[2] + ")");
                                }
                            }
                            //removes a laser
                            else if (com[0].charAt(0) == 'r') {
                                if (com.length != 3){
                                    System.out.println("Incorrect coordinates");
                                }
                                else {
                                    System.out.println("remove " + com[1] + " " + com[2]);
                                    safe.safeRemove(Integer.parseInt(com[1]), Integer.parseInt(com[2]));
                                    System.out.println("Laser removed at: (" + com[1] + ", " + com[2] + ")");
                                }
                            }
                            //verifies the safe
                            else if (com[0].charAt(0) == 'v') {
                                System.out.println("verify");
                                safe.safeVerify();
                            }
                            //displays the safe
                            else if (com[0].charAt(0) == 'd') {
                                System.out.println("display");
                                safe.safeDisplay();
                            }
                            //prints the help statement
                            else if (com[0].charAt(0) == 'h') {
                                System.out.println("help");
                                System.out.println("a|add r c: Add laser to (r,c) \nd|display: Display safe \nh|help: Print this help message \nq|quit: Exit program \nr|remove r c: Remove laser from (r,c) \nv|verify: Verify safe correctness");
                            } else {
                                System.out.println("Invalid input: " + first);
                            }
                    }
                    //runs the user input
                    UserInput(safe);
                }
            }
        }
        catch (FileNotFoundException f){
            System.out.println("File not found");
        }
    }
}