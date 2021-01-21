package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class LowToHighFoodSpoiledComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef a, Chef b) {
		// We do a - b because we want smallest to largest
		return (int) (Math.round(a.getSpoiled()*100) - Math.round(b.getSpoiled()*100.0));
	}
}
