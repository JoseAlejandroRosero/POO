package util;

import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import model.Drawing;
import model.Figure;

public class RemoveFigureEdit extends AbstractUndoableEdit implements UndoableEdit {

	private static final long serialVersionUID = 1L;
	
	private Drawing model;
	private List<Figure> figures;
	private int[] indexes;

	public RemoveFigureEdit(Drawing model,List<Figure> figures,int[] Indexes) {
		super();
		this.model = model;
		this.figures = figures;
		this.indexes = Indexes;
	}
	
	@Override
	public boolean canUndo(){
		if(model != null || indexes.length>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canRedo(){
		return canUndo();
	}
	
	@Override
	public void undo() throws CannotUndoException  {
		for(int i=0;i<indexes.length;i++){
			model.insertFigureAt(indexes[i], figures.get(i));
		}
	}
	
	@Override
	public void redo(){
		model.removeFigures(figures);
	}
}
