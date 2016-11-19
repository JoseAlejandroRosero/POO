package util;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

import mediator.App;
import model.Figure;

public abstract class CreationTool extends Tool {
	
	protected abstract Figure createFigure(Point p, Point r);
	
	@Override
	public void processMouseReleased(MouseEvent e){
		Point p=getPtPressed();
		Point r=getPtReleased();
		if(p.equals(r)){
			;
		}
		else{
			clean();
			Figure f = createFigure(getPtPressed(),getPtReleased());
			if(f!=null){
				App.getInstance().addFigure(f);
			}
		}
		}
	
	public void clean(){
		ListIterator<Figure> li = App.getInstance().getListIterator();
		while(li.hasPrevious()){
			Figure f = li.previous();
			f.setSelected(false);
		}
	}
	@Override
	protected void drawFeedback(Graphics2D g) {
		Point p = getPtPressed();
		Point r = getPtReleased();
		int x = (p.x>r.x? r.x : p.x);
		int y = (p.y>r.y? r.y : p.y);
		g.drawRect(x, y, Math.abs(p.x-r.x), Math.abs(p.y-r.y));
		}
}
