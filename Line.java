package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends GeometricFigure {

	private static final long serialVersionUID = 1L;
	public Line(Color color,int thickness,BoundBox bbox){
		super(color,thickness,bbox);
	}
	
	public Line(BoundBox bbox){
		this(Color.BLACK,1,bbox);
	}

	public Line(Point p, Point r) {
		this(new BoundBox(p.x,p.y,r.x-p.x,r.y-p.y));
	}

	@Override
	public void dopaint(final Graphics2D g) {
		g.setStroke(new BasicStroke(getThickness()));
		BoundBox bbox=getBoundBox();
		g.drawLine(bbox.x, bbox.y, bbox.x+bbox.width, bbox.y+bbox.height);
	}
	@Override
	public boolean needsNormalization(){
		return false;
	}
	@Override
	public BoundBox getNormalizedBoundBox(){
		BoundBox bbox = getBoundBox().normalized();
		return bbox;
	}
	@Override
	public void resize(Point p) {
		getNormalizedBoundBox().moveTo(p);
		BoundBox bbox=getBoundBox();
		if(bbox.width<0 && bbox.height>0);
	}
}	

