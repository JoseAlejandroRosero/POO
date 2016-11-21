package util;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;
import model.BoundBox;

public class SelectionTool extends Tool {

	@Override
	public void processMouseReleased(MouseEvent e) {
		Point ptPressed=getPtPressed();
		Point ptReleased=e.getPoint();
		if(App.getInstance().getMyCursor()==Cursor.getDefaultCursor()){
			if (ptPressed.equals(ptReleased)){
				App.getInstance().select(ptReleased);
			}
			else{
				BoundBox selectedBoundBox= new BoundBox(ptPressed,ptReleased);
				App.getInstance().select(selectedBoundBox);
			}			
		}
		else if(App.getInstance().getMyCursor()==Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)){
			App.getInstance().fixedPosition(ptPressed,ptReleased);
		}
		else{
			App.getInstance().processCursor(App.getInstance().getMyCursor(), ptReleased);
		}
	}
	@Override
	protected void drawFeedback(Graphics2D g) {
		Point ptReleased=getPtReleased();
		if(App.getInstance().getMyCursor()==Cursor.getDefaultCursor()){
			super.drawFeedback(g);
		}
		else if(App.getInstance().getMyCursor()==Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)){
			App.getInstance().move(ptReleased);
		}
		else{
			App.getInstance().processCursor(App.getInstance().getMyCursor(), ptReleased);
		}
	}
}
