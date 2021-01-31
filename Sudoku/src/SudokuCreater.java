
import java.io.*;
import java.util.Random;


public class SudokuCreater {

    private int[][] a=new int[9][9];

    SudokuCreater(){

    }

    private void generator()  // GENERATE ARRAY [9][9] 
    {
        int x=1,y=1;
        for(int i=0;i<9;i++)
        {
            x=y;
            for(int j=0;j<9;j++)
            {
                if(x<=9){
                    a[i][j]=x;
                    x++;
                }else{
                    x=1;
                    a[i][j]=x;
                    x++;
                }
            }
            y=x+3;
            if(x==10)
                y=4;
            if(y>9)
                y=(y%9)+1;
        }
    }

    // FILL GENERATED ARRAY WITH RANDOM VARIABLES
    private void randomize(int check){
        int x1,x2,max=2,min=0;
        Random r= new Random();
        for(int i=0;i<3;i++)
        {
//There are three groups.So we are using for loop three times.
            x1=r.nextInt(max-min+1)+min;
//This while is just to ensure x1 is not equal to x2.
            do{
                x2=r.nextInt(max-min+1)+min;
            }while(x1==x2);
            max+=3;min+=3;
//check is global variable.
//We are calling randomize two time from the main func.
//Once it will be called for columns and once for rows.
            if(check==1)
//calling a function to interchange the selected rows.
                per_row(x1,x2);
            else if(check==0)
                per_col(x1,x2);
        }
    }

    private void per_row(int x1,int x2){
        int temp;//x1 and x2 are two rows that we are selecting to interchange.
        for(int j=0;j<9;j++)
        {
            temp=a[x1][j];
            a[x1][j]=a[x2][j];
            a[x2][j]=temp;
        }
    }

    private void per_col(int x1,int x2){
        int temp;
        for(int j=0;j<9;j++) //x1 and x2 are two columns that we are selecting to interchange.
        {
            temp=a[j][x1];
            a[j][x1]=a[j][x2];
            a[j][x2]=temp;
        }
    }

    private void row_change(int x1,int x2)
    {
        int temp;
        for(int y=1;y<=3;y++)
        {
            for(int i=0;i<9;i++)
            {
                temp=a[x1][i];
                a[x1][i]=a[x2][i];
                a[x2][i]=temp;
            }
            x1++;
            x2++;
        }
    }
    private void col_change(int x1,int x2)
    {
        int temp;
        for(int y=1;y<=3;y++)
        {
            for(int i=0;i<9;i++)
            {
                temp=a[i][x1];
                a[i][x1]=a[i][x2];
                a[i][x2]=temp;
            }
            x1++;
            x2++;
        }
    }
    private void check(int x1,int x2) // CHECK DUPLICATES and ROWS AND COLUMNS ACCORDING TO SUDOKU RULES
    {
        int row_from;
        int row_to;
        int col_from;
        int col_to;
        int i,j,b,c;
        int rem1,rem2;
        int flag;
        int count=9;

        for(i=1;i<=9;i++)
        { flag=1;
            for(j=0;j<9;j++)
            {
                if(j!=x2)
                {
                    if(i!=a[x1][j])
                    {
                        continue;
                    }
                    else
                    {
                        flag=0;
                        break;
                    }
                }
            }
            if(flag==1)
            {
                for(c=0;c<9;c++)
                {
                    if(c!=x1)
                    {
                        if(i!=a[c][x2])
                        {
                            continue;
                        }
                        else
                        {
                            flag=0;
                            break;
                        }
                    }
                }
            }
            if(flag==1)
            {
                rem1=x1%3; rem2=x2%3;
                row_from=x1-rem1;
                row_to=x1+(2-rem1);
                col_from=x2-rem2;
                col_to=x2+(2-rem2);
                for(c=row_from;c<=row_to;c++)
                {
                    for(b=col_from;b<=col_to;b++)
                    {
                        if(c!=x1 && b!=x2)
                        {
                            if(i!=a[c][b])
                                continue;
                            else{
                                flag=0;
                                break;
                            }
                        }
                    }
                }
            }
            if(flag==0)
                count--;
        }
        if(count==1)
        {
            a[x1][x2]=0;
        }
    }


    public int[][] puzzlecreater() throws IOException {

        int counter=1,x1,x2;
        generator();
        randomize(1);
        randomize(0);

        Random rand=new Random();
        int y[]={0,3,6};
        for(int i=0;i<2;i++)
        {
            x1=y[rand.nextInt(y.length)];
            do{
                x2=y[rand.nextInt(y.length)];
            }while(x1==x2);
            if(counter==1)
                row_change(x1,x2);
            else col_change(x1,x2);
            counter++;
        }


        //Striking out
        for(x1=0;x1<9;x1++)
        {
            for(x2=0;x2<9;x2++)
                check(x1,x2);
        }


        File temple=new File("temple.txt");
        BufferedWriter wr =new BufferedWriter(new FileWriter(temple,false));
        for (int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                wr.write(Integer.toString(a[i][j]));
            }
        }
        wr.close();

        return a;
    }
}
