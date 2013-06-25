/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	private JLabel label ;
	private JTextField field ;
	private JButton graphButton ;
	private JButton clear ;
	private JButton remove ;
	private NameSurferDataBase DB = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph graph;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
		graph = new NameSurferGraph();
		add(graph);
		
		label = new JLabel("Name");
		field = new JTextField(20);
		graphButton = new JButton("Graph");
		clear = new JButton("Clear");
		remove = new JButton("Remove"); 
	
		add(label, SOUTH);
		add(field, SOUTH);
		add(graphButton, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);
		addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == graphButton){
			String name = field.getText();
			NameSurferEntry rankings = DB.findEntry(name);
			if(rankings != null) {
				graph.addEntry(rankings);
				graph.update();
			}
		}
		if(e.getSource() == clear){
			graph.clear();
			graph.update();
		}
		if(e.getSource() == remove){
			String name = field.getText();
			graph.delete(name);
			graph.update();
		}
	}
	
}
