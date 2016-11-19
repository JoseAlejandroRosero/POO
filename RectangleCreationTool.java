package util;

import java.awt.Point;

import model.BoundBox;
import model.Figure;
import model.Rectangle;

public class RectangleCreationTool extends CreationTool {

	@Override
	protected Figure createFigure( final Point p, final Point r) {
		Point pt = p;
		Point rt = r;
		return new Rectangle(new BoundBox(pt,rt));
	}
}
