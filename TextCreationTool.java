package util;

import java.awt.Point;

import model.BoundBox;
import model.Figure;
import model.Text;

public class TextCreationTool extends CreationTool {

	@Override
	protected Figure createFigure(final Point p, final Point r) {
		Point pt = p;
		Point rt = r;
		return new Text(new BoundBox(pt,rt));
	}

}
