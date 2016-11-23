package util;

import java.awt.Point;

import javax.swing.undo.AbstractUndoableEdit;

import model.Drawing;
import model.Figure;

public class MoveFigureEdit extends AbstractUndoableEdit {


	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Drawing model;
	private Figure f;
	private Point np;
	private Point fp;

	public MoveFigureEdit(Drawing model, Point np, Point fp) {
		super();
		this.model=model;
		this.np = np;
		this.fp = fp;
	}
	
	@Override
	public boolean canUndo(){
		if(f==null || fp==null || np==null){
			return false;
		}
		return true;
	}
	@Override
	public void undo(){
	}
	
	@Override
	public boolean canRedo(){
		return canUndo();
	}
	@Override
	public void redo(){
	}
}