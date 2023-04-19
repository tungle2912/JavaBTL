package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.Constants;
import utils.Load;

public class Startview  extends JPanel{
	private JLabel jlabel;
	private JButton btnPlay;
	private Image background;
	private Image imgText;

	
	public Startview() {
		this.setBounds(0, 0, Constants.w_Frame, Constants.H_Frame);
		background =Load.getImage("startbackground1.jpg");
		imgText=Load.getImage("spacewar.png");
		this.setLayout(null);
		this.setOpaque(false);
		btnPlay=new JButton("Play");
		btnPlay.setBounds(Constants.w_Frame/2-50, Constants.H_Frame-200, 100, 40);
		btnPlay.setBackground(Color.black);
		btnPlay.setForeground(Color.white);
		btnPlay.setBorder(null);
		btnPlay.setBorderPainted(false);
		btnPlay.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.add(btnPlay);
	}
	@Override
	protected void paintComponent(Graphics g) {
	g.drawImage(background,0,0,Constants.w_Frame,Constants.H_Frame,null);
//	g.setColor(Color.red);
//	g.setFont(new Font("NewellsHand", Font.PLAIN, 60));
//	g.drawString("Space War",Constants.w_Frame/2-150, 200);
	g.drawImage(imgText, Constants.w_Frame/2-imgText.getWidth(null)/2,200, null);
	}


	public JButton getBtnPlay() {
		return btnPlay;
	}


	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}
	

}
