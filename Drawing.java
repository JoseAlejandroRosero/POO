package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

import model.DrawingListener.DrawingEvent;
import util.AddFigureEdit;
import util.ColorEdit;
import util.FillcolorEdit;
import util.MoveFigureEdit;
import util.RemoveFigureEdit;

public class Drawing {
	
	
	private int numSelected;
	private boolean modified;
	private String pathname = "No Name";
	private UndoManager lastAction;
	private UndoableEditSupport support;
	private Action undoAction = new UndoAction();
	private Action redoAction = new RedoAction();
	private List<Figure> figures;
	private List<DrawingListener> listeners;
	private int thickness;
	
	public Drawing(List<Figure> figures) {
		this.figures = figures;
		listeners = new LinkedList<>();
		
		lastAction = new UndoManager();
		support = new UndoableEditSupport();
		support.addUndoableEditListener(new UndoAdapter());
		
	}
	
	public String getPathname() {
		return pathname;
	}

	private void setPathname(String path) {
		pathname = path;
	}

	public ListIterator<Figure> getListIterator() {
		return figures.listIterator(figures.size());
	}
	
	public Iterator<Figure> getIterator() {
		return figures.iterator();
	}
	
	public boolean isModified() {
		return modified;
	}
	
	
	public List<Figure> getSelected() {
		List<Figure> nl= new LinkedList<Figure>();
		for(Figure f : figures){
			if(f.isSelected()){
				nl.add(f);
			}
		}
		return nl;
	}
	
	public void refreshUndoRedo() {
		if(lastAction.canUndo()){
			notifyListeners(DrawingEvent.UNDOREDO);
		}
	}
	
	public void undo() {
		undoAction.actionPerformed(null);
		refreshUndoRedo();
	}
	
	public void redo() {
		redoAction.actionPerformed(null);
		refreshUndoRedo();
	}
	
	public void clearList() {
		figures.clear();
		lastAction.discardAllEdits();
		numSelected=0;
		notifyListeners( DrawingEvent.NEW );
	}
	
	public void fillColorChoosed(final Color formerColor,final Color newColor) {
		for(Figure f: getSelected()){
			f.setFillcolor(newColor);
		}
		UndoableEdit edit = new FillcolorEdit(this,formerColor,newColor);
		support.postEdit(edit);
		notifyListeners(DrawingEvent.CHANGED_FILLCOLOR);
		refreshUndoRedo();
	}
	
	public void borderColorChoosed(Color formercolor, Color newcolor) {
		for(Figure f : getSelected()){
			f.setBordercolor(newcolor);
		}
		UndoableEdit edit = new ColorEdit(this,formercolor,newcolor);
		support.postEdit(edit);
		notifyListeners(DrawingEvent.CHANGED_COLOR);
		refreshUndoRedo();
	}
	
	public void setFillcolor(Color Color) {
			for(Figure f : getSelected()){
				f.setFillcolor(Color);
			}
	}
	
	public void setBordercolor(Color color){
		for(Figure f : getSelected()){
			f.setBordercolor(color);
		}
	}

	public void addFigure(Figure f) {
		UndoableEdit edit = new AddFigureEdit(this,f,figures.size());
		f.setThickness(thickness);
		figures.add(f);
		support.postEdit(edit);
		notifyListeners( DrawingEvent.ADDED );
		refreshUndoRedo();
	}


	public void deleteSelected() {
		List<Figure> selected = getSelected();
		int[] Indexes=new int[selected.size()];
		int i=0;
		for(Figure f : selected){
				Indexes[i]=figures.indexOf(f);
				figures.remove(f);
				i++;
		}
		UndoableEdit edit = new RemoveFigureEdit(this,selected,Indexes);
		support.postEdit(edit);
		numSelected=0;
		refreshUndoRedo();
		notifyListeners( DrawingEvent.REMOVED );
	}
	public void select(Point p) {
		ListIterator<Figure> li= getListIterator();
		for(Figure f : figures){
			f.setSelected(false);
		}
		while(li.hasPrevious()){
			Figure f = li.previous();
			if(f.contains(p)){
				f.setSelected(true);
				break;
			}
		}
		numSelected=1;
		refreshUndoRedo();
		notifyListeners( DrawingEvent.SELECTED );
	}
	
	public void select(BoundBox selectedBoundBox) {
		ListIterator<Figure> li = getListIterator();
		while(li.hasPrevious()){
		    Figure f = li.previous();
			if(selectedBoundBox.intersects(f.getNormalizedBoundBox())){
				f.setSelected(true);
				numSelected+=1;
			}
		}
		refreshUndoRedo();
		notifyListeners( DrawingEvent.SELECTED );
	}

	public void selectAll() {
		for(Figure f : figures){
			f.setSelected(true);
		}
		numSelected=figures.size();
		notifyListeners( DrawingEvent.SELECTED );
	}
	
	public void deselectAll() {
		for(Figure f : figures){
			f.setSelected(false);
		}
		numSelected=0;
		notifyListeners( DrawingEvent.DESELECTED );
	}

	@SuppressWarnings("unchecked")
	public void load(ObjectInputStream ois,String filename) throws ClassNotFoundException, IOException {
		figures = (List<Figure>)ois.readObject();
		setPathname(filename);
		notifyListeners( DrawingEvent.LOADED);
	}
	
	public void save(ObjectOutputStream oos,String filename) throws IOException{
		oos.writeObject(figures);
		setPathname(filename);
		notifyListeners( DrawingEvent.SAVED );
	}
	
	
	public void addListener(DrawingListener dl){
		listeners.add(dl);
	}
	
	public void removeListener(DrawingListener dl){
		if(listeners.contains(dl)){
			listeners.remove(dl);
		}
	}

	public void removeFigureAt(int index) {
		figures.remove(index);
		numSelected--;
		notifyListeners(DrawingEvent.REMOVED);
		refreshUndoRedo();
	}

	public void insertFigureAt(int index, Figure element) {
		figures.add(index, element);
		numSelected++;
		notifyListeners(DrawingEvent.ADDED);
		refreshUndoRedo();
	}

	public void removeFigures(List<Figure> figures) {
		this.figures.removeAll(figures);
		notifyListeners(DrawingEvent.REMOVED);
		refreshUndoRedo();
	}
	
	public boolean thereAreSelected() {
		if(numSelected>0){
			return true;
		}
		return false;
	}
	
	
	public void resetPathname() {
		setPathname("No Name");
	}
	public void group() {
		List<Figure> children = getSelected();
		figures.add(new Group(children));
		figures.removeAll(children);
		notifyListeners(DrawingEvent.GROUP);
	}
	
	public void ungroup(){
		List<Figure> children=null;
		for(Figure f : getSelected()){
			children=f.getChildren();
			if(children!=null){
				figures.remove(f);
				break;
			}
		}
		if(children!=null){
			for(Figure f : children){
				f.setSelected(false);
				figures.add(f);
			}
			figures.get(figures.size()-1).setSelected(true);
			notifyListeners(DrawingEvent.UNGROUP);
		}
	}
	
	public Cursor getCursor(Point p) {
		Cursor c=null;
		for(Figure f: getSelected()){
			if(f.contains(p)){
				c=f.getCursor(p);
			}
		}
		return c;
	}
	
	public void move(Point p) {
		for(Figure f : getSelected()){
				f.move(p);
				break;
		}
		notifyListeners(DrawingEvent.MOVED);
	}
	
	public void setThickness(int t) {
		thickness=t;
		for(Figure f : getSelected()){
			f.setThickness(t);
		}
		notifyListeners(DrawingEvent.CHANGE_THICKNESS);
	}
	
	public void processCursor(Cursor c,Point p) {
		for(Figure f : getSelected()){
			if(numSelected==1){
				f.processCursor(c,p);
				break;
			}
		}
		notifyListeners(DrawingEvent.RESIZED);
	}
	public void relocalize(Figure f, Point p) {
		for(Figure e: figures){
			if(e==f){
				f.move(p);
			}
		}
		notifyListeners(DrawingEvent.MOVED);
	}
	
	public void fixedPosition(Point p,Point r) {
		int dx;
		int dy;
		for(Figure f : getSelected()){
			if(f.contains(r)){
				dx=r.x-f.getPosition().x;
				dy=r.y-f.getPosition().y;
				Point g = f.getPosition();
				UndoableEdit e = new MoveFigureEdit(this,f,new Point(p.x-dx,p.y-dy),g);
				support.postEdit(e);
				refreshUndoRedo();
			}
		}
	}

	
	private void notifyListeners(final DrawingEvent ev) {
		switch( ev ){
			case ADDED:
			case REMOVED:
			case MOVED:
			case CHANGED_FILLCOLOR:
			case CHANGED_COLOR:
			case RESIZED:
			case GROUP:
			case UNGROUP:
			case CHANGE_THICKNESS:	
				modified=true;
			case SAVED:
			case LOADED:
				modified=false;
			case UNDOREDO:
				if(lastAction.canUndo()){
					modified=true;
				}
				else{
					modified=false;
				}
		default:
			modified=true;
			break;
		}
		for(DrawingListener dl : listeners){
			dl.update(ev);
		}
	}
	
	private class RedoAction extends AbstractAction{
		
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lastAction.redo();
		}
		
	}
	
	private class UndoAdapter implements UndoableEditListener{
		
		@Override
		public void undoableEditHappened(UndoableEditEvent e) {
			UndoableEdit edit = e.getEdit();
			lastAction.addEdit(edit);
			refreshUndoRedo();
		}
		
	}
	
	private class UndoAction extends AbstractAction{
		
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lastAction.undo();
		}
		
	}
}

