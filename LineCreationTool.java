package util;

import java.awt.Graphics2D;
import java.awt.Point;

import model.Figure;
import model.Line;

public class LineCreationTool extends CreationTool{

	@Override
	protected Figure createFigure(final Point p, final Point r) {
		return new Line(p,r);
	}
	
	@Override
	protected void drawFeedback(Graphics2D g){
		Point p=getPtPressed();
		Point r=getPtReleased();
		int x=p.x;
		int y=p.y;
		int w=-(p.x-r.x);
		int h=-(p.y-r.y);
		g.drawLine(x, y, x+w,y+h);
	}
}
