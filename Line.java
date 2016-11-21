package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
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
		BoundBox bbox =getBoundBox();
		g.setStroke(new BasicStroke(getThickness()));
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
	public void processCursor(Cursor c, Point p){
		BoundBox bbox = getBoundBox();
		if(bbox.width>0 && bbox.height>0){
			super.processCursor(c, p);
		}
		else if(bbox.width<0 && bbox.height>0){
			int dy;
			int dx;
			if(isResizable()){
				switch(c.getType()){
				case Cursor.NW_RESIZE_CURSOR:
					dy=bbox.y-p.y;
					dx=bbox.x-p.x;
					bbox.setBounds(bbox.x,p.y,-dx,bbox.height+dy);
					break;
				case Cursor.N_RESIZE_CURSOR:
					dy=bbox.y-p.y;
					bbox.setBounds(bbox.x,p.y,bbox.width,bbox.height+dy);
					break;
				case Cursor.NE_RESIZE_CURSOR:	
					dx=p.x-bbox.x;
					dy=bbox.y-p.y;
					bbox.setBounds(p.x,p.y,bbox.width-dx,bbox.height+dy);
					break;
				case Cursor.E_RESIZE_CURSOR:
					dx=bbox.x-p.x;
					bbox.setBounds(p.x,bbox.y,bbox.width+dx,bbox.height);
					break;
				case Cursor.SE_RESIZE_CURSOR:
					dy=p.y-bbox.y-bbox.height;
					dx=bbox.x-p.x;
					bbox.setBounds(p.x,bbox.y,bbox.width+dx,bbox.height+dy);
					break;
				case Cursor.S_RESIZE_CURSOR:
					dy=p.y-bbox.y-bbox.height;
					bbox.setBounds(bbox.x,bbox.y,bbox.width,bbox.height+dy);
					break;
				case Cursor.SW_RESIZE_CURSOR:
					dx=p.x-bbox.x-bbox.width;
					dy=p.y-bbox.y-bbox.height;
					bbox.setBounds(bbox.x,bbox.y,bbox.width+dx,bbox.height+dy);
					break;
				case Cursor.W_RESIZE_CURSOR:
					dx=bbox.x-p.x;
					bbox.setBounds(bbox.x,bbox.y,-dx,bbox.height);
					break;
				}
			}
		}
		else if(bbox.width>0 && bbox.height<0){
			int dy;
			int dx;
			if(isResizable()){
				switch(c.getType()){
				case Cursor.NW_RESIZE_CURSOR:
					dy=bbox.y-p.y;
					dx=bbox.x-p.x;
					bbox.setBounds(p.x,bbox.y,bbox.width+dx,-dy);
					break;
				case Cursor.N_RESIZE_CURSOR:
					dy=bbox.y-p.y;
					bbox.setBounds(bbox.x,bbox.y,bbox.width,-dy);
					break;
				case Cursor.NE_RESIZE_CURSOR:	
					dx=p.x-bbox.x;
					dy=bbox.y-p.y;
					bbox.setBounds(bbox.x,bbox.y,dx,-dy);
					break;
				case Cursor.E_RESIZE_CURSOR:
					dx=p.x-bbox.x;
					bbox.setBounds(bbox.x,bbox.y,dx,bbox.height);
					break;
				case Cursor.SE_RESIZE_CURSOR:
					dy=p.y-bbox.y;
					dx=p.x-bbox.x;
					bbox.setBounds(bbox.x,p.y,dx,bbox.height-dy);
					break;
				case Cursor.S_RESIZE_CURSOR:
					dy=p.y-bbox.y;
					bbox.setBounds(bbox.x,p.y,bbox.width,bbox.height-dy);
					break;
				case Cursor.SW_RESIZE_CURSOR:
					dx=bbox.x-p.x;
					dy=p.y-bbox.y;
					bbox.setBounds(p.x,p.y,bbox.width+dx,bbox.height-dy);
					break;
				case Cursor.W_RESIZE_CURSOR:
					dx=bbox.x-p.x;
					bbox.setBounds(p.x,bbox.y,bbox.width+dx,bbox.height);
					break;
				}
			}
		}
		else if(bbox.width<0 && bbox.height<0){
			bbox.normalize();
			super.processCursor(c, p);
		}
	}	
}

