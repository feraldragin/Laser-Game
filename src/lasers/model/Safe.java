package lasers.model;

public class Safe {

    private String[][] safeArray;

    //constructor
    public Safe(String[][] safeArray){
        this.safeArray = safeArray;
    }

    //adds a laser
    public void safeAdd(int row, int column) {
        //adds if laserCheck() is true and sends error if false
        if (row >= safeArray.length || column >= safeArray[0].length || row < 0 || column < 0){
            System.out.println("Error adding laser at: (" + row + ", " + column + ")");
        }
        else {
            //checks if a laser is able to be added there
            if (laserCheck(row, column)) {
                String laserLetter = "L";
                //adds a laser at the correct coordinates
                this.safeArray[row][column] = laserLetter;
                //creates temporary row and column variables
                int row1 = row;
                int row2 = row;
                int column1 = column;
                int column2 = column;
                //adds a * to the left of the L
                while (row1 >= 1) {
                    row1 -= 1;
                    //makes sure it isn't replacing something already at the coordinates
                    if (laserCheck(row1, column)) {
                        String laserPointer = "*";
                        this.safeArray[row1][column] = laserPointer;
                    } else {
                        break;
                    }
                }
                //adds a * to the right of the the L
                while (row2 < safeArray[0].length - 1) {
                    row2++;
                    //makes sure it isn't replacing something already at the coordinates
                    if (laserCheck(row2, column)) {
                        String laserPointer = "*";
                        this.safeArray[row2][column] = laserPointer;
                    } else {
                        break;
                    }
                }
                //adds a * above the L
                while (column1 >= 1) {
                    column1 -= 1;
                    //makes sure it isn't replacing something already at the coordinates
                    if (laserCheck(row, column1)) {
                        String laserPointer = "*";
                        this.safeArray[row][column1] = laserPointer;
                    } else {
                        break;
                    }
                }
                //adds a * below the L
                while (column2 < safeArray.length - 1) {
                    column2++;
                    //makes sure it isn't replacing something already at the coordinates
                    if (laserCheck(row, column2)) {
                        String laserPointer = "*";
                        this.safeArray[row][column2] = laserPointer;
                    } else {
                        break;
                    }
                }

            }

        }
    }

    //removes the laser
    public void safeRemove(int row, int column){
        //makes sure the coordinates are valid
        if (row >= safeArray.length || column >= safeArray[0].length || row < 0 || column < 0){
            System.out.println("Error removing at: (" + row + ", " + column + ")");
        }
        //makes sure a laser can be removed
        else if (!safeArray[row][column].equals("L")){
            System.out.println("Error removing at: (" + row + ", " + column + ")");
        }
        //replaces the laser and the *s with a .
        else {
            String laserLetter = ".";
            this.safeArray[row][column] = laserLetter;
            //creates temporary row and column variables
            int temprow1 = row;
            int temprow2 = row;
            int tempcolumn1 = column;
            int tempcolumn2 = column;
            //replaces the *s with a .
            while (temprow1 >= 0) {
                String laserPointer = ".";
                //makes sure that there isn't anything being unintentionally removed
                if (!laserCheck(temprow1, column)) {
                    break;
                } else {
                    this.safeArray[temprow1][column] = laserPointer;
                }
                temprow1 -= 1;
            }
            //replaces the *s with a .
            while (temprow2 < safeArray[0].length - 1) {
                String laserPointer = ".";
                //makes sure that there isn't anything being unintentionally removed
                if (!laserCheck(temprow2, column)) {
                    break;
                } else {
                    this.safeArray[temprow2][column] = laserPointer;
                }
                temprow2++;
            }
            //replaces the *s with a .
            while (tempcolumn1 >= 0) {
                String laserPointer = ".";
                //makes sure that there isn't anything being unintentionally removed
                if (!laserCheck(row, tempcolumn1)) {
                    break;
                } else {
                    this.safeArray[row][tempcolumn1] = laserPointer;
                }
                tempcolumn1 -= 1;
            }
            //replaces the *s with a .
            while (tempcolumn2 < safeArray.length - 1) {
                String laserPointer = ".";
                //makes sure that there isn't anything being unintentionally removed
                if (!laserCheck(row, tempcolumn2)) {
                    break;
                } else {
                    this.safeArray[row][tempcolumn2] = laserPointer;
                }
                tempcolumn2++;


            }
            //adds the lasers back of any other laser that might have been affected
            for (int row2 = 0; row2 < safeArray.length; row2++) {
                for (int column2 = 0; column2 < safeArray[row2].length; column2++) {
                    if (safeArray[row2][column2].equals("L")) {
                        this.safeArray[row2][column2] = laserLetter;
                        this.safeAdd(row2, column2);
                    }
                }
            }
        }

    }

    //displays the safe
    public void safeDisplay(){
        //prints the number of columns
        System.out.print("  ");
        for (int column = 0; column < safeArray[0].length; column++){
            System.out.print(column + " ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < (safeArray[0].length*2)-1; i++){
            System.out.print("-");
        }
        System.out.println();
        //prints the number of rows
        for (int row = 0; row < safeArray.length; row++){
            System.out.print((row) + "|");
            //prints the safe
            for (int column = 0; column < safeArray[row].length; column++){
                System.out.print(safeArray[row][column] + " ");
            }
            System.out.println();

        }
    }

    //checks if the safe is correct
    public void safeVerify(){
        forBreak:
        for (int row = 0; row < safeArray.length; row++) {
            for (int column = 0; column < safeArray[row].length; column++) {
                //checks for . and prints an error if there is one
                if (safeArray[row][column].equals(".")){
                    System.out.println("Error verifying at: (" + row + ", "+ column + ")");
                    break forBreak;
                }
                else if (safeArray[row][column].equals("L")){
                    //creates temporary row and column variables
                    int row1 = row;
                    int row2 = row;
                    int column1 = column;
                    int column2 = column;
                    //checks if two lasers cross
                    while (row1 >= 1) {
                        row1 -= 1;
                        //breaks if there's a number
                        if (Character.isDigit(safeArray[row1][column].charAt(0))){
                            break;
                        }
                        else if (safeArray[row1][column].equals("L")) {
                            System.out.println("Error verifying at: (" + row + ", " + column + ")");
                            //breaks the for loop
                            break forBreak;
                        }
                    }
                    //checks if two lasers cross
                    while (row2 < safeArray[0].length - 1) {
                        row2++;
                        if (Character.isDigit(safeArray[row2][column].charAt(0))){
                            break;
                        }
                        else if (safeArray[row2][column].equals("L")) {
                            System.out.println("Error verifying at: (" + row + ", " + column + ")");
                            break forBreak;
                        }
                    }
                    //checks if two lasers cross
                    while (column1 >= 1) {
                        column1 -= 1;
                        if (Character.isDigit(safeArray[row][column1].charAt(0))){
                            break;
                        }
                        else if (safeArray[row][column1].equals("L")) {
                            System.out.println("Error verifying at: (" + row + ", " + column + ")");
                            break forBreak;
                        }
                    }
                    //checks if two lasers cross
                    while (column2 < safeArray.length - 1) {
                        column2++;
                        if (Character.isDigit(safeArray[row][column2].charAt(0))){
                            break;
                        }
                        else if (safeArray[row][column2].equals("L")) {
                            System.out.println("Error verifying at: (" + row + ", " + column + ")");
                            break forBreak;
                        }
                    }
                }
                //checks to make sure the number of lasers around the pillar
                else if(Character.isDigit(safeArray[row][column].charAt(0))) {
                    int laserCount = laserCounter(row, column);

                    if (laserCount != Integer.parseInt(safeArray[row][column])){
                        System.out.println("Error verifying at: (" + row + ", " + column + ")");
                        break forBreak;
                    }
                }
                //safe is verified
                if (column == safeArray[row].length - 1 && row == safeArray.length-1){
                    System.out.println("Safe is fully verified!");
                }
            }
        }
    }

    //checks if a laser can be placed at the coordinates
    public boolean laserCheck(int row, int column){
        //checks if there is a laser at the coordinates
        if (safeArray[row][column].equals("L")){
            return false;
        }
        //checks if there is an X at the coordinates
        else if (safeArray[row][column].equals("X")){
            return false;
        }
        //checks if there is a number at the coordinates
        else if (Character.isDigit(safeArray[row][column].charAt(0))){
            return false;
        }
        else {
            return true;
        }
    }
    //counts the number of lasers around the pillar
    public int laserCounter(int row, int column){
        int laserCount = 0;
        //checks if a laser is to the left or above the pillar in a corner
        if (row + 1 > safeArray.length-1 && column + 1 > safeArray[0].length-1){
            if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the left or below of the pillar in a corner
        else if (row + 1 > safeArray.length-1 && column - 1 <0){
            if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the right or above of the pillar in a corner
        else if (row - 1 < 0 && column + 1 > safeArray[0].length-1){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the right or below of the pillar in a corner
        else if (row - 1 < 0 && column - 1 <0){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the left, above, or below of the pillar
        else if (row + 1 > safeArray.length-1) {

            if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the right, above, or below of the pillar
        else if (row - 1 < 0){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the right, left, or above of the pillar
        else if (column + 1 > safeArray[0].length-1){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
        }
        //checks if a laser is to the right, above, or below of the pillar
        else if (column - 1 <0){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }

        }
        //checks if a laser is around the pillar if the pillar isn't on an edge
        else {
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
        //returns the number of lasers around the pillar
        return laserCount;
    }
}
