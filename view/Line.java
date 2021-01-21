package sushigame.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import comp401.sushi.Plate.Color;
import comp401.sushi.Roll;
import comp401.sushi.Sushi;
import sushigame.model.Belt;
import sushigame.model.Chef;
import sushigame.view.BeltView;



public class Line extends JPanel {
	String color;
	String typeOfSushi;
	String kindName;
	String nameOfChef;
	int ageOfPlate;
	Plate plate;
	public JButton btnA = new JButton();	

	


	private static final long serialVersionUID = 1L;

	public Line() {		
		//JButton btnA = new JButton();	
		btnA.setText("Uncover!");
		btnA.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if (plate == null) {
					btnA.setText("No Sushi Here!");
					btnA.setToolTipText("");

				} else if (plate.getContents() instanceof Roll) {
					String ing = "";
					for (int i = 0; i < plate.getContents().getIngredients().length; i++) {
						ing += plate.getContents().getIngredients()[i].getName() + " " + plate.getContents().getIngredients()[i].getAmount() + "oz. ";
					}
					btnA.setText("Color:" + color + " Type:" + typeOfSushi + " Kind:" + kindName + " Chef:" + nameOfChef + " Age:" + ageOfPlate + "  Hover to see Ingredients!");
					btnA.setToolTipText(" Made with: " + ing);
				} else {
					btnA.setText("Color:" + color + " Type:" + typeOfSushi + " Kind:" + kindName + " Chef:" + nameOfChef + " Age:" + ageOfPlate);
					btnA.setToolTipText("");

				}
				
				
			}
		});
		add(btnA);
		
		
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setTypeOfSushi(String typeOfSushi) {
		this.typeOfSushi = typeOfSushi;
	}

	public void setKind(String kindName) {
		this.kindName = kindName;
	}

	public void setNameOfChef(String nameOfChef) {
		this.nameOfChef = nameOfChef;
	}

	public void setAgeOfPlate(int ageOfPlate) {
		this.ageOfPlate = ageOfPlate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}
	
	public void setBlank() {
		btnA.setText("No Sushi Here!");
	}


	
	
	



}
