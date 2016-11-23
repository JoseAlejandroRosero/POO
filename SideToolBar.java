package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import mediator.App;

@SuppressWarnings("serial")
public class SideToolBar extends JToolBar {
	
	private JButton undo = new JButton(new ImageIcon("Assets/undo.png"));
	private JButton redo = new JButton(new ImageIcon("Assets/redo.png"));
	private JButton line = new JButton(new ImageIcon("Assets/line.png"));
	private JButton rectangle = new JButton(new ImageIcon("Assets/rectangle.png"));
	private JButton ellipse = new JButton(new ImageIcon("Assets/ellipse.png"));
	private JButton text = new JButton(new ImageIcon("Assets/text.png"));
	private JButton diamond = new JButton(new ImageIcon("Assets/diamond.png"));
	private JButton select = new JButton(new ImageIcon("Assets/select.png"));
	private JButton group = new JButton(new ImageIcon("Assets/group.png"));
	private JButton ungroup = new JButton(new ImageIcon("Assets/ungroup.png"));

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
