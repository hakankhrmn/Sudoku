import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sudoku {
    private static char[] c={'0','C','N','G','B','I','M','2','1','3'};
    private static int[][] table;
    static String command;
    public static void main(String[] args) throws IOException {

        while (command != "Q") {
            Scanner input= new Scanner(System.in);
            System.out.println("First you should create a puzzle");
            System.out.println("-To create a puzzle please enter 'C' ");
            System.out.println("-To solve the puzzle please enter 'S' ");
            System.out.println("-To end the program please enter 'Q' ");
            command = input.nextLine();
            if (command.equals("C")){  // CREATE SUDOKU PUZZLE

                SudokuCreater s=new SudokuCreater();
                table = s.puzzlecreater();
                printTable(table);
            }else if(command.equals("S")){  // SOLVE SUDOKU PUZZLE
                if(checkFile()==true){
                    SudokuSolver solver=new SudokuSolver();
                    solver.grid();
                    solver.Solution();
                    table=solver.getBoard();
                    printTable(table);
                }else{
                    // ERROR MESSAGE
                    System.out.println("***Please, First Create a Sudoku with 'C' command***");
                    System.out.println("****************************************************");
                }

            }else if(command.equals("Q")){
                break;
            }else{
                // ERROR MESSAGE
                System.out.println("***Please, a valid command***");
                System.out.println("*****************************");
            }
        }


    }
    
    // TABLE and GRAPHICAL ARRANGEMENTS
    public static void printTable(int[][] table){
        for(int i=0;i<9;i++)
        {
            if(i==0){
                System.out.println("-------------------------");
            }
            for(int j=0;j<9;j++)
            {
                if(j==0){
                    System.out.printf("| ");
                }
                System.out.print(c[table[i][j]]+" ");
                if((j+1)%3==0){
                    System.out.printf("| ");
                }
            }
            System.out.println("");
            if ((i+1)%3==0){
                System.out.println("-------------------------");
            }
        }
    }
    
    // FILE
    public static boolean checkFile() {
        File file = new File("temple.txt");
        if(file.exists()){
            return true;
        }


        return false;
    }

}
