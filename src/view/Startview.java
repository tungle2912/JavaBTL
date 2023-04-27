package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import utils.Constants;
import utils.Load;

public class Startview extends JPanel {
	private JLabel jlabel;
	private JButton btnPlay;
	private JButton btnList;
	private Image background;
	private Image imgText;
	JRadioButton easyRadioButton;
	JRadioButton mediumRadioButton;
	JRadioButton hardRadioButton;
	ButtonGroup buttonGroup;

	public Startview() {
		this.setBounds(0, 0, Constants.w_Frame, Constants.H_Frame);
		background = Load.getImage("startview.jpg");
		imgText = Load.getImage("spacewar.png");
		this.setLayout(null);
		this.setOpaque(false);
		easyRadioButton = new JRadioButton("Easy");
		easyRadioButton.setPreferredSize(new Dimension(100, 20));
		easyRadioButton.setBackground(Color.black);
		easyRadioButton.setForeground(Color.white);
		easyRadioButton.setSelected(true);

		mediumRadioButton = new JRadioButton("Medium");
		mediumRadioButton.setPreferredSize(new Dimension(100, 20));
		mediumRadioButton.setBackground(Color.black);
		mediumRadioButton.setForeground(Color.white);

		hardRadioButton = new JRadioButton("Hard");
		hardRadioButton.setPreferredSize(new Dimension(100, 20));
		hardRadioButton.setBackground(Color.black);
		hardRadioButton.setForeground(Color.white);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(easyRadioButton);
		buttonGroup.add(mediumRadioButton);
		buttonGroup.add(hardRadioButton);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("aria", Font.ITALIC, 16));
		btnPlay.setBackground(Color.black);
		btnPlay.setForeground(Color.red);
		panel.add(easyRadioButton);
		panel.add(mediumRadioButton);
		panel.add(hardRadioButton);
		panel.add(btnPlay);
		panel.setBounds(Constants.w_Frame / 2 - 75, Constants.H_Frame - 350, 150, 500);
		this.add(panel);
		btnList = new JButton();
		btnList.setIcon(new ImageIcon("src/data/img/cup.png"));
		btnList.setBounds(10, 10, 50, 50);
		this.add(btnList);

		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(new HistoryList());
				repaint();
				revalidate();
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, Constants.w_Frame, Constants.H_Frame, null);
		g.drawImage(imgText, Constants.w_Frame / 2 - imgText.getWidth(null) / 2, 200, null);
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	public JRadioButton getEasyRadioButton() {
		return easyRadioButton;
	}

	public void setEasyRadioButton(JRadioButton easyRadioButton) {
		this.easyRadioButton = easyRadioButton;
	}

	public JRadioButton getMediumRadioButton() {
		return mediumRadioButton;
	}

	public void setMediumRadioButton(JRadioButton mediumRadioButton) {
		this.mediumRadioButton = mediumRadioButton;
	}

	public JRadioButton getHardRadioButton() {
		return hardRadioButton;
	}

	public void setHardRadioButton(JRadioButton hardRadioButton) {
		this.hardRadioButton = hardRadioButton;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

}
