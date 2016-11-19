package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;

public abstract class Tool {
	private Point ptPressed;
	private Point ptReleased;
	public abstract void processMouseReleased(MouseEvent e);
	public void mousePressed( MouseEvent e){
		ptReleased=ptPressed=e.getPoint();
	}
	public void mouseReleased( MouseEvent e ){
		ptReleased=e.getPoint();
		processMouseReleased(e);
	}
	protected Point getPtPressed() {
		return ptPressed;
	}
	protected Point getPtReleased() {
		return ptReleased;
	}
	public void processMouseDragged(Point p) {
		Graphics2D g=App.getInstance().getGraphics();
		g.setXORMode(Color.WHITE);
		
		drawFeedback(g);
		ptReleased=p;
		drawFeedback(g);
		
		g.dispose();
	}
	protected void drawFeedback(Graphics2D g) {
		int x = (ptPressed.x>ptReleased.x? ptReleased.x : ptPressed.x);
		int y = (ptPressed.y>ptReleased.y? ptReleased.y : ptPressed.y);
		g.drawRect(x, y, Math.abs(ptReleased.x-ptPressed.x), Math.abs(ptReleased.y-ptPressed.y));
	}
}
