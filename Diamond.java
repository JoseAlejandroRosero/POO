package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Diamond extends ClosedFigure {
	
	private static final long serialVersionUID = 1L;
	
	public static final int N=4;
	public static final int FIRST=0;
	public static final int SECOND=1;
	public static final int THIRD=2;
	public static final int FORTH = 3;
	
	private int[] xPoints = new int[N];
	private int[] yPoints = new int[N];

	public Diamond(Point position, Color color, Dimension size, int thickness, Color fillcolor, BoundBox bbox) {
		super(position, color, size, thickness, fillcolor, bbox);

		xPoints[FIRST] = bbox.x+(bbox.width/2);
		xPoints[SECOND] = bbox.x;
		xPoints[THIRD] = bbox.x+(bbox.width/2);
		xPoints[FORTH] = (bbox.x+bbox.width);
		
		yPoints[FIRST] = bbox.y;
		yPoints[SECOND] = bbox.y+(bbox.height/2);
		yPoints[THIRD] = bbox.y+bbox.height;
		yPoints[FORTH] = bbox.y+(bbox.height/2);

	}
	
	public Diamond(Point first, Point second){
		
		this(first,Color.BLACK,null,1,null,new BoundBox(first,second));
		
		BoundBox bbox = getNormalizedBoundBox();
		xPoints[FIRST] = bbox.x+(bbox.width/2);
		xPoints[SECOND] = bbox.x;
		xPoints[THIRD] = bbox.x+(bbox.width/2);
		xPoints[FORTH] = (bbox.x+bbox.width);
		
		yPoints[FIRST] = bbox.y;
		yPoints[SECOND] = bbox.y+(bbox.height/2);
		yPoints[THIRD] = bbox.y+bbox.height;
		yPoints[FORTH] = bbox.y+(bbox.height/2);
		
	}

	@Override
	protected void fillFigure(final Graphics2D g) {
		if(getFillColor()!=null){
			g.setColor(getFillColor());
			g.fillPolygon(xPoints,yPoints,N);	
		}
	}
	
	@Override
	public void dopaint(final Graphics2D g){
		BoundBox bbox = getNormalizedBoundBox();
		xPoints[FIRST] = bbox.x+(bbox.width/2);
		xPoints[SECOND] = bbox.x;
		xPoints[THIRD] = bbox.x+(bbox.width/2);
		xPoints[FORTH] = (bbox.x+bbox.width);
		
		yPoints[FIRST] = bbox.y;
		yPoints[SECOND] = bbox.y+(bbox.height/2);
		yPoints[THIRD] = bbox.y+bbox.height;
		yPoints[FORTH] = bbox.y+(bbox.height/2);
		g.setStroke(new BasicStroke(getThickness()));
		g.drawPolygon(xPoints, yPoints, N);
		fillFigure(g);
	}
}
