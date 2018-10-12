import ptolemy.plot.*;

public class LogPlot {
    public void drawGraph(int xPoints, long[] yPoints, String title) {

	Plot plot = new Plot();             // Make a default plot
	plot.setXLog(true);                 // Set x log axis 
	plot.setYLog(true);                 // Set y log axis

	for(int i = 1; i <= xPoints; i++) {	    
	    plot.addPoint(0,i,yPoints[i-1],true);
	}
	
	plot.setTitle(title);      // Set plot title 
	plot.setXLabel("Time");         // Set the x/y labels
	plot.setYLabel("Velocity");
	
	//  Make a frame to display the plot in
	PlotFrame frame = new PlotFrame("A Log plot",plot);
	frame.setSize(600,400);              // Set size of frame (in pixels)
	frame.setVisible(true);              // Make frame visible
    }
}

