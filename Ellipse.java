package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ellipse extends ClosedFigure implements Cloneable{
	
	private static final long serialVersionUID = 1L;

	public Ellipse(Point position,Color color,Dimension size,int thickness,Color fillcolor,BoundBox bbox){
		super(position,color,size,thickness,fillcolor,bbox);
	}
	
	public Ellipse(BoundBox bbox){
		super(bbox);
	}

	@Override
	public void dopaint(final Graphics2D g) {
		BoundBox bbox=getNormalizedBoundBox();
		g.setColor(getColor());
		g.setStroke(new BasicStroke(getThickness()));
		g.drawOval(bbox.x, bbox.y, bbox.width, bbox.height);
		fillFigure(g);
		}

	@Override
	protected void fillFigure(final Graphics2D g) {
		if(getFillColor()!=null || getFillColor()==Color.WHITE){
			BoundBox bbox=getNormalizedBoundBox();
			g.setColor(getFillColor());
			g.fillOval(bbox.x, bbox.y, bbox.width, bbox.height);
		}
	}
}
