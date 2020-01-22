package treeGrow;

import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class TreeGrow {
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static ForestPanel fp;
	static ForkJoinPool commonPool = new ForkJoinPool();
	static volatile boolean running;
	static volatile int year;
	static JLabel yearLabel;
        static SunData sundata;

	// start timer
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	public static void setupGUI(int frameX,int frameY,Tree [] trees) {
		Dimension fsize = new Dimension(800, 800);
		// Frame init and dimensions
    	JFrame frame = new JFrame("Photosynthesis"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(fsize);
    	frame.setSize(800, 800);
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setPreferredSize(fsize);
 
		fp = new ForestPanel(trees);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		JScrollPane scrollFrame = new JScrollPane(fp);
		fp.setAutoscrolls(true);
		scrollFrame.setPreferredSize(fsize);
		g.add(scrollFrame);
		
	
		JPanel buttons = new JPanel();
	    buttons.setLayout(new FlowLayout());
	    
	    yearLabel = new JLabel("");
	    buttons.add(yearLabel);
       
       
       // add the buttons required are below
      
	    JButton reset = new JButton("Reset");
	    buttons.add(reset);
       reset.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				reset();
				yearLabel.setText("Year: "+year);
            running = false;
            }
            });

      
      
      JButton pause = new JButton("Pause");
	   buttons.add(pause);
      pause.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
         try{
					Thread.sleep(300); 				} 
           catch(InterruptedException f){
					f.printStackTrace();
				}
         running = false;
         }
         });

      
      JButton play = new JButton("Play");
      buttons.add(play);
      play.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
        running = true;
        
        }
      });

      
      
      JButton end = new JButton("End");
      buttons.add(end);
      end.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
      running = false;
      System.exit(0);
      }
      });
      
      
      g.add(buttons);
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
        frame.setVisible(true);
		Thread fpt = new Thread(fp);
		fpt.start();
	}

         public static void reset() {
      year = 0; 
		for(Tree tree : sundata.trees) {
			tree.setExt(0.4f);
		}
	}

         // this method is used to create treads to do the layer simulations.
         // method will keep running until a button is pressed.
         public static void Simulate(SunData s)
             {
               float time;
               running = true;
                while (true) {
			if (running == true){
				tick();
				System.out.println("Years simulated: "+year);
				for (int i = 20; i > 0; i = i-2){
					ArrayList<Tree> temp_layer = new ArrayList<Tree>();
					for (int j = 0; j < sundata.trees.length; j++){
						if (sundata.trees[j].inrange(i-2, i)) {
							temp_layer.add(sundata.trees[j]);
						} 
					}
				
					commonPool.invoke(new layerSimulation(0, temp_layer.size(), temp_layer, sundata.sunmap));//process year
				}
				time = tock();
				System.out.println("Time for year simulation: "+time+ "seconds");
				sundata.sunmap.resetShade();
				year++;
				yearLabel.setText("Year: "+year);
			} else {
				continue;
			}
		}
              }

	
	public static void main(String[] args) {
		sundata = new SunData();
		
		
		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
			System.exit(0);
		}
				
		// read in forest and landscape information from file supplied as argument
		sundata.readData(args[0]);
		System.out.println("Data loaded");
		
		frameX = sundata.sunmap.getDimX();
		frameY = sundata.sunmap.getDimY();

		setupGUI(frameX, frameY, sundata.trees);
		
		reset(); // reset extent before running
      Simulate(sundata); // create simulation thread
                
		
		
	}
}
