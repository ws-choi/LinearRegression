package logic;

import java.awt.Point;

public class GridTranslator {

	public int gridx, gridy, ratiox, ratioy;
	double ox, oy;
	
	public GridTranslator(int gridx, int gridy, double ox, double oy) {
		//ratio must be greater than 1. and ox<gridx, oy<gridy
		
		this.gridx=gridx;
		this.gridy=gridy;
		this.ox=ox;
		this.oy=oy;
		
		this.ratiox = (int)(gridx/ox);
		this.ratioy = (int)(gridy/oy);
		
		
	}
	
	public Point getTransformedXY (double x, double y)
	{
		int newy = (int)((oy-y) * ratioy);
		int newx = (int)((x) * ratiox);
		
		return new Point(newx, newy);
	}
	
}
