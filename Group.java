package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

public class Group extends Figure {
	
	private List<Figure> children;

	protected Group(List<Figure> children) {
		super(new BoundBox());
		this.children=children;
		BoundBox bbox = getNormalizedBoundBox();
		
		for(Figure f : children){
			if(children.indexOf(f)==0){
				bbox.copy(f.getNormalizedBoundBox());
			}
			else{
				bbox.copy(bbox.union(f.getNormalizedBoundBox()));
			}
		}
		setSelected(true);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void dopaint(Graphics2D g) {
		for(Figure f : children){
			f.dopaint(g);
		}
	}
	
	@Override
	public List<Figure> getChildren(){
		return children;
	}
	@Override
	public void setFillcolor(Color c){
		for(Figure f : children){
			f.setFillcolor(c);
		}
	}
	@Override
	public void setBordercolor(Color c){
		for(Figure f : children){
			f.setBordercolor(c);
		}
	}
	@Override
	public void setThickness(int thickness){
		for(Figure f : children){
			f.setThickness(thickness);
		}
	}
	@Override
	public void processCursor(Cursor c, Point p){
		super.processCursor(c, p);
		for(Figure f: children){
			f.processCursor(c, p);
		}
	}
}
