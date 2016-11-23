package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JPanel;

import mediator.App;
import model.ControlPoint;
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
	private Point pressed;
	
	public void setPressed(Point pressed) {
		this.pressed = pressed;
	}

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
				pressed=e.getPoint();
				if(getCursor()==Cursor.getDefaultCursor())activeTool.mousePressed(e);
			}
			@Override
			public void mouseReleased( MouseEvent e ){
				if(getCursor()==Cursor.getDefaultCursor())activeTool.mouseReleased(e);
			}
		});
			addMouseMotionListener(new MouseAdapter(){
				
				@Override
				public void mouseMoved(MouseEvent e){
					Point p=e.getPoint();
					ControlPoint cp=App.getInstance().getControlPoint(p);
					if(cp!=null)Canvas.super.setCursor(cp.getCursor());
					else{
						Figure f=App.getInstance().getFigureIn(p);
						if(f!=null)Canvas.super.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
						else {
							Canvas.super.setCursor(Cursor.getDefaultCursor());
						}
					}
				}
				
				@Override
				public void mouseDragged(MouseEvent e){
					Cursor c=getCursor();
					Point p=e.getPoint();
					if(c==Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)){
						int dx=p.x-pressed.x;
						int dy=p.y-pressed.y;
						Figure figure = App.getInstance().getFigureIn(p);
						if(figure!=null){
							App.getInstance().select(p);
							App.getInstance().move(dx, dy);
						}
						setPressed(p);
					}
					if(c==Cursor.getDefaultCursor())activeTool.processMouseDragged(e.getPoint());
					else{ 
						App.getInstance().select(p);
						App.getInstance().resize(p);
					}
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
