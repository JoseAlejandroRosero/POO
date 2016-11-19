package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import mediator.App;

@SuppressWarnings("serial")
public class SideToolBar extends JToolBar {
	
	private JButton undo = new JButton("Undo",new ImageIcon("Assets/undo.png"));
	private JButton redo = new JButton("Redo",new ImageIcon("Assets/redo.png"));
	private JButton line = new JButton("Line",new ImageIcon("Assets/line.png"));
	private JButton rectangle = new JButton("Rectangle",new ImageIcon("Assets/rectangle.png"));
	private JButton ellipse = new JButton("Ellipse",new ImageIcon("Assets/ellipse.png"));
	private JButton text = new JButton("Text",new ImageIcon("Assets/text.png"));
	private JButton diamond = new JButton("Diamond",new ImageIcon("Assets/diamond.png"));
	private JButton select = new JButton("Selection",new ImageIcon("Assets/select.png"));
	private JButton group = new JButton("Group",new ImageIcon("Assets/group.png"));
	private JButton ungroup = new JButton("Ungroup",new ImageIcon("Assets/ungroup.png"));

	public SideToolBar() {
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().undo();
			}
		});
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().redo();
			}
		});
		line.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.LINE_CREATION_TOOL);
			}
			
		});
		rectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.RECTANGLE_CREATION_TOOL);
			}
		});
		ellipse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.ELLIPSE_CREATION_TOOL);
			}
		});
		text.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.TEXT_CREATION_TOOL);
			}
		});
		diamond.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.DIAMOND_CREATION_TOOL);
			}
			
		});
		select.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setActiveTool(Canvas.SELECTION_TOOL);
			}
			
		});
		group.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().group();
			}
		});
		ungroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().ungroup();
			}
		});
		
		add(line);
		add(rectangle);
		add(ellipse);
		add(text);
		add(diamond);
		add(select);
		addSeparator();
		add(undo);
		add(redo);
		addSeparator();
		add(group);
		add(ungroup);
		
		setOrientation(JToolBar.VERTICAL);
	}

}
