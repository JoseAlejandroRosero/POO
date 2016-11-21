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
	
	public Cursor getCursor() {
		return cursor;
	}

	private static final Dimension SIZE = new Dimension(5,5);

	public ControlPoint(Cardinal cardinal,BoundBox bbox) {
		super(0,0,SIZE.width,SIZE.height);
		this.cardinal=cardinal;
		this.bbox=bbox;
		updatePosition(bbox);
	}

	public void updatePosition(BoundBox bbox) {
		switch(cardinal.ordinal()){
		case 0:
			setLocation(bbox.x-SIZE.width/2, bbox.y-SIZE.height/2);
			cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
			break;
		case 1:
			setLocation(bbox.x+((bbox.width/2)-(SIZE.width/2)), bbox.y-SIZE.height/2);
			cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
			break;
		case 2:
			setLocation( bbox.x+bbox.width-SIZE.width/2, bbox.y-SIZE.height/2);
			cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
			break;
		case 3:
			setLocation( bbox.x+bbox.width-SIZE.width/2, bbox.y+((bbox.height/2)-(SIZE.height/2)));
			cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
			break;
		case 4:
			setLocation( bbox.x+bbox.width-SIZE.width/2, bbox.y+(bbox.height-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
			break;
		case 5:
			setLocation( bbox.x+((bbox.width/2)-(SIZE.width/2)), bbox.y+(bbox.height-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
			break;
		case 6:
			setLocation( bbox.x-SIZE.width/2, bbox.y+(bbox.height-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
			break;
		case 7:
			setLocation( bbox.x-SIZE.width/2, bbox.y+((bbox.height/2)-SIZE.height/2));
			cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		Point p=getLocation();
		g.fillRect(p.x, p.y, SIZE.width, SIZE.height);
	}
}

