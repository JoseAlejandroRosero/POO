package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

public abstract class Figure implements Shape{ 
	
	private static final long serialVersionUID = 1L;
	private Point position;
	private Color color;
	private Dimension size;
	private boolean selected;

	private BoundBox bbox;
	private boolean resizing=true;
	
	protected Figure(Point position,Color color,Dimension size,BoundBox bbox){
		super();
		this.color = color;
		this.position = position;
		this.size = size;
		this.bbox=bbox;
		if(needsNormalization()){
			this.bbox.normalize();
		}
	}
	protected Figure(Point position,Dimension size){
		this(position,Color.BLACK,size,new BoundBox(position.x,position.y,size.width,size.height));
}
	protected Figure(Figure f){
		this(f.position,f.color,f.size,f.bbox);
	}
	protected Figure(BoundBox bbox){
		this(new Point(bbox.x,bbox.y),Color.BLACK,new Dimension(bbox.width,bbox.height),bbox);
	}
	public abstract void dopaint(final Graphics2D g);

	protected boolean needsNormalization(){
		return true;
	}
	public Point getPosition(){
		Point p= new Point(position);
		return p;
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
	public boolean contains(Point pt) {
		if(getNormalizedBoundBox().contains(pt)){
			return true;
		}
		return false;
	}
	public void setPosition(Point p) {
		this.position=p;
	}
	public void setFillcolor(Color c){}
	public void setBordercolor(Color color) {
		this.color=color;
	}
	public void setThickness(int thickness){}
	public Cursor getCursor(Point p) {
		return bbox.getCursor(p);
	}
	public List<Figure> getChildren() {
		return null;
	}
	public void move(Point p) {
		bbox.setPosition(p);
		position=bbox.getLocation();
	}
	public void processCursor(Cursor c,Point p) {
		int dy;
		int dx;
		if(resizing){
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
		if(bbox.isEmpty()){
			resizing=false;
		}
		if(!resizing){
			switch(c.getType()){
			case Cursor.NW_RESIZE_CURSOR:
				if(p.x<bbox.x && p.y<bbox.y){
					resizing = true;
				}
				break;
			case Cursor.N_RESIZE_CURSOR:
				if(p.y<bbox.y){
					resizing = true;
				}
				break;
			case Cursor.NE_RESIZE_CURSOR:
				if(p.x>bbox.x && p.y<bbox.y){
					resizing = true;
				}
				break;
			case Cursor.E_RESIZE_CURSOR:
				if(p.x>bbox.x){
					resizing = true;
				}
				break;
			case Cursor.SE_RESIZE_CURSOR:
				if(p.x>bbox.x && p.y>bbox.x){
					resizing = true;
				}
				break;
			case Cursor.S_RESIZE_CURSOR:
				if(p.y>bbox.y){
					resizing = true;
				}
				break;
			case Cursor.SW_RESIZE_CURSOR:
				if(p.y>bbox.y && p.x<bbox.x){
					resizing = true;
				}
				break;
			case Cursor.W_RESIZE_CURSOR:
				if( p.x<bbox.x){
					resizing = true;
				}
				break;
			}
		}
	}
}