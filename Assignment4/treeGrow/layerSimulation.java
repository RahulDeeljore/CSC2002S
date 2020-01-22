package treeGrow;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

import treeGrow.SunData;

class layerSimulation extends RecursiveAction{
    static int SEQUENTIAL_CUTOFF = 62500;
    int start;
    int end;
    ArrayList<Tree> trees = new ArrayList<Tree>();
    Land land;

    layerSimulation(int start, int end, ArrayList<Tree> trees, Land land){
        this.start = start;
        this.end = end;
        this.trees = trees;
        this.land = land;
    }

    public void compute(){
        double total = 0;
        if (end - start <= SEQUENTIAL_CUTOFF) {
            for (int i = start; i < end; i++) {
                trees.get(i).sungrow(land);
    
            }
        } else {
            layerSimulation left = new layerSimulation(start, (start+end)/2, trees, land);
            layerSimulation right = new layerSimulation((start+end)/2, end, trees, land);
            left.fork();
            right.compute(); // half the threads
            left.join();
        }
    }
}
