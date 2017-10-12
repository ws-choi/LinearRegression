package test;

import graphic.Grid;
import logic.DataCreator;
import logic.GridTranslator;

public class test {

	
	public static void main(String[] args) {
		DataCreator dc;
		GridTranslator gt;
		Grid grid;
			
		dc = new DataCreator();
		gt = new GridTranslator(1000, 500, 100, 100);
		grid = new Grid();
		grid.createAndShowGUI();
	


		
	}

}
