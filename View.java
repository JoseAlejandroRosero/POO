package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import mediator.App;
import model.DrawingListener;
import util.MenuHelper;

public class View extends JFrame implements DrawingListener{
	private static final long serialVersionUID = 2118299654730994785L;
	
      private static final Canvas CANVAS = new Canvas();
      private static final MenuHelper MENU_HELPER= new MenuHelper();
      private static final ToolBar TOOLBAR = new ToolBar();
      private static final SideToolBar SIDETOOLBAR = new SideToolBar();
      
	public View(){
		super();
		setTitle(App.TITLE+"No Name");
		setJMenuBar(MENU_HELPER);
		add(CANVAS);
		TOOLBAR.setFloatable(false);
		add(TOOLBAR,BorderLayout.NORTH);
		add(SIDETOOLBAR,BorderLayout.WEST);
	}
	
	public void init(){
		CANVAS.init();
		MENU_HELPER.init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		App.getInstance().addDrawingListener(this);
	}
	
	public void setActiveTool(int tool) {
		CANVAS.setActiveTool(tool);
	}
	public void cleanCanvas() {
		CANVAS.clean();
	}

	@Override
	public void update(DrawingEvent ev) {
		if(ev==DrawingEvent.SAVED || ev==DrawingEvent.LOADED || ev==DrawingEvent.NEW){
			setTitle(App.TITLE+App.getInstance().getPathname());
		}
		else if(ev==DrawingEvent.SELECTED || ev==DrawingEvent.DESELECTED){}
		else{
			setTitle(App.TITLE+App.getInstance().getPathname()+'*');
		}
	}

	public Graphics2D getMyGraphics() {
		return (Graphics2D)CANVAS.getGraphics();
	}

	public Cursor getMyCursor() {
		return CANVAS.getCursor();
	}
}
