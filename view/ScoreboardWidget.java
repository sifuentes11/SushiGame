package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver {

	private SushiGameModel game_model;
	private JLabel display;
	private Comparator<Chef> sorter;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;

	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		sorter = new HighToLowBalanceComparator();
		display.setText(makeScoreboardHTML());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3, 1));
		
		btn1 = new JButton("Sort balance high to low");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sorter = new HighToLowBalanceComparator();
				display.setText(makeScoreboardHTML());
			}
		});;
		panel2.add(btn1);;
		
		btn2 = new JButton("Sort food sold high to low");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sorter = new HighToLowFoodSoldComparator();
				display.setText(makeScoreboardHTML());
			}
		});;
		panel2.add(btn2);
		
		btn3 = new JButton("Sort food spoiled low to high");
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sorter = new LowToHighFoodSpoiledComparator();
				display.setText(makeScoreboardHTML());
			}
		});;
		panel2.add(btn3);
			
		add(panel2, BorderLayout.SOUTH);
		
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		Arrays.sort(chefs, sorter);


		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>" + " * Eaten " + Math.round(c.getConsumed()*100.0)/100.0 + " <br>" + " * Spoiled " + Math.round(c.getSpoiled()*100.0)/100.0 + " <br>";
		}
		return sb_html;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

}
