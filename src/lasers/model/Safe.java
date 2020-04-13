package lasers.model;

public class Safe {

    private String[][] safeArray;

    public Safe(String[][] safeArray){
        this.safeArray = safeArray;
    }

    public void safeAdd(int row, int column) {
        //adds if laserCheck() is true and sends error if false

        if (laserCheck(row, column)) {
            String laserLetter = "L";
            this.safeArray[row][column] =laserLetter;
            int row1 = row;
            int row2 = row;
            int column1 = column;
            int column2 = column;
            while (row1 >= 1) {
                row1 -= 1;
                if (laserCheck(row1, column)) {
                    String laserPointer = "*";
                    this.safeArray[row1][column] = laserPointer;
                }
                else {
                    break;
                }
            }
            while (row2 < safeArray[0].length - 1) {
                row2++;
                if (laserCheck(row2, column)) {
                    String laserPointer = "*";
                    this.safeArray[row2][column] = laserPointer;
                }
                else{
                    break;
                }
            }
            while (column1 >= 1) {
                column1 -= 1;
                if (laserCheck(row, column1)) {
                    String laserPointer = "*";
                    this.safeArray[row][column1] = laserPointer;
                }
                else{
                    break;
                }
            }
            while (column2 < safeArray.length - 1) {
                column2++;
                if (laserCheck(row, column2)) {
                    String laserPointer = "*";
                    this.safeArray[row][column2] = laserPointer;
                }
                else{
                    break;
                }
            }

        }
    }

    public void safeRemove(int row, int column){
        String laserLetter = ".";
        this.safeArray[row][column] =laserLetter;

        while (row >= 1) {
            String laserPointer = ".";
            row-=1;
            this.safeArray[row][column] = laserPointer;
            if (this.safeArray[row][column].equals("L")){
                break;
            }
        }
        while (row <= safeArray[0].length) {
            String laserPointer = ".";
            row++;
            this.safeArray[row][column] = laserPointer;
            if (this.safeArray[row][column].equals("L")){
                break;
            }
        }
        while (column >= 1) {
            String laserPointer = ".";
            column-=1;
            this.safeArray[row][column] = laserPointer;
            if (this.safeArray[row][column].equals("L")){
                break;
            }
        }
        while (column <= safeArray.length) {
            String laserPointer = ".";
            column++;
            this.safeArray[row][column] = laserPointer;
            if (this.safeArray[row][column].equals("L")){
                break;
            }
            for (int row2 = 0; row2 < safeArray.length; row2++){
                for (int column2 = 0; column2 < safeArray[row2].length; column2++){
                    if (safeArray[row2][column2].equals("L")){
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

    public boolean safeVerify(){
        return false;
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
}
