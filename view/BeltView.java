package sushigame.view;

import java.awt.Color;  
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
//	private JLabel[] belt_labels;
	private Line[] line_objects;


	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		line_objects = new Line[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {

			Line l = new Line();
			add(l);
			line_objects[i] = l;
			
			l.setVisible(true);
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i = 0; i < belt.getSize(); i++) {
			Plate plate = belt.getPlateAtPosition(i);

			Line l = line_objects[i];
			//l.btnA.setText("Empty Tray");
			l.setPlate(plate);
			l.btnA.setToolTipText("");
			if (plate == null) {

				//l.setVisible(false);

				l.btnA.setText("Empty Tray Position #" + (i+1));
				
			} else {
				//l.btnA.setText("Uncover!");
				l.btnA.setText("Click to view this yummy " + plate.getColor().toString() + " plate!");

				l.setVisible(true);

				l.setPlate(null);
				l.setAgeOfPlate(0);
				l.setColor("");
				l.setNameOfChef("");
				l.setKind("");
				l.setTypeOfSushi("");
				
				
				l.setPlate(plate);
				l.setAgeOfPlate(belt.getAgeOfPlateAtPosition(i));
				l.setColor(plate.getColor().toString());
				l.setNameOfChef(plate.getChef().getName());
				l.setKind(plate.getContents().getName().toString());
				l.setTypeOfSushi(plate.getContents().getClass().getSimpleName());
				repaint();
				validate();
			}

//			JLabel plabel = belt_labels[i];
//			if (l == null) {
//
//				plabel.setBackground(Color.GRAY);
//				
//			} else {
//				plabel.setText(p.toString());
//				
//				switch (p.getColor()) {
//				case RED:
//					plabel.setBackground(Color.RED); plabel.setText("Red"); break;
//				case GREEN:
//					plabel.setBackground(Color.GREEN); plabel.setText("Green"); break;
//				case BLUE:
//					plabel.setBackground(Color.BLUE); plabel.setText("Blue"); break;
//				case GOLD:
//					plabel.setBackground(Color.YELLOW); plabel.setText("Yellow"); break;
//				}
//			}
		}
	}
}
