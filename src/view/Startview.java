package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import untils.Container;
import untils.Load;

public class Startview  extends JPanel{
	private JLabel jlabel;
	private JButton btnPlay;
	private Image background;

	
	public Startview() {
		this.setBounds(0, 0, Container.w_Frame, Container.H_Frame);
		background =Load.getImage("startbackground1.jpg");
		this.setLayout(null);
		btnPlay=new JButton("Play");
		this.setOpaque(false);
		btnPlay.setBounds(600, 450, 100, 40);
		btnPlay.setBackground(Color.BLACK);
		btnPlay.setForeground(Color.white);
		btnPlay.setBorder(null);
		btnPlay.setBorderPainted(false);
		this.add(btnPlay);
	}
	@Override
	protected void paintComponent(Graphics g) {
	g.drawImage(background,0,0,Container.w_Frame,Container.H_Frame,null);
	g.setColor(Color.red);
	g.setFont(new Font("NewellsHand", Font.PLAIN, 60));
	g.drawString("Space War", 530, 200);
	}


	public JButton getBtnPlay() {
		return btnPlay;
	}


	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}
	

}
