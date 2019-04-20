package cse360.todo.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cse360.todo.ListBox.ListBox;
import cse360.todo.Window.Window;

public class ListBoxLibrary {

	/**
	 * the List of boxes
	 */
	private List<ListBox> boxes;
	/**
	 * The current window
	 */
	public Window window;
	/**
	 * Constructor. Sets the objects window to the passed window and calls setListBoxes with an empty arrayList of ListBoxes
	 * @param window the current window
	 */
	public ListBoxLibrary(Window window) {
		 this.window = window;
		 setListBoxes(new ArrayList<ListBox>());
	}
	/**
	 * Returns the objects List of boxes
	 * @return The list of boxes
	 */
	public List<ListBox> getListBoxes() {
		return boxes;
	}
	/**
	 * Sets the objects Boxes list to the passed list.
	 * @param listBoxes the list of ListBoxes to be used
	 */
	public void setListBoxes(List<ListBox> listBoxes) {
		this.boxes = listBoxes;
	}
	/**
	 * Returns the index of the requested box, l, in the objects list of boxes. Returns -1 if the list is not there.
	 * @param l the listbox to be indexed
	 * @return the index of the requested listBox
	 */
	public int getIndex(ListBox l){
		int it = 0;
		for(ListBox box : boxes){
			if(l.equals(box)){
				return it;
			}
			it++;
		}
		return -1;
	}
	
	/**
	 * Addes box to the List of Boxes.
	 * @param box the new Box to be added to the list. 
	 */
	public void add(ListBox box){
		boxes.add(box);
		populateView();
	}
	
	/**
	 * 
	 * @param box t
	 */
	public void load(ListBox box){
		boxes.add(box);
		populateView();
	}
	/**
	 * Removes the passed box from the list
	 * @param box The box to be removed
	 */
	public void remove(ListBox box){
		boxes.remove(box);
		populateView();
	}
	/**
	 * Searches for the box and moves it up one index. 
	 * @param box the box to be searched for
	 */
	public void swapUp(ListBox box){
		int index1 = 0;
		int index2 = 0;
		int it = 0;
		for(ListBox l : boxes){
			if(l.equals(box)){
				index1 = it;
				break;
			}
			it++;
		}
		index2 = index1 - 1;
		
		if(index2 >= 0){
			Collections.swap(boxes, index1, index2);
			populateView();
		}
		
		
	}
	/**
	 * Searches for the box in the list of  boxes
	 * @param box the box to look for
	 */
	public void swapDown(ListBox box){
		int index1 = 0;
		int index2 = 0;
		int it = 0;

		for(ListBox l : boxes){
			if(l.equals(box)){
				index1 = it;
				break;
			}
			it++;
		}
		index2 = index1 + 1;
		if(index2 < boxes.size()){
			
			Collections.swap(boxes, index1, index2);
			populateView();
		}
	}
	/**
	 * Clears all Boxes from view
	 */
	public void clearLibrary(){
		boxes.removeAll(boxes);
	}
	/**
	 * Removes all current boxes and adds all the current Listboxes to the view. Validates as well
	 */
	private void populateView(){
		window.view.removeAll();
		for(ListBox l : boxes){
			window.view.add(l);
		}
		window.view.revalidate();
	}
	
	public ArrayList<ListBoxData> getAllSaveData(){
		
		ArrayList<ListBoxData> allSaveData = new ArrayList<>();
		
		for(ListBox box : boxes){
			allSaveData.add(box.getSaveData());
		}
		
		return allSaveData;
	}
	
	public void sortByPriority() {
		ListBox.compareMode = ListBox.COMPARE_PRIORITY;
		Collections.sort(boxes);
		populateView();
	}
	
	public void sortByDueDate() {
		ListBox.compareMode = ListBox.COMPARE_DUE_DATE;
		Collections.sort(boxes);
		populateView();
	}
	
	public void sortByDescription() {
		ListBox.compareMode = ListBox.COMPARE_DESCRIPTION;
		Collections.sort(boxes);
		populateView();
	}
	
}
