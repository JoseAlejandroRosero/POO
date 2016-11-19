package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mediator.App;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private JButton save = new JButton(new ImageIcon("Assets/save.png"));
	private JButton saveAs = new JButton(new ImageIcon("Assets/saveas.png"));
	private JButton load = new JButton(new ImageIcon("Assets/load.png"));
	private JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
	private JLabel label= new JLabel("Thickness");
	private JButton bordercolor = new JButton("Border Color",new ImageIcon("Assets/bordercolor.png"));
	private JButton fillcolor = new JButton("Fill Color",new ImageIcon("Assets/fillcolor.png"));
	private JButton delete = new JButton("Delete Selected",new ImageIcon("Assets/delete.png"));
	
	public ToolBar() {
	super();
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
	spinner.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent e) {
			Number v = (Number)spinner.getModel().getValue();
			App.getInstance().setThickness(v.intValue());
		}
		
	});
	bordercolor.addActionListener(new ActionListener() {
		
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
	delete.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			App.getInstance().deleteSelected();
		}
	});
	
	add(save);
	add(saveAs);
	add(load);
	addSeparator();
	add(label);
	add(spinner);
	addSeparator();
	add(delete);
	addSeparator();
	add(bordercolor);
	add(fillcolor);
	}
}
