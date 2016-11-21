package util;

import java.awt.Point;

import javax.swing.undo.AbstractUndoableEdit;

import model.Drawing;
import model.Figure;

public class MoveFigureEdit extends AbstractUndoableEdit {

	private static final long serialVersionUID = 1L;
	
	private Drawing model;
	private Figure f;
	private Point np;
	private Point fp;

	public MoveFigureEdit(Drawing model,Figure f, Point np, Point fp) {
		super();
		this.model=model;
		this.f = f;
		this.np = np;
		this.fp = fp;
	}
	
	@Override
	public boolean canUndo(){
		if(f==null || np==null || fp==null){
			return false;
		}
		return true;
	}
	@Override
	public void undo(){
		model.relocalize(f,fp);
	}
	
	@Override
	public boolean canRedo(){
		return canUndo();
	}
	@Override
	public void redo(){
		model.relocalize(f,np);
	}
}