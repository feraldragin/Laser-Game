package lasers.model;

public class Safe {

    private String[][] safeArray;

    public Safe(String[][] safeArray){
        this.safeArray = safeArray;
    }

    public String safeAdd(){
        //adds if laserCheck() is true and sends error if false
        return "add";
    }

    public String safeRemove(){
        return "remove";
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

    public boolean laserCheck(){
        //if pillars are good and laser, number, letter, and/or * not in place
        return false;
    }
}
