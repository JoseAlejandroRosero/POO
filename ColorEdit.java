package util;

import java.awt.Color;

import javax.swing.undo.AbstractUndoableEdit;

import mediator.App;
import model.Drawing;

public class ColorEdit extends AbstractUndoableEdit {

	private static final long serialVersionUID = 1L;

	private Color formerColor;
	private Color newColor;
	private Drawing model;
	
	public ColorEdit(Drawing model,Color formerColor,Color newColor) {
		this.model=model;
		this.formerColor=formerColor;
		this.newColor=newColor;
	}
	
	@Override
	public boolean canUndo(){
		if(formerColor!=null){
			return true;
		}
		return false;
	}
	@Override
	public void undo(){
		App.getInstance().setBordercolor(formerColor);
		model.setBordercolor(formerColor);
	}
	@Override
	public boolean canRedo(){
		if(newColor==null){
			return false;
		}
		return true;
	}
	@Override
	public void redo(){
		App.getInstance().setBordercolor(newColor);
		model.setBordercolor(newColor);
	}

}
