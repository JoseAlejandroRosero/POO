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
		normalize();
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
	public void copy(Rectangle bbox) {
		this.x = bbox.x;
		this.y = bbox.y;
		this.width = bbox.width;
		this.height = bbox.height;
		for(ControlPoint cp : controlpoints){
			cp.updatePosition(this);
		}
	}
	public Cursor setCursor(Point p) {
		for(ControlPoint cp : controlpoints){
			if(cp.contains(p)){
				return cp.getCursor();
			}
		}
		return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
	}
	@Override
	public boolean contains(Point p){
		for(ControlPoint cp : controlpoints){
			if(cp.contains(p)){
				return true;
			}
		}
		return super.contains(p);
	}
	@Override
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		for(ControlPoint cp : controlpoints){
			cp.updatePosition(this);
		}
	}
	public void setPosition(int dx, int dy) {
		setLocation(x+dx,y+dy);
		for(ControlPoint cp: controlpoints){
			cp.translate(dx, dy);
		}
	}
	public ControlPoint getControlPoint(Point p) {
		for(ControlPoint cp: controlpoints){
			if(cp.contains(p))return cp;
		}
		return null;
	}
	public void moveTo(Point p) {
		for(ControlPoint cp : controlpoints){
			cp.moveTo(p);
		}
	}
}

