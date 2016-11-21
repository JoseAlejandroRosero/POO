package model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class ClosedFigure extends GeometricFigure{

	private static final long serialVersionUID = 1L;
	private Color fillcolor;
	
	protected abstract void fillFigure(Graphics2D g);
	protected ClosedFigure( Color color, int thickness,Color fillcolor,BoundBox bbox) {
		super( color, thickness,bbox);
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
