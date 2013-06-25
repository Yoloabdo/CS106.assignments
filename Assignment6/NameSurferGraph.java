/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import acm.gui.TableLayout;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

import javax.swing.JButton;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	private ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		update();
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		entries.clear();

		

		
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
		
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		gridDrwaing();
		if (entries.size() > 0){
			for(int i =0; i < entries.size(); i++){
				entryDraw(entries.get(i), i);

			}
			
		}
	}
	
	public void delete(String name){
		
		for(int i =0; i < entries.size(); i++){
			NameSurferEntry check = entries.get(i);
			if(check.getName().equalsIgnoreCase(name)){
				entries.remove(i);
			}

		}
	}
	
	/*
	 * Drawing the basic grids and adding labels
	 */
	
	private void gridDrwaing(){
		
		int widX = getWidth() / NDECADES;
		
		// Upper and lower lines 
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight()-GRAPH_MARGIN_SIZE));
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		
		// Vertical lines 
		
		double vertStart = 0;
		
		int startDate = START_DECADE;
		for (int i =0; i < NDECADES ; i++){
			add(new GLine (vertStart, 0, vertStart, getHeight()));
			
			GLabel date =  new GLabel(Integer.toString(startDate), vertStart + widX/4, getHeight()- GRAPH_MARGIN_SIZE / 4);
			//date.setFont(font);
			add(date);
			
			// ++ Date
			startDate += 10;
			vertStart += widX;
		}
		
	}
	
	/*
	 * Draws the entries in the arraylist that i've created above
	 * whenever it's requested in update method above.
	 * for the color thing i've ignored putting numbring thing and made it totally random
	 * 
	 * 
	 * also adds label above the line, to mark it
	 */
	
	private void entryDraw(NameSurferEntry entry, int range){
		
		// calculating grid length 
		double HeightLength = (getHeight() - 2* GRAPH_MARGIN_SIZE);
		int Decade = START_DECADE;

		
		double unit = (getHeight() - GRAPH_MARGIN_SIZE) / 1000.0;
		double firstPointY = GRAPH_MARGIN_SIZE + entry.getRank(Decade) * unit;
		if(entry.getRank(Decade) == 0){
			firstPointY = getHeight() - GRAPH_MARGIN_SIZE;
		}
				
		for (int i = 0; i < NDECADES ; i++){
			
			int firstGrowthValue = entry.getRank(Decade);

			int secondGrowthValue = entry.getRank(Decade + 10);
			
			double firstPointX = i * (getWidth()/ NDECADES);
			double secondPointX = (i+1) * (getWidth() / NDECADES);
			
			double secondPointY = GRAPH_MARGIN_SIZE + secondGrowthValue * unit;
			GLabel entryName = new GLabel(entry.getName() +  entry.getRank(Decade));
			if(secondGrowthValue == 0){
				secondPointY = getHeight() - GRAPH_MARGIN_SIZE;
			}
			if(firstGrowthValue == 0){
				entryName = new GLabel(entry.getName() +  " *");
			}
			GLine line = new GLine(firstPointX, firstPointY, secondPointX, secondPointY);
			int color = (range + 1) % 4;
			if (color == 0){
				line.setColor(Color.magenta);
				entryName.setColor(Color.magenta);
			}
			if (color == 1){
				line.setColor(Color.black);
				entryName.setColor(Color.black);
			}
			if (color == 2){
				line.setColor(Color.red);
				entryName.setColor(Color.red);
			}
			if (color == 3){
				line.setColor(Color.blue);
				entryName.setColor(Color.blue);
			}
			
			add(line);
			add(entryName, firstPointX, firstPointY);

			
			firstPointY = secondPointY;
			Decade += 10;
			
		}
		
		
		
		
			
		
		
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
