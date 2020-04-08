package lasers.ptui;

import lasers.model.Safe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LasersPTUI {
    /**
     * The main method
     * @param args command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // check sanity of input
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java LasersPTUI safe-file [input]");
        } else {
            // TODO
            if (args.length == 1){
                int lines = 0;
                File file = new File(args[0]);
                Scanner in = new Scanner(file);
                String first = in.nextLine();
                String[] dim = first.split(" ");
                int rows = Integer.parseInt(dim[0]);
                int columns = Integer.parseInt(dim[1]);
                String[][] safeArray = new String[rows][columns];
                while (in.hasNextLine()){
                    String line = in.nextLine();
                    lines++;
                    String[] parts = line.split(" ");
                    if(parts[0].isEmpty()){
                        break;
                    }
                    for(int i = 0; i < columns;i++){
                        safeArray[lines-1][i] = parts[i];
                    }
                }
                Safe safe = new Safe(safeArray);
                safe.safeDisplay();

            }
        }
    }
}