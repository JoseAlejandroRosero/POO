package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import view.Cardinal;
import view.ControlPoint;

public class BoundBox extends Rectangle {
	
	private static final long serialVersionUID = -4017699224554280511L;
	
	private ControlPoint[] controlpoints= new ControlPoint[8];

	public BoundBox(int x,int y,int width,int height) {
		super(x,y,width,height);
		for(Cardinal c: Cardinal.values()){
			controlpoints[c.ordinal()]= new ControlPoint(c,this);
			}
		}
	public BoundBox (Point p, Dimension d){
		super(p,d);
		for(Cardinal c: Cardinal.values()){
			controlpoints[c.ordinal()]= new ControlPoint(c,this);
			}
	}
	public BoundBox(BoundBox bbox){
		super(bbox);
		for(Cardinal c: Cardinal.values()){
			controlpoints[c.ordinal()]= new ControlPoint(c,this);
			}
	}
	public BoundBox(Point p, Point r) {
		this(p.x,p.y,r.x-p.x,r.y-p.y);
		this.normalize();
	}
	public BoundBox() {
		for(Cardinal c: Cardinal.values()){
			controlpoints[c.ordinal()]= new ControlPoint(c,this);
		}
	}
	public void normalize(){
		if(width<0){
			x+=width;
		 width=-width;
		}
		if(height<0){
			y+=height;
	     height=-height;
		}
		setBounds(x,y,width,height);
	}
	public BoundBox normalized(){
		BoundBox bbox= new BoundBox(this);
		bbox.normalize();
		return bbox;
	}
	public void paint(final Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		float[] dashed = {10.0f};
		g2.setStroke(new BasicStroke( 1.0f , BasicStroke.CAP_BUTT , BasicStroke.JOIN_BEVEL , BasicStroke.CAP_BUTT ,dashed,  0.0f));   
		g2.setColor(Color.DARK_GRAY);
		g2.drawRect(x, y, width, height);
		for(ControlPoint c : controlpoints){
			c.paint(g2);
		}
	}
	public ControlPoint[] getControlPoint(Point p) {
		return controlpoints;
	}
	public void copy(Rectangle bbox) {
		this.x = bbox.x;
		this.y = bbox.y;
		this.width = bbox.width;
		this.height = bbox.height;
		for(Cardinal c : Cardinal.values()){
			controlpoints[c.ordinal()]= new ControlPoint(c,this);
		}
	}
	public Cursor getCursor(Point p) {
		Cursor c = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		for(ControlPoint cp : controlpoints){
			if(cp.contains(p)){
				c=cp.getCursor();
			}
		}
		return c;
	}
	@Override
	public boolean contains(Point p){
		boolean b= false;
		if(super.contains(p)){
			b=true;
		}
		for(ControlPoint cp : controlpoints){
			if(cp.contains(p)){
				b=true;
			}
		}
		return b;
	}
	public void setPosition(Point p) {
		int dx = 2*x-p.x;
		int dy = 2*y-p.y;
		setLocation(x-dx, y-dy);
		for(ControlPoint cp : controlpoints ){
			cp.setPosition(dx, dy);
		}
	}
	@Override
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		for(Cardinal c : Cardinal.values()){
			controlpoints[c.ordinal()]= new ControlPoint(c,this);
		}
	}
}

