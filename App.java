package mediator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BoundBox;
import model.Drawing;
import model.DrawingListener;
import model.Figure;
import view.View;

public class App{
	
	public static final String TITLE = "Editor Gráfico v0.1-";

	private static App instance;
	private static final String EXT=".eg";
	
	private Drawing model;
	private View view;
	private Color fillcolor=null;
	private Color bordercolor=Color.BLACK;
	private int thickness;
	private File f=null;
	
	public static App getInstance(){
		if(instance==null){
			instance = new App();
		}
		return instance;	
	}
	
	private App(){
		view = new View();
		model= new Drawing(new LinkedList<Figure>());
	}

	public void addFigure(Figure f) {
		f.setThickness(thickness);
		f.setFillcolor(fillcolor);
		f.setBordercolor(bordercolor);
		model.addFigure(f);
	}
	public void chooseBorderColor() {
		Color newcolor=JColorChooser.showDialog(view, "Eliga un color", Color.BLACK);
		Color formercolor = bordercolor;
		if(newcolor!=null){
			model.borderColorChoosed(formercolor,newcolor);
			bordercolor=newcolor;
		}
	}

	public ListIterator<Figure> getListIterator() {
		return model.getListIterator();
	}

	public void cleanCanvas() {
		view.cleanCanvas();
	}
	
	public boolean doSave() {
		boolean b=true;
		JFileChooser chooser = new JFileChooser();
		int r=chooser.showSaveDialog(view);
		if(r==JFileChooser.APPROVE_OPTION){
			File f = chooser.getSelectedFile();					
			ObjectOutputStream oos=null;
			try{
				String filename = f.getAbsolutePath();
				if(filename.indexOf(EXT)<0){
					filename+=EXT;
					f=new File(filename);
				}
				if(f.exists() &&  f.canWrite()){
					int a=JOptionPane.showConfirmDialog(view, "Quiere sobreescribir el archivo?");
					if(a==JOptionPane.YES_OPTION){						
						oos = new ObjectOutputStream(new FileOutputStream(f));
						model.save(oos,filename);
						this.f=f;
					}
					else if(a==JOptionPane.NO_OPTION){
						return doSave();
					}
					else{
						return false;
					}
				}
				else{
					oos = new ObjectOutputStream(new FileOutputStream(f));
					model.save(oos,filename);
					this.f=f;
				}
			}
			catch(Exception e){			
				b=handleSaveError();
			}
			finally{
				try {
					oos.close();
				} 
				catch (Exception e) {
				}
			}
		}
		else{
			return false;
		}
		return b;
	}
	
	private boolean handleSaveError() {
		boolean b=false;
		int a=JOptionPane.showConfirmDialog(view, "Ha ocurrido un error.\n"+" desea reintentar?");
		if(a==JOptionPane.YES_OPTION){
			b = save();
		}
		else if(a==JOptionPane.CANCEL_OPTION){
			return b;
		}
		else{
			b=true;
		}
		return b;
	}

	public boolean save() {
		ObjectOutputStream oos=null;
		boolean b=true;
		if(f==null){
			return doSave();
		}
		else{
			try{				
				oos = new ObjectOutputStream(new FileOutputStream(f));
				model.save(oos,f.getAbsolutePath());
			}
			catch(Exception e){			
				b=handleSaveError();
			}
			finally{
				try {
					oos.close();
				} 
				catch (Exception e) {
				}
			}
		}
		return b;
	}
	
	public  void doLoad() {
		if(model.isModified()){
			int r=JOptionPane.showConfirmDialog(view, "Quieres guardar el documento modificado?");
			if(r==JOptionPane.YES_OPTION){
				boolean b=doSave();
				if(!b){
					return;
				}
			}
			else if(r==JOptionPane.CANCEL_OPTION){
				return;
			}
		}
		JFileChooser chooser = new JFileChooser();
		int e=chooser.showOpenDialog(view);
		if(e==JFileChooser.APPROVE_OPTION){
			File f = chooser.getSelectedFile();
			ObjectInputStream ois = null;
			try{
				if(f.exists() && f.canRead()){
					ois=new ObjectInputStream(new FileInputStream(f));
					model.load(ois,f.getAbsolutePath());
					this.f=f;
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(view, "No se pudo cargar el archivo o no es un archivo válido");
			}
			finally{
				try {
					ois.close();
				} catch (IOException ex) {
				}
			}
		}
	}	
	

	public void addDrawingListener(DrawingListener dl) {
		model.addListener(dl);
	}

	public Iterator<Figure> getIterator() {
		return model.getIterator();
	}

	public void clearList() {
		if(model.isModified()){
			int a=JOptionPane.showConfirmDialog(view, "Quieres guardar el dibujo antes de crear uno nuevo");
			if(a==JOptionPane.YES_OPTION){
				getInstance().doSave();
			}
		}
		model.resetPathname();
		model.clearList();
	}

	public void quit() {
		boolean okToQuit=true;
		if(model.isModified()){
			int a = JOptionPane.showConfirmDialog(view, "El documento ha sido modificado.\n"+
		"Desea guardarlo antes de cerrar el programa?");
			if(a==JOptionPane.YES_OPTION){
				okToQuit=save();
			}
			else if(a==JOptionPane.CANCEL_OPTION){
				okToQuit=false;
			}
		}
		if(okToQuit){
			System.exit( 0 );
		}
	}

	public void deselectAll() {
		model.deselectAll();
	}

	public void selectAll() {
		model.selectAll();
	}

	public void undo() {
		model.undo();
	}

	public void redo() {
		model.redo();
	}

	public void deleteSelected() {
		model.deleteSelected();
	}

	public void setActiveTool(int tool) {
		view.setActiveTool(tool);
	}

	public boolean thereAreSelected() {
		return model.thereAreSelected();
	}

	public void select(Point p) {
		model.select(p);
	}

	public void select(BoundBox bbox) {
		model.select(bbox);
	}

	public String getPathname() {
		return model.getPathname();
	}
	
	private void run() {
		view.setSize(600, 600);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.init();
		view.setVisible(true);
	}
	
	public static void main(String[] args){
		App.getInstance().run();
	}
	public void chooseFillcolor() {
		Color newColor = JColorChooser.showDialog(view, "Eliga un color de relleno", Color.BLACK);
		Color formerColor = fillcolor;
		if(newColor!=null){
			fillcolor=newColor;
			model.fillColorChoosed(formerColor,newColor);
		}
	}

	public void setFillcolor(Color c) {
		fillcolor=c;
	}

	public void setBordercolor(Color c) {
		bordercolor=c;
	}

	public void setThickness(int t) {
		model.setThickness(t);
	}
	public Cursor getMyCursor() {
		return view.getMyCursor();
	}

	public Graphics2D getGraphics() {
		return view.getMyGraphics();
	}

	public void move(Point p) {
		model.move(p);
	}

	public void group() {
		model.group();
	}

	public void ungroup() {
		model.ungroup();
	}

	public void processCursor(Cursor c,Point p) {
		model.processCursor(c,p);
	}

	public Cursor getCursor(Point p) {
		return model.getCursor(p);
	}

	public void fixLocation(Point p) {
		model.fixLocation(p);
	}

	public int getNumSelected() {
		return model.getNumSelected();
	}
}