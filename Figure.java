package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

public abstract class Figure implements Shape {

	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean selected;

	private BoundBox bbox;

	protected Figure(Color color, BoundBox bbox) {
		super();
		this.color = color;
		this.bbox = bbox;
		if (needsNormalization()) {
			this.bbox.normalize();
		}
	}

	protected Figure(Figure f) {
		this(f.color, f.bbox);
	}

	protected Figure(BoundBox bbox) {
		this(Color.BLACK, bbox);
	}

	public abstract void dopaint(final Graphics2D g);

	protected boolean needsNormalization() {
		return true;
	}
	
	public BoundBox getBoundBox() {
		return bbox;
	}

	public BoundBox getNormalizedBoundBox() {
		return bbox;
	}

	public Color getColor() {
		return color;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean b) {
		selected = b;
	}

	public void paint(final Graphics g) {
		g.setColor(color);
		dopaint((Graphics2D) g);
		if (isSelected()) {
			getNormalizedBoundBox().paint(g);
		}
	}

	public boolean contains(Point p) {
		return getNormalizedBoundBox().contains(p);
	}

	public void setFillcolor(Color c) {
	}

	public void setBordercolor(Color color) {
		this.color = color;
	}

	public void setThickness(int thickness) {
	}
	
	public List<Figure> getChildren() {
		return null;
	}

	public Point getPosition() {
		return bbox.normalized().getLocation();
	}

	public void move(int dx, int dy) {
		bbox.setPosition(dx, dy);
	}

	public ControlPoint getControlPoints(Point p) {
		return bbox.normalized().getControlPoint(p);
	}

	public void resize(Point p) {
		bbox.moveTo(p);
		bbox.normalize();
	}
}