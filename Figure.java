package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

public abstract class Figure implements Shape{ 
	
	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean selected;

	private BoundBox bbox;
	private boolean resizable=true;
	
	protected Figure(Color color,BoundBox bbox){
		super();
		this.color = color;
		this.bbox=bbox;
		if(needsNormalization()){
			this.bbox.normalize();
		}
	}
	protected Figure(Figure f){
		this(f.color,f.bbox);
	}
	protected Figure(BoundBox bbox){
		this(Color.BLACK,bbox);
	}
	public abstract void dopaint(final Graphics2D g);

	protected boolean needsNormalization(){
		return true;
	}
	
	public boolean isResizable() {
		return resizable;
	}
	public void setResizable(boolean b) {
		resizable = b;
	}
	public BoundBox getBoundBox(){
		return bbox;
	}
	public BoundBox getNormalizedBoundBox(){
		return bbox;
	}
	public Color getColor(){
		return color;
	}
	public boolean isSelected(){
		return selected;
	}
	public void setSelected(boolean b){
		selected=b;
	}
	public void paint(final Graphics g){
		g.setColor( color );
		dopaint((Graphics2D) g);
		if(isSelected()){
			getNormalizedBoundBox().paint(g);
		}
	}
	public boolean contains(Point p) {
		return getNormalizedBoundBox().contains(p);
	}
	public void setFillcolor(Color c){}
	public void setBordercolor(Color color) {
		this.color=color;
	}
	public void setThickness(int thickness){}
	public Cursor getCursor(Point p) {
		return bbox.normalized().getCursor(p);
	}
	public List<Figure> getChildren() {
		return null;
	}
	public void move(Point p) {
		bbox.setPosition(p);
	}
	public void processCursor(Cursor c,Point p) {
		int dy;
		int dx;
		if(bbox.isEmpty()){
			resizable=false;
		}
		if(!resizable){
			switch(c.getType()){
			
			case Cursor.NW_RESIZE_CURSOR:
				if(p.x<bbox.x+bbox.width && p.y<bbox.y+bbox.height){
					resizable = true;
				}
				break;
			case Cursor.N_RESIZE_CURSOR:
				if(p.y<bbox.y+bbox.height){
					resizable = true;
				}
				break;
			case Cursor.NE_RESIZE_CURSOR:
				if(p.x>bbox.x && p.y<bbox.y+bbox.height){
					resizable = true;
				}
				break;
			case Cursor.E_RESIZE_CURSOR:
				if(p.x>bbox.x){
					resizable = true;
				}
				break;
			case Cursor.SE_RESIZE_CURSOR:
				if(p.x>bbox.x && p.y>bbox.y){
					resizable = true;
				}
				break;
			case Cursor.S_RESIZE_CURSOR:
				if(p.y>bbox.y){
					resizable = true;
				}
				break;
			case Cursor.SW_RESIZE_CURSOR:
				if(p.y>bbox.y && p.x<bbox.x+bbox.width){
					resizable = true;
				}
				break;
			case Cursor.W_RESIZE_CURSOR:
				if( p.x<bbox.x+bbox.width){
					resizable = true;
				}
				break;
			}
		}
		if(resizable){
			switch(c.getType()){
			case Cursor.NW_RESIZE_CURSOR:
				dy=bbox.y-p.y;
				dx=bbox.x-p.x;
				bbox.setBounds(bbox.x-dx,p.y,bbox.width+dx,bbox.height+dy);
				break;
			case Cursor.N_RESIZE_CURSOR:
				dy=bbox.y-p.y;
				bbox.setBounds(bbox.x,p.y,bbox.width,bbox.height+dy);
				break;
			case Cursor.NE_RESIZE_CURSOR:	
				dx=p.x-bbox.x-bbox.width;
				dy=bbox.y-p.y;
				bbox.setBounds(bbox.x,p.y,bbox.width+dx,bbox.height+dy);
				break;
			case Cursor.E_RESIZE_CURSOR:
				dx=p.x-bbox.x-bbox.width;
				bbox.setBounds(bbox.x,bbox.y,bbox.width+dx,bbox.height);
				break;
			case Cursor.SE_RESIZE_CURSOR:
				dx=p.x-bbox.x-bbox.width;
				dy=p.y-bbox.y-bbox.height;
				bbox.setBounds(bbox.x,bbox.y,bbox.width+dx,bbox.height+dy);
				break;
			case Cursor.S_RESIZE_CURSOR:
				dy=p.y-bbox.y-bbox.height;
				bbox.setBounds(bbox.x,bbox.y,bbox.width,bbox.height+dy);
				break;
			case Cursor.SW_RESIZE_CURSOR:
				dy=p.y-bbox.y-bbox.height;
				dx=bbox.x-p.x;
				bbox.setBounds(p.x,bbox.y,bbox.width+dx,bbox.height+dy);
				break;
			case Cursor.W_RESIZE_CURSOR:
				dx=bbox.x-p.x;
				bbox.setBounds(p.x,bbox.y,bbox.width+dx,bbox.height);
				break;
			}
		}
	}
	public Point getPosition() {
		return bbox.normalized().getLocation();
	}
}