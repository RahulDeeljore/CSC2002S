package treeGrow;

import java.util.Arrays;

public class Land{

        int dx;
        int dy;
	float[][] grid;
	float[][] unshaded;

	// to do
	// sun exposure data here

	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

	Land(int dx, int dy) {
		grid = new float[dx][dy];
		unshaded = new float[dx][dy];
	}

	int getDimX() {
		return unshaded[0].length;
	}

	int getDimY() {
		return unshaded[1].length;
	}

	// no need to synchronize
	void resetShade() {
                 int ogx = Math.round(unshaded[0].length);
                 int ogy = Math.round(unshaded[1].length);
		for (int i = 0; i < ogx; i++){
			for (int j = 0; j < ogy; j++){
				grid[i][j] = unshaded[i][j];
			}
		}
	}

	float getFull(int x, int y) {
		return unshaded[x][y];
	}

	void setFull(int x, int y, float val) {
		// to do
		unshaded[x][y] = val;
		grid[x][y] = val;
	}


   // all 3 methods below are synchronized to prevent interleavings.

	synchronized float getShade(int x, int y) { 
		// to do
		return grid[x][y];
	}

	synchronized void setShade(int x, int y, float val){
		grid[x][y] = val;
	}

 
     // used to calculate shadow and set it
     synchronized void shadow(Tree tree){
		int intext = Math.round(tree.getExt());
     int xstart;
     int xend;
     int ystart;
     int yend;
     int xpos = tree.getX();
     int ypos = tree.getY();
     
     float total = 0f;
     
     if( xpos - intext<0)
     {
     xstart = 0;
     }
     else
     {
     xstart = xpos - intext;
     }
    
     if (xpos + intext > dx - 1)
     {
     xend = dx - 1;
     }
     
     else
     {
     xend = xpos + intext;
     }
     
     ystart = ypos - intext;
     if(ystart <0)
     {
     ystart = 0;
     }
     
     yend = ypos + intext;
     if(yend > dy - 1)
     {
     yend =  dy - 1;
     }
     
    
     for(int x=xstart;x<=xend;x++) {
			for(int y=ystart;y<=yend;y++){
				setShade(x,y,getShade(x, y)*shadefraction);
	}}}

	
}
