package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JOptionPane;

public class Text extends Figure {

	private static final long serialVersionUID = 1L;
	
	private Font font;
	private String string;
	
	public Text(Point position, Color color, Dimension size,Font font,String string, BoundBox bbox) {
		super(position, color, size,bbox);
		this.font=font;
		this.string=string;
	}
	
	public Text(BoundBox bbox){
		super(bbox);
		string=JOptionPane.showInputDialog("Escribe el texto:");
		
	}

	@Override
	public void dopaint(final Graphics2D g) {
		BoundBox bbox = getNormalizedBoundBox();
		FontMetrics fm = g.getFontMetrics();
		Point p= getPosition();
		g.setFont(font);
		g.setClip(bbox.x, bbox.y, bbox.width, bbox.height);
		g.drawString(string,p.x, p.y+fm.getAscent());
		g.setClip(null);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
