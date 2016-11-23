package model;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import mediator.App;
import view.Cardinal;

@SuppressWarnings("serial")
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

	public void moveTo(Point p) {
		int dy;
		int dx;
		if(App.getInstance().getMyCursor()==getCursor())
		switch (cardinal) {
		case NORTHWEST:
			dy = bbox.y - p.y;
			dx = bbox.x - p.x;
			bbox.setBounds(p.x, p.y, bbox.width + dx, bbox.height + dy);
			break;
		case NORTH:
			dy = bbox.y - p.y;
			bbox.setBounds(bbox.x, p.y, bbox.width, bbox.height + dy);
			break;
		case NORTHEAST:
			dx = p.x - bbox.x - bbox.width;
			dy = bbox.y - p.y;
			bbox.setBounds(bbox.x, p.y, bbox.width + dx, bbox.height + dy);
			break;
		case EAST:
			dx = p.x - bbox.x - bbox.width;
			bbox.setBounds(bbox.x, bbox.y, bbox.width + dx, bbox.height);
			break;
		case SOUTHEAST:
			dx = p.x - bbox.x - bbox.width;
			dy = p.y - bbox.y - bbox.height;
			bbox.setBounds(bbox.x, bbox.y, bbox.width + dx, bbox.height + dy);
			break;
		case SOUTH:
			dy = p.y - bbox.y - bbox.height;
			bbox.setBounds(bbox.x, bbox.y, bbox.width, bbox.height + dy);
			break;
		case SOUTHWEST:
			dy = p.y - bbox.y - bbox.height;
			dx = bbox.x - p.x;
			bbox.setBounds(p.x, bbox.y, bbox.width + dx, bbox.height + dy);
			break;
		case WEST:
			dx = bbox.x - p.x;
			bbox.setBounds(p.x, bbox.y, bbox.width + dx, bbox.height);
			break;
		}
	}
}

