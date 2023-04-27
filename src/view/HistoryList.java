package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HistoryList extends JPanel {
	FileReader fr;
	BufferedReader br;
	List<String> scores;

	public HistoryList() {
		readFile("src/data/text/diem.txt");
		List<String> lastTenScores = scores.subList(Math.max(scores.size() - 10, 0), scores.size());
		JList<String> list;
//		list.setCellRenderer(new SpacedListCellRenderer(10, 10));
		if (scores.size() < 10) {
			list = new JList<>(lastTenScores.toArray(new String[0]));
		} else {
			list = new JList<>(lastTenScores.toArray(new String[0]));
		}
		add(list);
		this.setPreferredSize(new Dimension(200, 1000));
		this.setOpaque(false);
		this.setBackground(Color.white);
		this.setBounds(0, 55, 150, 1500);
	}

	public void readFile(String fileName) {
		scores = new ArrayList<>();
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				scores.add(line);
			}
			Collections.sort(scores, Collections.reverseOrder());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
