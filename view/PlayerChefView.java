package sushigame.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.CrabPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.IngredientPortion;
import comp401.sushi.IngredientPortionImpl;
import comp401.sushi.Nigiri;
import comp401.sushi.Plate;
import comp401.sushi.Plate.Color;
import comp401.sushi.RedPlate;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.SalmonPortion;
import comp401.sushi.Sashimi;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.Sushi;
import comp401.sushi.TunaPortion;
import sushigame.model.Belt;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private Belt belt;
	private Plate plate;
	private int belt_size;
	private JComboBox colorList;
	private JComboBox sushiKind;
	private JComboBox positionList;
	private JComboBox sushiType;



	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(7,1));
		
		
		String[] position = { "Select Belt Position", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
		positionList = new JComboBox(position);
//		colorList.setActionCommand("plate_color");
//		colorList.addActionListener(this);
		add(positionList);
		
		String[] types = { "Select Sushi Type", "sashimi", "nigiri", "roll"};
		sushiType = new JComboBox(types);
		add(sushiType);
		sushiKind = new JComboBox();
		sushiType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
//					sushiList.setActionCommand("sushi_type");
//					sushiList.addActionListener(this);
//					add(sushiKind);
				if (sushiType.getSelectedItem().equals("sashimi")) {
					//sushiKind = new JComboBox(Sashimi.SashimiType.values());
					sushiKind.removeAllItems();
					sushiKind.setEnabled(true);

					Object[] u = Sashimi.SashimiType.values();
					for (int i = 0; i < u.length; i++) {
						sushiKind.addItem(u[i]); 
					} 
					//sushiKind.addItem(Sashimi.SashimiType.values());
					repaint();
					validate();
				} else if (sushiType.getSelectedItem().equals("nigiri")) {
					//sushiKind = new JComboBox(Sashimi.SashimiType.values());
					sushiKind.removeAllItems();
					sushiKind.setEnabled(true);

					Object[] u = Nigiri.NigiriType.values();
					for (int i = 0; i < u.length; i++) {
						sushiKind.addItem(u[i]); 
					} 
				} else if (sushiType.getSelectedItem().equals("roll")) {
					sushiKind.removeAllItems();
					sushiKind.setEnabled(false);

				} else if (sushiType.getSelectedItem().equals("Sushi Type")) {
					sushiKind.removeAllItems();
					sushiKind.setEnabled(true);

				}
				
				
				
			}
		});
		
		add(sushiKind);

		JButton red_button = new JButton("Make red plate");
		red_button.setActionCommand("red_plate");
		red_button.addActionListener(this);
		add(red_button);

		JButton blue_button = new JButton("Make blue plate");
		blue_button.setActionCommand("blue_plate");
		blue_button.addActionListener(this);
		add(blue_button);

		JButton gold_button = new JButton("Make gold plate");
		gold_button.setActionCommand("gold_plate");
		gold_button.addActionListener(this);
		add(gold_button);
		
		JButton green_button = new JButton("Make green plate");
		green_button.setActionCommand("green_plate");
		green_button.addActionListener(this);
		add(green_button);
		
		
		
//		String[] plateColors = { "RED", "BLUE", "GREEN", "GOLD" };
//		colorList = new JComboBox(plateColors);
////		colorList.setActionCommand("plate_color");
////		colorList.addActionListener(this);
//		add(colorList);
		


		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	
//	private void makeRedColorRequest(Color color) {
//		for (ChefViewListener l : listeners) {
//			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
//		}
//	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "red_plate":
			if (sushiType.getSelectedItem().equals("") || positionList.getSelectedIndex() == 0) {
			} else if (sushiType.getSelectedItem().equals("sashimi")) {
				Sushi s = new Sashimi((Sashimi.SashimiType) sushiKind.getSelectedItem());
				makeRedPlateRequest(s, positionList.getSelectedIndex() - 1);
			} else if (sushiType.getSelectedItem().equals("nigiri")) {
				Sushi s = new Nigiri((Nigiri.NigiriType) sushiKind.getSelectedItem());
				makeRedPlateRequest(s, positionList.getSelectedIndex() - 1);
			} else if (sushiType.getSelectedItem().equals("roll")) {
				makeRedPlateRequest(handleMakeRoll(), positionList.getSelectedIndex() - 1);
			} 
			break;
		case "blue_plate":
			if (sushiType.getSelectedItem().equals("") || positionList.getSelectedIndex() == 0) {
			} else if (sushiType.getSelectedItem().equals("sashimi")) {
				Sushi s = new Sashimi((Sashimi.SashimiType) sushiKind.getSelectedItem());
				makeBluePlateRequest(s, positionList.getSelectedIndex());
			} else if (sushiType.getSelectedItem().equals("nigiri")) {
				Sushi s = new Nigiri((Nigiri.NigiriType) sushiKind.getSelectedItem());
				makeBluePlateRequest(s, positionList.getSelectedIndex());
			} else if (sushiType.getSelectedItem().equals("roll")) {
				makeBluePlateRequest(handleMakeRoll(), positionList.getSelectedIndex());
			} 
			break;
		case "gold_plate":
			double n = 0;
			while (n < 5 || n > 10) {
				n = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the price of the Gold Plate (between 5 and 10 dollars)", "OK", JOptionPane.PLAIN_MESSAGE));
			}
			
			if (sushiType.getSelectedItem().equals("") || positionList.getSelectedIndex() == 0) {
			} else if (sushiType.getSelectedItem().equals("sashimi")) {
				Sushi s = new Sashimi((Sashimi.SashimiType) sushiKind.getSelectedItem());
				makeGoldPlateRequest(s, positionList.getSelectedIndex(), n);
			} else if (sushiType.getSelectedItem().equals("nigiri")) {
				Sushi s = new Nigiri((Nigiri.NigiriType) sushiKind.getSelectedItem());
				makeGoldPlateRequest(s, positionList.getSelectedIndex(), n);
			} else if (sushiType.getSelectedItem().equals("roll")) {
				makeGoldPlateRequest(handleMakeRoll(), positionList.getSelectedIndex(), n);
			} 
			break;
			
		case "green_plate":
			if (sushiType.getSelectedItem().equals("") || positionList.getSelectedIndex() == 0) {
			} else if (sushiType.getSelectedItem().equals("sashimi")) {
				Sushi s = new Sashimi((Sashimi.SashimiType) sushiKind.getSelectedItem());
				makeGreenPlateRequest(s, positionList.getSelectedIndex());
			} else if (sushiType.getSelectedItem().equals("nigiri")) {
				Sushi s = new Nigiri((Nigiri.NigiriType) sushiKind.getSelectedItem());
				makeGreenPlateRequest(s, positionList.getSelectedIndex());
			} else if (sushiType.getSelectedItem().equals("roll")) {
				makeGreenPlateRequest(handleMakeRoll(), positionList.getSelectedIndex());
			} 
			break;
		}
	}
	
	public Sushi handleMakeRoll() {
		String name = JOptionPane.showInputDialog(new JFrame(), "Name of Roll", "Ok", JOptionPane.PLAIN_MESSAGE);
		IngredientPortion[] ings = new IngredientPortion[8];
		//double[] amts = new double[8];
		int count = 0;
		double i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Tuna to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 != 0) {
			ings[0] = new TunaPortion(i1);
			count++;
		} else {
			ings[0] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Eel to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[1] = new EelPortion(i1);
			count++;
		} else {
			ings[1] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Salmon to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[2] = new SalmonPortion(i1);
			count++;
		} else {
			ings[2] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Shrimp to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[3] = new ShrimpPortion(i1);
			count++;
		} else {
			ings[3] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Crab to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[4] = new CrabPortion(i1);
			count++;
		} else {
			ings[4] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Avocado to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[5] = new AvocadoPortion(i1);
			count++;
		} else {
			ings[5] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Rice to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[6] = new RicePortion(i1);
			count++;
		} else {
			ings[6] = null;
		}
		
		i1 = -1;
		while (i1 < 0 || i1 > 1.5) {
			i1 = Double.parseDouble(JOptionPane.showInputDialog(new JFrame(), "Input the amount of Seaweed to include (between 0 and 1.5 ounces)", "Add It!", JOptionPane.PLAIN_MESSAGE));
		}
		if (i1 > 0 && i1 <= 1.5) {
			ings[7] = new SeaweedPortion(i1);
			count++;
		} else {
			ings[7] = null;
		}
		
		int j = 0;
		IngredientPortion[] ings2 = new IngredientPortion[count];
		for (int i = 0; i < ings.length; i++) {
			if (ings[i] != null) {
				ings2[j] = ings[i];
				j++;
			}
		}

		Sushi s = new Roll(name, ings2);
		return s;

	}
}
