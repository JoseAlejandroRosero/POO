package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JPanel;

import mediator.App;
import model.DrawingListener;
import model.Figure;
import util.DiamondCreationTool;
import util.EllipseCreationTool;
import util.LineCreationTool;
import util.RectangleCreationTool;
import util.SelectionTool;
import util.TextCreationTool;
import util.Tool;


public class Canvas extends JPanel implements DrawingListener{
	private static final long serialVersionUID = 1L;
	
	public static final int LINE_CREATION_TOOL=0;
	public static final int RECTANGLE_CREATION_TOOL=1;
	public static final int ELLIPSE_CREATION_TOOL=2;
	public static final int TEXT_CREATION_TOOL=3;
	public static final int DIAMOND_CREATION_TOOL=4;
	public static final int SELECTION_TOOL=5;
	
	private static final int NUM_TOOL= SELECTION_TOOL+1;
	
	private Tool[] tools;
	private Tool activeTool;
	
	public Canvas(){
		super(true);
		setBackground(Color.WHITE);
		this.tools= new Tool[NUM_TOOL];
		tools[LINE_CREATION_TOOL]= new LineCreationTool();
		tools[RECTANGLE_CREATION_TOOL]= new RectangleCreationTool();
		tools[ELLIPSE_CREATION_TOOL]= new EllipseCreationTool();
		tools[TEXT_CREATION_TOOL]= new TextCreationTool();
		tools[DIAMOND_CREATION_TOOL]=new DiamondCreationTool();
		tools[SELECTION_TOOL]= new SelectionTool();
		
		setActiveTool(ELLIPSE_CREATION_TOOL);
		
		addMouseListener( new MouseAdapter(){
			@Override
			public void mousePressed( MouseEvent e){
					activeTool.mousePressed(e);
			}
			@Override
			public void mouseReleased( MouseEvent e ){
					activeTool.mouseReleased(e);
			}
		});
			addMouseMotionListener(new MouseAdapter(){
				
				@Override
				public void mouseMoved(MouseEvent e){
					Canvas.super.setCursor(App.getInstance().getCursor(e.getPoint()));
				}
				
				@Override
				public void mouseDragged(MouseEvent e){
						activeTool.processMouseDragged(e.getPoint());
				}
				
			});
	}
	
	public void init(){
		
	App.getInstance().addDrawingListener(this);
	
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Iterator<Figure> it=App.getInstance().getIterator();
		while(it.hasNext()){
			it.next().paint(g);
		}
	}
	
	public void setActiveTool(int tool) {
		activeTool = tools[tool];
	}
	public void clean() {
		App.getInstance().clearList();
	}
	@Override
	public void update(final DrawingEvent ev) {
		if (ev != DrawingEvent.SAVED){
			repaint();
		}
	}
}
