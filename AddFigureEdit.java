package util;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import model.Drawing;
import model.Figure;

public class AddFigureEdit extends AbstractUndoableEdit{
	
	private static final long serialVersionUID = 1L;
	private Drawing model;
	private Figure element;
	private int index;
	

	public AddFigureEdit(Drawing model, Figure element, int index) {
		super();
		this.model = model;
		this.element = element;
		this.index = index;
	}

	@Override
	public void undo() throws CannotUndoException {
		model.removeFigureAt(index);
	}

	@Override
	public boolean canUndo() {
		if(model != null && element != null && index >=0){
			return true;
		}
		return false;
	}

	@Override
	public void redo() throws CannotRedoException {
		model.insertFigureAt(index,element);
	}

	@Override
	public boolean canRedo() {
		return canUndo();
	}
	
}
