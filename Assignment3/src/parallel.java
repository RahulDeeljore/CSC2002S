import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;



public class parallel extends RecursiveTask<Double>
{
public static int SIZE;
public static int NUMTREES;
public static double TOTAL[];//original file containing 1 million trees
public static double TOTAL2[];// new experimental containing 2 million trees
public static double TOTAL4[];// new experimental containing 4 million trees
public static double TOTAL8[];// new experimental containing 8 million trees
public static double TOTAL16[];// new experimental containing 16 million trees

public static FileReader x;
public static BufferedReader reader;
public static double[][] grid;
int hi,lo;
public static final int sequentialcutoff= 62500;
static final ForkJoinPool fjPool = new ForkJoinPool();
static double startTime=0;



public static void main(String []args)
{
parallel p = new parallel(0,1000000);
p.makegrid();

//lines below need to be uncommented to use run with increased data

//p.newarr(TOTAL,TOTAL2);
//p.newarr(TOTAL2,TOTAL4);
//p.newarr(TOTAL4,TOTAL8);
//p.newarr(TOTAL8,TOTAL16);


//System.out.println(TOTAL[TOTAL.length-1]);
//System.out.println(TOTAL2[TOTAL2.length-1]); // this was used to see if new array was well created



// 5 runs for the parallel program
// some of these values need to be changed when using different arraysizes
System.out.println("Size:" +TOTAL.length);
System.out.println("Sequential cutoff = "+sequentialcutoff);
System.out.println("Run number 1");
tick();// start timer
System.out.println("Average sunlight: "+p.averageSun()/(NUMTREES));
double end = tock(); // end timer
System.out.println((end)+" Milli Seconds");
System.out.println("Run number 2");
tick();
System.out.println(p.averageSun()/(NUMTREES));
end = tock();
System.out.println((end)+" Milli Seconds");
System.out.println("Run number 3");
tick();
System.out.println(p.averageSun()/(NUMTREES));
end = tock();
System.out.println((end)+" Milli Seconds");
System.out.println("Run number 4");
tick();
System.out.println(p.averageSun()/(NUMTREES));
end = tock();
System.out.println((end)+" Milli Seconds");
System.out.println("Run number 5");
tick();
System.out.println(p.averageSun()/(NUMTREES));
end = tock();
System.out.println((end)+" Milli Seconds");

}


/** method to record time, starting stopwatch
*/
private static void tick(){ 
startTime = System.nanoTime();
}
/** method to record time, stopping stopwatch
*/   
private static double tock()
{       
return (System.nanoTime() - startTime) / 1000000.0d;    // returns time in milliseconds 
}

/** Constructor */
public parallel(int lo,int hi)
{
this.lo = lo;
this.hi = hi;
}

/** invoke the compute method*/
static double averageSun()
{
return fjPool.invoke(new parallel(0,TOTAL.length));
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
String[] Sizeline = line1.split(" ");
SIZE= Integer.parseInt(Sizeline[0]);//size of sunmap. It is a square so only first value needed
String line2 = reader.readLine(); // all sunlight values
String[] arr = line2.split(" ");// split sunlight values
String line3 = reader.readLine(); // number of trees
NUMTREES = Integer.parseInt(line3);


TOTAL = new double[NUMTREES];
TOTAL2 = new double[NUMTREES*2];
TOTAL4= new double[NUMTREES*4];
TOTAL8 = new double[NUMTREES*8];
TOTAL16 = new double[NUMTREES*16];


grid = new double[SIZE][SIZE];
int gridval = 0;
for(int i =0;i<SIZE;i++)
{
for(int j=0;j<SIZE;j++)
{
grid[i][j] = Double.parseDouble(arr[gridval]);
gridval = gridval + 1;
}
}
double sum=0;
for(int k=0;k<NUMTREES;k++)
{
sum = 0;
String currenttree = reader.readLine();
String[] arrtree = currenttree.split(" ");
int first = Integer.parseInt(arrtree[0]);
int second = Integer.parseInt(arrtree[1]);
int third = Integer.parseInt(arrtree[2]);

for(int l=first;l<first+third;l++)
{
  for(int m = second;m<second+third;m++)
   {
     if( m>=SIZE || l>= SIZE)
   {
       sum = sum + 0;
   }
     else if(m<SIZE && l<SIZE)
   {
       sum = sum + grid[l][m];
   }
}
}
TOTAL[k] = sum;

}
}
catch(Exception e)
{e.printStackTrace(); 
System.out.println(e); 
}
}

public void newarr(double TOTAL[],double TOTAL1[])
{
for(int p=0;p<TOTAL1.length;p++)
{
if(p <  TOTAL.length)
{
TOTAL1[p] = TOTAL[p];
}
else
{
TOTAL1[p] = TOTAL[p-TOTAL.length];
}
}}



/** method that uses parallelism to add all sunlight values.
*/
protected Double compute() {
      
 double sum1=0;
if((hi-lo)< sequentialcutoff ){ //hi and lo are initialized in constructor
            
      for(int i = lo;i<hi;i++){
        
            sum1+= TOTAL[i];
            }        
            return sum1;
        }
        else{
            
            parallel left =new parallel(lo,(lo+hi)/2);
            parallel right =new parallel((lo+hi)/2,hi);
            
          
            left.fork();
            right.fork();
            double leftAns =left.join();
            double rightAns = right.join();
            
            return (rightAns+leftAns);


}
}
}
