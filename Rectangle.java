package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends ClosedFigure{
	
	private static final long serialVersionUID = 1L;
	
	public Rectangle(Color color,int thickness,Color fillcolor,BoundBox bbox){
		super(color,thickness,fillcolor,bbox);
		
	}
	public Rectangle(BoundBox bbox){
		super(bbox);
	}
	
@Override
public void dopaint(final Graphics2D g){
	BoundBox bbox=getNormalizedBoundBox();
	g.setStroke(new BasicStroke(getThickness()));
	g.setColor(getColor());
	g.drawRect(bbox.x, bbox.y, bbox.width, bbox.height);
	fillFigure(g);
	}

@Override
protected void fillFigure(Graphics2D g) {
	BoundBox bbox= getNormalizedBoundBox();
	if(getFillColor()!=null){
		g.setColor(getFillColor());
		g.fillRect(bbox.x, bbox.y, bbox.width, bbox.height);
	}
}
}