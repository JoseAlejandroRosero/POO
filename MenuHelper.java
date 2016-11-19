package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mediator.App;
import model.DrawingListener;
import view.Canvas;

public class MenuHelper extends JMenuBar implements DrawingListener{

	private static final long serialVersionUID = 3144286073990267430L;
	public static final int N = 6;
	public static final int LINE = 0;
	public static final int RECTANGLE = 1;
	public static final int ELLIPSE = 2;
	public static final int TEXT = 3;
	public static final int TRIANGLE = 4;
	public static final int SELECT = 5;
	private static JMenuItem[] menutools=new JCheckBoxMenuItem[N];
	
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenu tool = new JMenu("Tools");
	private JMenu view = new JMenu("View");
	private JMenu options = new JMenu("Options");
	private JMenu help = new JMenu("Help");
	
	final JMenuItem fnew = new JMenuItem("New");
	final JMenuItem quit= new JMenuItem("Quit");
	
	final JMenuItem deselect= new JMenuItem("Deselect");
	final JMenuItem select_all= new JMenuItem("Select all");
	final JMenuItem undo = new JMenuItem("Undo");
	final JMenuItem redo = new JMenuItem("Redo");
	final JMenuItem cut = new JMenuItem("Cut");
	final JMenuItem copy = new JMenuItem("Copy");
	final JMenuItem paste = new JMenuItem("Paste");
	final JMenuItem deleteSelected = new JMenuItem("Delete Selected");
	
	final JMenuItem line = new JCheckBoxMenuItem("Line Creation Tool");
	final JMenuItem rectangle = new JCheckBoxMenuItem("Rectangle Creation Tool");
	final JMenuItem ellipse = new JCheckBoxMenuItem("Ellipse Creation Tool");
	final JMenuItem text = new JCheckBoxMenuItem("Text Creation Tool");
	final JMenuItem diamond = new JCheckBoxMenuItem("Diamond Creation Tool");
	final JMenuItem selection = new JCheckBoxMenuItem("Selection Tool");

	final JMenuItem color = new JMenuItem("Color");
	final JMenuItem fillcolor = new JMenuItem("Fillcolor");
	
	public MenuHelper() {
		
		final JMenuItem save = new JMenuItem("save");
		final JMenuItem saveAs = new JMenuItem("save as...");
		final JMenuItem load = new JMenuItem("load...");
		
		menutools[LINE]=line;
		menutools[RECTANGLE]=rectangle;
		menutools[ELLIPSE]=ellipse;
		menutools[TEXT]=text;
		menutools[TRIANGLE]=diamond;
		menutools[SELECT]=selection;
		
		fnew.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().cleanCanvas();
			}
			
		});
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().save();
			}
			
		});
		saveAs.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().doSave();
			}
			
		});
		load.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().doLoad();
			}
			
		});
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().quit();
			}
			
		});
		deselect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().deselectAll();
			}
			
		});
		select_all.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					App.getInstance().selectAll();
			}
			
		});
		undo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().undo();
			}
			
		});
		redo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().redo();
			}
			
		});
		deleteSelected.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				App.getInstance().deleteSelected();
				
			}
			
		});
		
		line.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.LINE_CREATION_TOOL);
				 cleanChecked(line);
			}
		});
		rectangle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				App.getInstance().setActiveTool(Canvas.RECTANGLE_CREATION_TOOL);
				cleanChecked(rectangle);
			}
		});
		ellipse.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.ELLIPSE_CREATION_TOOL);
				cleanChecked(ellipse);
			}
		});
		
		text.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.TEXT_CREATION_TOOL);
				cleanChecked(text);
			}
		});
		
		diamond.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.DIAMOND_CREATION_TOOL);
				cleanChecked(diamond);
			}
			
		});
		
		selection.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.SELECTION_TOOL);
				cleanChecked(selection);
			}
		});
		color.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().chooseBorderColor();
			}
			
		});
		fillcolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().chooseFillcolor();
			}
		});
		
		file.add(fnew);
		file.addSeparator();
		file.add(save);
		file.add(saveAs);
		file.add(load);
		file.addSeparator();
		file.add(quit);
		
		edit.add(deselect);
		edit.add(select_all);
		edit.addSeparator();
		edit.add(undo);
		edit.add(redo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(deleteSelected);
		
		tool.add(line);
		tool.add(rectangle);
		tool.add(ellipse);
		tool.add(text);
		tool.add(diamond);
		tool.addSeparator();
		tool.add(selection);
		
		options.add(fillcolor);
		options.add(color);
		
		ellipse.setSelected(true);
		
		add(file);
		add(edit);
		add(tool);
		add(options);
		add(view);
		add(help);
	}
	
	public void init(){
		
		cut.setEnabled(false);
		copy.setEnabled(false);
		paste.setEnabled(false);
		deleteSelected.setEnabled(false);
		undo.setEnabled(false);
		redo.setEnabled(false);
		
		App.getInstance().addDrawingListener(this);
		
	}

	@Override
	public void update(DrawingEvent ev) {		
		if(ev==DrawingEvent.SELECTED || ev==DrawingEvent.ADDED){
			if(App.getInstance().thereAreSelected()){
				cut.setEnabled(true);
				copy.setEnabled(true);
				deleteSelected.setEnabled(true);
			}
		}
		
		else if(ev==DrawingEvent.DESELECTED || ev==DrawingEvent.REMOVED){
			
			cut.setEnabled(false);
			copy.setEnabled(false);
			deleteSelected.setEnabled(false);
			
		}
		
		if(ev==DrawingEvent.ADDED_TO_CLIPBOARD){

			paste.setEnabled(true);
		
		}
		else if(ev==DrawingEvent.PASTED){
			
			paste.setEnabled(false);
			
		}
		if(ev==DrawingEvent.UNDOREDO){
			undo.setEnabled(true);
			redo.setEnabled(true);
		}
	}
	private void cleanChecked(final JMenuItem jm){
		for(int i=0;i<menutools.length;i++){
			if(!menutools[i].equals(jm)){
				menutools[i].setSelected(false);	
			}
		}
	}
}
