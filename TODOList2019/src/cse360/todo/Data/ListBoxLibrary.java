package cse360.todo.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cse360.todo.ListBox.ListBox;
import cse360.todo.Window.Window;

public class ListBoxLibrary {

	private List<ListBox> boxes;
	public Window window;
	
	public ListBoxLibrary(Window window) {
		 this.window = window;
		 setListBoxes(new ArrayList<ListBox>());
	}

	public List<ListBox> getListBoxes() {
		return boxes;
	}

	public void setListBoxes(List<ListBox> listBoxes) {
		this.boxes = listBoxes;
	}

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
	
	public void add(ListBox box){
		
		if(!boxes.isEmpty()) {
			box.getSaveData().setPriority(boxes.get(boxes.size() - 1).getSaveData().getPriority() + 1);
		}
		box.updatePriorityDisplay(box.getSaveData().getPriority());
		
		boxes.add(box);
		populateView();
	}
	
	public void load(ListBox box){
		boxes.add(box);
		populateView();
	}
	
	public void remove(ListBox box){
		boxes.remove(box);
		populateView();
	}
	
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
			ListBox other = boxes.get(index2);
			
			int temp = other.getSaveData().getPriority();
			other.getSaveData().setPriority(box.getSaveData().getPriority());
			other.updatePriorityDisplay(other.getSaveData().getPriority());
				
			box.getSaveData().setPriority(temp);
			box.updatePriorityDisplay(box.getSaveData().getPriority());
			
			Collections.swap(boxes, index1, index2);
			populateView();
		}
		
		
	}
	
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
			ListBox other = boxes.get(index2);
	
			int temp = other.getSaveData().getPriority();
			other.getSaveData().setPriority(box.getSaveData().getPriority());
			other.updatePriorityDisplay(other.getSaveData().getPriority());
				
			box.getSaveData().setPriority(temp);
			box.updatePriorityDisplay(box.getSaveData().getPriority());
			
			Collections.swap(boxes, index1, index2);
			populateView();
		}
	}
	
	public void clearLibrary(){
		boxes.removeAll(boxes);
	}
	
	private void populateView(){
		window.view.removeAll();
		for(ListBox l : boxes){
			window.view.add(l);
		}
		window.view.revalidate();
	}
}
