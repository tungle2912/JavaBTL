package controller;

import java.awt.BorderLayout;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.*;
import utils.Constants;
import utils.Load;
import view.MyPanel;

public class GameManager {
	private MyPanel myPanel;
	private int count;
	private boolean check;
	private boolean checkwin;
	private int countEnemy;

	public GameManager(MyPanel myPanel) {
		this.myPanel = myPanel;
		initGame();
		countEnemy = 7;
	}

	public void initGame() {
		checkwin = false;
		check = true;
	}

	public void initEnemy() {
		if (myPanel.getPlane().getScore() < Constants.scoreCreatBoss) {
			for (int i = 0; i < countEnemy; i++) {
				myPanel.getArrEnemy().add(new Enemy());
			}
			countEnemy++;
			if (countEnemy >= 20) {
				countEnemy = 10;
			}
		} else {
			if (check) {
				myPanel.getArrEnemy().add(new Boss());
				myPanel.creatProgressBar();
				check = false;
			}

		}

	}

	public void restartGame() {
		myPanel.removeProgressBar();
		myPanel.writeFile();
		if (JOptionPane.showConfirmDialog(null, "Do you want to replay ?", "Game Over", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == 0) {
			initGame();
			myPanel.init();
		} else {
			System.exit(0);
		}
	}

	public void completeGame() {
		myPanel.removeProgressBar();
		myPanel.writeFile();
		JOptionPane pane = new JOptionPane("Do you want to replay ?", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION);
		JDialog dialog = pane.createDialog(null, "You Win");
		dialog.setLocation(Constants.w_Frame / 2 - pane.getWidth() / 2, Constants.H_Frame - 200);
		dialog.setVisible(true);
		if (pane.getValue().equals(JOptionPane.YES_OPTION)) {
			myPanel.removeWin();
			initGame();
			myPanel.init();
		} else {
			System.exit(0);
		}
	}

	public void checkCrash() {
		count++;
		if (count >= 500) {
			initEnemy();
			count = 0;
		}
		if (checkwin) {
			completeGame();
		}
		for (int i = 0; i < myPanel.getArrEnemy().size(); i++) {
			if (myPanel.getArrEnemy().get(i).disappear()) {
				myPanel.getArrEnemy().remove(i);
			}
		}
		for (int i = 0; i < myPanel.getArrEnemy().size(); i++) {
			myPanel.getArrEnemy().get(i).move();
			myPanel.getArrEnemy().get(i).addBoom(myPanel.getArrBoom());
			Rectangle r = myPanel.getArrEnemy().get(i).getRect().intersection(myPanel.getPlane().getRect());
			if (r.isEmpty() == false) {
				if (myPanel.getArrEnemy().get(i) instanceof Boss) {
					Load.getAudio("enemydie.wav");
					if (myPanel.getPlane().die()) {
						restartGame();

					}
				} else {
					Load.getAudio("enemydie.wav");
					myPanel.getArrEnemy().remove(i);
					if (myPanel.getPlane().die()) {
						restartGame();
					}
				}
			}

			for (int j = 0; j < myPanel.getArrBullet().size(); j++) {
				Rectangle rect = myPanel.getArrEnemy().get(i).getRect()
						.intersection(myPanel.getArrBullet().get(j).getRect());
				if (rect.isEmpty() == false) {
					if (myPanel.getArrEnemy().get(i) instanceof Boss) {
						myPanel.getArrEnemy().get(i).setX(myPanel.getArrEnemy().get(i).getX() - 5);
						;
						myPanel.getArrBullet().remove(j);
						if (myPanel.getArrEnemy().get(i).die()) {
							myPanel.win();
							checkwin = true;
							break;
						}
						myPanel.setValueProgressBar(
								(int) myPanel.getArrEnemy().get(myPanel.getArrEnemy().size() - 1).getHp());
						myPanel.getPlane().setScore(myPanel.getPlane().getScore() + 50);
					} else {
						myPanel.getArrBullet().remove(j);
						if (myPanel.getArrEnemy().get(i).die()) {
							if (myPanel.getArrEnemy().get(i).getK() == 1) {
								myPanel.getPlane().setScore(myPanel.getPlane().getScore() + 40);
							} else if (myPanel.getArrEnemy().get(i).getK() == 2) {
								myPanel.getPlane().setScore(myPanel.getPlane().getScore() + 50);
							} else {
								myPanel.getPlane().setScore(myPanel.getPlane().getScore() + 60);
							}
							myPanel.getArrEnemy().remove(i);
							break;
						}
					}

				}
			}

		}

		for (int i = 0; i < myPanel.getArrBoom().size(); i++) {
			myPanel.getArrBoom().get(i).move();
			if (myPanel.getArrBoom().get(i).disappear()) {
				myPanel.getArrBoom().remove(i);
			} else {
				Rectangle r = myPanel.getArrBoom().get(i).getRect().intersection(myPanel.getPlane().getRect());
				if (r.isEmpty() == false) {
					Load.getAudio("enemydie.wav");
					myPanel.getArrBoom().remove(i);
					if (myPanel.getPlane().die()) {
						restartGame();
					}
				}
			}
		}
		for (int i = 0; i < myPanel.getArrBullet().size(); i++) {
			myPanel.getArrBullet().get(i).move();
			if (myPanel.getArrBullet().get(i).disappear()) {
				myPanel.getArrBullet().remove(i);
			}
		}

	}

//	Runnable run = new Runnable() {
//		@Override
//		public void run() {
//			while (true) {
//				checkCrash();
//				repaint();
//				try {
//					Thread.sleep(12);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	};

}
