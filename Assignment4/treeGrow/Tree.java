package treeGrow;

// Trees define a canopy which covers a square area of the landscape
public class Tree{

private
	int xpos;	// x-coordinate of center of tree canopy
	int ypos;	// y-coorindate of center of tree canopy
	float ext;	// extent of canopy out in vertical and horizontal from center

	static float growfactor = 1000.0f; // divide average sun exposure by this amount to get growth in extent

public
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}

	int getX() {
		return xpos;
	}

	int getY() {
		return ypos;
	}

	float getExt() {
		return ext;
	}

	void setExt(float e) {
		ext = e;
	}

	// return the average sunlight 
	synchronized float sunexposure(Land land){
		 int intext = Math.round(ext);
     int xstart;
     int xend;
     int ystart;
     int yend;
     float total = 0f;
     
     //check if positions are beyond dimensions for x and y
     
     if( xpos - intext<0)
     {
     xstart = 0;
     }
     else
     {
     xstart = xpos - intext;
     }
    
     if (xpos + intext > land.getDimX() - 1)
     {
     xend = land.getDimX() - 1;
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
     if(yend > land.getDimY() - 1)
     {
     yend =  land.getDimY() - 1;
     }
     
     int totalcells = (xend- xstart) * (yend-ystart);
     
     for(int x=xstart;x<=xend;x++) {
			for(int y=ystart;y<=yend;y++){
				total += land.getShade(x, y);
            land.setShade(x, y, land.getShade(x, y)*0.1f); 
            }
        }

      if(totalcells == 0)
 	{
	totalcells =1;
	}

     return (total/totalcells);
	}

	// is the tree extent within the provided range [minr, maxr)
	boolean inrange(float minr, float maxr) {
		return (ext >= minr && ext < maxr);
	}

	// grow a tree according to its sun exposure
	synchronized void sungrow(Land land) {
		ext = ext + sunexposure(land)/growfactor;
			}
}
