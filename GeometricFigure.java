package model;

import java.awt.Color;


public abstract class GeometricFigure extends Figure{
	
	private static final long serialVersionUID = 1L;
	private float thickness=0;
	
	protected GeometricFigure(Color color,int thickness,BoundBox bbox) {
		super(color,new BoundBox(bbox.x-thickness,bbox.y-thickness,bbox.width+2*thickness,bbox.height+2*thickness));
		this.thickness=thickness;
	}
	
	protected GeometricFigure(BoundBox bbox) {
		super(bbox);
		this.thickness=1;
	}
	public float getThickness(){
		return thickness;
	}
	@Override
	public void setThickness(int thickness){
		this.thickness=thickness;
		
	}
	
	
}
