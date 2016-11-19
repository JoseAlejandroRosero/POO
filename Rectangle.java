package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends ClosedFigure{
	
	private static final long serialVersionUID = 1L;
	private double base;
	private double height;
	
	public Rectangle(Point position,Color color,Dimension size,int base,int height,int thickness,Color fillcolor,BoundBox bbox){
		super(position,color,size,thickness,fillcolor,bbox);
		this.base = base;
		this.height = height;
	}
	public Rectangle(BoundBox bbox){
		super(bbox);
		this.base = bbox.width;
		this.height = bbox.height;
	}
	
	public double area(){
		return (base*height);
	}
	
	public double GetBase(){
		return base;
	}
	
	public double GetHeight(){
		return height;
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