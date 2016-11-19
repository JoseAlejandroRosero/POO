package util;

import java.awt.Graphics2D;
import java.awt.Point;

import model.BoundBox;
import model.Ellipse;
import model.Figure;

public class EllipseCreationTool extends CreationTool {

	@Override
	protected Figure createFigure(Point p, Point r) {
		Point pt = p;
		Point rt = r;
		return new Ellipse(new BoundBox(pt,rt));
	}
	
	@Override
	protected void drawFeedback(Graphics2D g) {
		Point p = getPtPressed();
		Point r = getPtReleased();
		int x = (p.x>r.x? r.x : p.x);
		int y = (p.y>r.y? r.y : p.y);
		g.drawOval(x, y, Math.abs(p.x-r.x), Math.abs(p.y-r.y));
		}

}
