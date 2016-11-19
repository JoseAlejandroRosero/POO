package util;

import java.awt.Graphics2D;
import java.awt.Point;

import model.Diamond;
import model.Figure;

public class DiamondCreationTool extends CreationTool {
	
	@Override
	protected Figure createFigure(final Point p, final Point r) {
		Point first = p;
		Point second = r;
		return new Diamond(first,second);
	}
	
	@Override
	protected void drawFeedback(Graphics2D g) {
		Point p = getPtPressed();
		Point r = getPtReleased();
		int nPoints = 4;
		int x = (p.x>r.x? r.x : p.x);
		int y = (p.y>r.y? r.y : p.y);
		int width=Math.abs(p.x-r.x);
		int height=Math.abs(p.y-r.y);
		int [] xPoints = {x+width/2,x+width,x+width/2,x};
		int [] yPoints = {y,y+height/2,y+height,y+height/2};
		g.drawPolygon(xPoints, yPoints, nPoints);
	}
}

