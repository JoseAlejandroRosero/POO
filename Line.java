package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends GeometricFigure {

	private static final long serialVersionUID = 1L;
	
	private Point endpoint;
	
	public Line(Point position,Color color,Dimension size,int thickness,BoundBox bbox, Point endpoint){
		super(position,color,size,thickness,bbox);
		this.endpoint=endpoint;
	}
	
	public Line(BoundBox bbox){
		super(bbox);
		this.endpoint= new Point(bbox.x-getPosition().x,bbox.y-getPosition().y);
	}

	public Line(Point p, Point r) {
		this(p,Color.BLACK,new Dimension(r.x-p.x,r.y-p.y),1,new BoundBox(p.x,p.y,r.x-p.x,r.y-p.y),r);
	}

	@Override
	public void dopaint(final Graphics2D g) {
		Point p = getPosition();
		g.setStroke(new BasicStroke(getThickness()));
		g.drawLine(p.x, p.y, endpoint.x, endpoint.y);
	}
	@Override
	public boolean needsNormalization(){
		return false;
	}
	@Override
	public void move(Point p){
		super.move(p);
		BoundBox bbox = getNormalizedBoundBox();
		endpoint=new Point(bbox.x+bbox.width,bbox.y+bbox.height);
	}
	@Override
	public void processCursor(Cursor c,Point p){
		super.processCursor(c, p);
		BoundBox bbox = getNormalizedBoundBox();
		endpoint=new Point(bbox.x+bbox.width,bbox.y+bbox.height);
		setPosition(bbox.getLocation());
	}
}
