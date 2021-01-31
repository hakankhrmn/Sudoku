import java.io.*;

public class SudokuSolver {


    public static final int x = 0; // empty cell
    public static final int y = 9; // size of our Sudoku grids
    private int[][] puzzle=new int[9][9];

    SudokuSolver(){

    }
    public void grid() throws IOException {

        File file = new File("temple.txt");
        BufferedReader rd =new BufferedReader(new FileReader("temple.txt"));
        String r=rd.readLine();
        rd.close();
        file.delete();

        char[] charArray=r.toCharArray();
        int c =0;
        for (int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                puzzle[i][j] = Character.getNumericValue(charArray[c]);
                c++;
            }
        }
    }
    // we check if a possible number is already in a row
    private boolean rowCheck(int row, int num) {
        for (int i = 0; i < y; i++)
            if (puzzle[row][i] == num)
                return true;

        return false;
    }
    // we check if a possible number is already in a column
    private boolean colCheck(int col, int num) {
        for (int i = 0; i < y; i++)
            if (puzzle[i][col] == num)
                return true;

        return false;
    }
    // we check if a possible number is in its 3x3 box
    private boolean EmptyCheck(int row, int col, int num) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (puzzle[i][j] == num)
                    return true;

        return false;
    }
    // combined method to check if a number possible to a row,col position is ok
    private boolean AllCheck(int row, int col, int num) {
        return !rowCheck(row, num)  &&  !colCheck(col, num)  &&  !EmptyCheck(row, col, num);
    }
    public boolean Solution() throws IOException {
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < y; col++) {
                // we search an empty cell
                if (puzzle[row][col] == x) {
                    // we try possible numbers
                    for (int num = 1; num <= y; num++) {
                        if (AllCheck(row, col, num)) {
                            // Number ok. it respects sudoku constraints
                            puzzle[row][col] = num;

                            if (Solution()) { // we start backtracking recursively
                                return true;
                            } else { // if not a solution, we empty the cell and we continue
                                puzzle[row][col] = x;
                            }
                        }
                    }
                    return false; // we return false
                }
            }
        }
        return true; // sudoku solved
    }
    public int[][] getBoard(){
        return  puzzle;
    }
}
