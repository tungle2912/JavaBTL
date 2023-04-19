package view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.*;
import utils.Constants;
import utils.Load;

public class MyFrame extends JFrame{
	 public MyFrame() {
	        setTitle("air attack");
	        setSize(Constants.w_Frame,Constants.H_Frame);
	        setLocationRelativeTo(null);
	        setResizable(false);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        setIconImage(Load.getImage("spaceship.png"));
	        Startview starview=new Startview();
	        this.add(starview);
	        starview.getBtnPlay().addActionListener( new ActionListener() {  
				@Override
				public void actionPerformed(ActionEvent e) {
				    GameManager g = new GameManager();
					add(g);
					starview.getBtnPlay().setVisible(false);
					repaint();
					revalidate();
					BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
					Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
					setCursor(blankCursor);
				}

			});
	    }

}
