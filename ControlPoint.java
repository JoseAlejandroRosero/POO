package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import model.BoundBox;
import model.Shape;

@SuppressWarnings({ "unused", "serial" })
public class ControlPoint extends Rectangle implements Shape{
	
	private BoundBox bbox;
	private Cardinal cardinal;
	private Cursor cursor;
	private Point position;
	
	public Cursor getCursor() {
		return cursor;
	}

	private static final Dimension SIZE = new Dimension(5,5);

	public ControlPoint(Cardinal cardinal,BoundBox bbox) {
		this.cardinal=cardinal;
		this.bbox=bbox;
		switch(cardinal.ordinal()){
		case 0:
			position= new Point(bbox.x-SIZE.width/2, bbox.y-SIZE.height/2);
			cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
			break;
		case 1:
			position = new Point(bbox.x+((bbox.width/2)-(SIZE.width/2)), bbox.y-SIZE.height/2);
			cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
			break;
		case 2:
			position = new Point(bbox.x+bbox.width-SIZE.width/2, bbox.y-SIZE.height/2);
			cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
			break;
		case 3:
			position = new Point(bbox.x+bbox.width-SIZE.width/2, bbox.y+((bbox.height/2)-(SIZE.height/2)));
			cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
			break;
		case 4:
			position = new Point(bbox.x+bbox.width-SIZE.width/2, bbox.y+(bbox.height-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
			break;
		case 5:
			position = new Point(bbox.x+((bbox.width/2)-(SIZE.width/2)), bbox.y+(bbox.height-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
			break;
		case 6:
			position = new Point(bbox.x-SIZE.width/2, bbox.y+(bbox.height-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
			break;
		case 7:
			position = new Point(bbox.x-SIZE.width/2, bbox.y+((bbox.height/2)-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		g.fillRect(position.x, position.y, SIZE.width, SIZE.height);
	}
	@Override
	public boolean contains(Point p){
		if(p.x>=position.x && p.x<=position.x+SIZE.width && p.y>=position.y && p.y<=position.y+SIZE.height){
			return true;
		}
		return false;
	}

	public void setPosition(int dx, int dy) {
		int x=position.x;
		int y=position.y;
		position=new Point(x-dx,y-dy);
		setLocation(position);
	}
}

