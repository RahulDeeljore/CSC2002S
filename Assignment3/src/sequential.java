import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.io.PrintWriter;


public class sequential
{
public static int SIZE; //size of one length of the square canvas 
public static int NUMTREES; //Total number of trees
public static double TOTAL[]; // Total sunlight hours of each tree
public static double TOTAL2[];// new experimental containing 2 million trees
public static double TOTAL4[];// new experimental containing 4 million trees
public static double TOTAL8[];// new experimental containing 8 million trees
public static double TOTAL16[];// new experimental containing 16 million trees

public static FileReader x;
public static BufferedReader reader;
public static double[][] grid; //2d grid representing tree canvas
public static File output;// output file
public static PrintWriter pw;


public static void main(String []args) throws Exception
{

System.out.println("Sequential run");
sequential p = new sequential();
p.makegrid();

// The values below need to be uncommented to create new data 
//p.newarr(TOTAL,TOTAL2);
//p.newarr(TOTAL2,TOTAL4);
//p.newarr(TOTAL4,TOTAL8);
//p.newarr(TOTAL8,TOTAL16);

output = new File("Output.txt"); // data will be output into this file
pw = new PrintWriter(output);
long start = System.nanoTime(); // start timer
p.avg(); // calculating average using all trees, this is timed. Also printing to file
long end = System.nanoTime(); // end timer
System.out.println((end-start)/1000000.0+" Milliseconds");

//printing the remaining data into file
pw.println(NUMTREES);
p.totalSun();

}


/** This method is used to create a 2d array which will represent the sunmap 
* The sunlight hours will be read from the input file and added to the array
*/
public void makegrid()
{
try{
x = new FileReader("input.txt");//load file
reader = new BufferedReader(x);

String line1 = reader.readLine();// size of array, always a square
String[] Sizeline = line1.split(" ");// split sizeline
SIZE= Integer.parseInt(Sizeline[0]);//size of sunmap. It is a square so only first value needed.
String line2 = reader.readLine(); // all sunlight values
String[] arr = line2.split(" ");// split sunlight values
String line3 = reader.readLine(); // number of trees
NUMTREES = Integer.parseInt(line3);

TOTAL = new double[NUMTREES]; //create all the arrays for experimentation.
TOTAL2 = new double[NUMTREES*2];
TOTAL4= new double[NUMTREES*4];
TOTAL8 = new double[NUMTREES*8];
TOTAL16 = new double[NUMTREES*16];


grid = new double[SIZE][SIZE];// create sunmap grid
int gridval = 0;
for(int i =0;i<SIZE;i++)
{
for(int j=0;j<SIZE;j++)
{
grid[i][j] = Double.parseDouble(arr[gridval]); // add sunlight hours to grid
gridval = gridval + 1;
}
}
double sum=0;
for(int k=0;k<NUMTREES;k++)
{
sum = 0;
String currenttree = reader.readLine(); // line about tree values, will read every tree in iteration
String[] arrtree = currenttree.split(" ");
int first = Integer.parseInt(arrtree[0]);
int second = Integer.parseInt(arrtree[1]);
int third = Integer.parseInt(arrtree[2]);// tree span

for(int l=first;l<first+third;l++)
{
  for(int m = second;m<second+third;m++)
   {
     if( m>=SIZE || l>= SIZE) //outside of sunmap
   {
       sum = sum + 0;
   }
     else if(m<SIZE && l<SIZE) // inside sunmap
   {
       sum = sum + grid[l][m];
   }
}
}
TOTAL[k] = sum; // add total sunlight value of tree to TOTAL array


}
}
catch(Exception e)
{e.printStackTrace(); 
System.out.println(e); 
}
}


/** Method used to write all sunlight total of each trees to file
*/
public void totalSun()
{
for(double sun:TOTAL)
pw.println(sun);
}

/** Method used to write average sunlight hours of all trees to file
*/
public void avg()
{
double totalval =0;
for(double sun:TOTAL)// change array name to vary data size, either TOTAL,TOTAL4,TOTAL8 OR TOTAL16
{
totalval = totalval+sun;
}                           
pw.println(totalval/(NUMTREES));// NUMTREE value must be multiplied according to array used
}


/** This method is used to create an array twice the size of the original
* The new array will then be used to calculate the running time
*/
public void newarr(double TOTAL[],double TOTAL1[])
{
for(int p=0;p<TOTAL1.length;p++)
{
if(p <  TOTAL.length)
{
TOTAL1[p] = TOTAL[p];// duplicate array
}
else
{
TOTAL1[p] = TOTAL[p-TOTAL.length]; // if inital array size reached, start again.
}
}}


}
