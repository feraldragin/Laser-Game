package lasers.model;

public class Safe {

    private String[][] safeArray;

    public Safe(String[][] safeArray){
        this.safeArray = safeArray;
    }

    public void safeAdd(int row, int column) {
        //adds if laserCheck() is true and sends error if false
        if (row >= safeArray.length || column >= safeArray[0].length || row < 0 || column < 0){
            System.out.println("Error adding laser at: (" + row + ", " + column + ")");
        }
        else {
            if (laserCheck(row, column)) {
                String laserLetter = "L";
                this.safeArray[row][column] = laserLetter;
                int row1 = row;
                int row2 = row;
                int column1 = column;
                int column2 = column;
                while (row1 >= 1) {
                    row1 -= 1;
                    if (laserCheck(row1, column)) {
                        String laserPointer = "*";
                        this.safeArray[row1][column] = laserPointer;
                    } else {
                        break;
                    }
                }
                while (row2 < safeArray[0].length - 1) {
                    row2++;
                    if (laserCheck(row2, column)) {
                        String laserPointer = "*";
                        this.safeArray[row2][column] = laserPointer;
                    } else {
                        break;
                    }
                }
                while (column1 >= 1) {
                    column1 -= 1;
                    if (laserCheck(row, column1)) {
                        String laserPointer = "*";
                        this.safeArray[row][column1] = laserPointer;
                    } else {
                        break;
                    }
                }
                while (column2 < safeArray.length - 1) {
                    column2++;
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

    public void safeRemove(int row, int column){
        if (row >= safeArray.length || column >= safeArray[0].length || row < 0 || column < 0){
            System.out.println("Error removing at: (" + row + ", " + column + ")");
        }
        else if (!safeArray[row][column].equals("L")){
            System.out.println("Error removing at: (" + row + ", " + column + ")");
        }
        else {
            String laserLetter = ".";
            this.safeArray[row][column] = laserLetter;
            int temprow1 = row;
            int temprow2 = row;
            int tempcolumn1 = column;
            int tempcolumn2 = column;

            while (temprow1 >= 0) {
                String laserPointer = ".";
                if (!laserCheck(temprow1, column)) {
                    break;
                } else {
                    this.safeArray[temprow1][column] = laserPointer;
                }
                temprow1 -= 1;
            }
            while (temprow2 < safeArray[0].length - 1) {
                String laserPointer = ".";
                if (!laserCheck(temprow2, column)) {
                    break;
                } else {
                    this.safeArray[temprow2][column] = laserPointer;
                }
                temprow2++;
            }
            while (tempcolumn1 >= 0) {
                String laserPointer = ".";
                if (!laserCheck(row, tempcolumn1)) {
                    break;
                } else {
                    this.safeArray[row][tempcolumn1] = laserPointer;
                }
                tempcolumn1 -= 1;
            }
            while (tempcolumn2 < safeArray.length - 1) {
                String laserPointer = ".";
                if (!laserCheck(row, tempcolumn2)) {
                    break;
                } else {
                    this.safeArray[row][tempcolumn2] = laserPointer;
                }
                tempcolumn2++;


            }
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

    public void safeDisplay(){
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
        for (int row = 0; row < safeArray.length; row++){
            System.out.print((row) + "|");
            for (int column = 0; column < safeArray[row].length; column++){
                System.out.print(safeArray[row][column] + " ");
            }
            System.out.println();

        }
    }

    public void safeVerify(){
        forBreak:
        for (int row = 0; row < safeArray.length; row++) {
            for (int column = 0; column < safeArray[row].length; column++) {
                if (safeArray[row][column].equals(".")){
                    System.out.println("Error verifying at: (" + row + ", "+ column + ")");
                    break forBreak;
                }
                else if (safeArray[row][column].equals("L")){
                    int row1 = row;
                    int row2 = row;
                    int column1 = column;
                    int column2 = column;
                    while (row1 >= 1) {
                        row1 -= 1;
                        if (Character.isDigit(safeArray[row1][column].charAt(0))){
                            break;
                        }
                        else if (safeArray[row1][column].equals("L")) {
                            System.out.println("Error verifying at: (" + row + ", " + column + ")");
                            break forBreak;
                        }
                    }
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
                else if(Character.isDigit(safeArray[row][column].charAt(0))) {
                    int laserCount = laserCounter(row, column);

                    if (laserCount != Integer.parseInt(safeArray[row][column])){
                        System.out.println("Error verifying at: (" + row + ", " + column + ")");
                        break forBreak;
                    }
                }
                if (column == safeArray[row].length - 1 && row == safeArray.length-1){
                    System.out.println("Safe is fully verified!");
                }
            }
        }
    }

    public boolean laserCheck(int row, int column){
        //if pillars are good and laser, number, letter, and/or * not in place
        if (safeArray[row][column].equals("L")){
            return false;
        }

        else if (safeArray[row][column].equals("X")){
            return false;
        }

        else if (Character.isDigit(safeArray[row][column].charAt(0))){
            return false;
        }
        else {
            return true;
        }
    }
    public int laserCounter(int row, int column){
        int laserCount = 0;
        if (row + 1 > safeArray.length-1 && column + 1 > safeArray[0].length-1){
            if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
        }
        else if (row + 1 > safeArray.length-1 && column - 1 <0){
            if (safeArray[row-1][column].equals("L")){
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
        else if (row - 1 < 0 && column + 1 > safeArray[0].length-1){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row][column-1].equals("L")){
                laserCount++;
            }
        }
        else if (row - 1 < 0 && column - 1 <0){
            if (safeArray[row + 1][column].equals("L")) {
                laserCount++;
            }
            else if (safeArray[row][column+1].equals("L")){
                laserCount++;
            }
        }
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
        return laserCount;
    }
}
