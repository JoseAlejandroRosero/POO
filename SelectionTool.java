package util;

import java.awt.Point;
import java.awt.event.MouseEvent;

import mediator.App;
import model.BoundBox;

public class SelectionTool extends Tool {

	@Override
	public void processMouseReleased(MouseEvent e) {
		Point ptPressed=getPtPressed();
		Point ptReleased=getPtReleased();
		if (ptPressed.equals(ptReleased))App.getInstance().select(ptReleased);
		else{
			BoundBox selectedBoundBox= new BoundBox(ptPressed,ptReleased);
			App.getInstance().select(selectedBoundBox);
		}			
	}
}
