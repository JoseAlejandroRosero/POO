package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class Text extends Figure {

	private static final long serialVersionUID = 1L;
	
	private Font font;
	private String string;
	
	public Text(Color color,Font font,String string, BoundBox bbox) {
		super( color,bbox);
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
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		g.setClip(bbox.x, bbox.y, bbox.width, bbox.height);
		if(string!=null){
			g.drawString(string,bbox.x, bbox.y+fm.getAscent());
		}
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
