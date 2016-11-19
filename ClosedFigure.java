package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class ClosedFigure extends GeometricFigure{

	private static final long serialVersionUID = 1L;
	private Color fillcolor;
	
	protected abstract void fillFigure(Graphics2D g);
	protected ClosedFigure(Point position, Color color, Dimension size, int thickness,Color fillcolor,BoundBox bbox) {
		super(position, color, size, thickness,bbox);
		this.fillcolor=fillcolor;
		
	}
	protected ClosedFigure(BoundBox bbox) {
		super(bbox);
		this.fillcolor=null;
	}
	public Color getFillColor(){
		return fillcolor;
	}
	@Override
	public void setFillcolor(Color c){
		this.fillcolor=c;
	}
}
