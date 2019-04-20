package cse360.todo.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import cse360.todo.ListBox.ListBox;
import cse360.todo.Window.Window;

public class TodoData implements Serializable{
	
	private static final long serialVersionUID = 5910887681330772799L;
	/**
	 * File path of the current window.
	 */
	private String filePath;
	/**
	 * An ArrayList of type ListBoxData. Contains all the save data for the current window
	 */
	private ArrayList<ListBoxData> allSaveData;
	
	/**
	 * Default contructor. Initializes the data to an empty array list
	 * @return a TodoData object
	 */
	public TodoData() {
		setAllSaveData(new ArrayList<>());
	}
	
	/**
	 * Resets the save data to an empty array list
	 */
	public void reset(){
		setAllSaveData(new ArrayList<>());
		filePath = null;
	}
	
	/**
	 * @return The file path of the current data list.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the file path to filePath
	 * @param filePath The new filePath for the TodoData object
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public void exportToTxt(String path, String name, Window window) {
		path = path + FileSystems.getDefault().getSeparator() + name + ".txt";
		ArrayList<ListBoxData> allSaveData = window.index.getAllSaveData();
		if(allSaveData.size() == 0) {
			return;
		}
		File file = new File(path);
		try{
		      if(!file.exists()) {
		    	  file.createNewFile();
		      }else {
		    	  file.delete();
		      }
		      file.createNewFile();
		      PrintWriter writer = new PrintWriter(file, "UTF-8");
		      for(ListBoxData data : allSaveData) {
		    	  writer.println("Priority Level: " + data.getPriority() + 
		    			  		 " Due Date: " + data.getDate().getMonth() + "/" + data.getDate().getDay() + "/" + data.getDate().getYear() +
		    			  		 " Description: " + data.getText() +
		    			  		 " Status: " + data.getStatus().getStatusText() +
		    			  		 " Start Date: " + data.getStatus().getStartDate().getMonth() + "/" + data.getStatus().getStartDate().getDay() + "/" + data.getStatus().getStartDate().getYear() +
		    			  		 " End Date: " + data.getStatus().getEndDate().getMonth() + "/" + data.getStatus().getEndDate().getDay() + "/" + data.getStatus().getEndDate().getYear());
		      }
		      
		      writer.flush();
		      writer.close();
		    }
		    catch (Exception e){
		      e.printStackTrace();
		    }
	}
	
	/**
	 * Saves the current file as the passed name. 
	 * @param path The directory to save the file to
	 * @param name The name of the file
	 */
	public void saveAs(String path, String name, Window window){
		this.filePath = path + FileSystems.getDefault().getSeparator() + name + ".todo2019";
		File f = new File(filePath);
		this.allSaveData = window.index.getAllSaveData();
		saveFile(this, f);
		window.infoCard.setCurrentPathText("Current Path: " + this.filePath);
	}
	
	/**
	 * Saves the file at the current location stored in filePath. If it does not exist,
	 * the method does nothing
	 */
	public void saveFile(ListBoxLibrary index){
		if(this.filePath != null){
			this.allSaveData = index.getAllSaveData();
			saveFile(this, new File(this.getFilePath()));
		}
	}
	
	/**
	 * @param path The path of the file to be opened
	 * @param window the current window handle
	 */
	public void openFile(String path, Window window){
		Object o = loadFile(new File(path));
		TodoData data = (TodoData) o;
		
		remove(window);
		
		this.filePath = path;
		
		create(window, data);
		window.infoCard.setCurrentPathText("Current Path: " + this.filePath);
	}
	
	/**
	 * Removes the current window's contents and removes it from view
	 *	@param window the current window to be removed. 
	 */
	private void remove(Window window){
		window.data.reset();
		window.index.clearLibrary();
		window.view.removeAll();
	}
	
	/**
	 * Helper method called by openFile. It takes the window and updates it with the 
	 * newData that was previously loaded by the loadData method.
	 * @param window The current window that the newData is being placed into
	 * @param newData The list of data that the window is being updated with
	 */
	private void create(Window window, TodoData newData){
		
		try {
			allSaveData = newData.getAllSaveData();
		}catch(NullPointerException e) {
			return;
		}
		
		filePath = newData.getFilePath();

		
		for(ListBoxData saveData : allSaveData){
			ListBox box = new ListBox(window);
			box.setSaveData(saveData);
			box.updatePriorityDisplay(saveData.getPriority());
			box.getText().setText(saveData.getText());
			box.updateDueDateDisplay(saveData.getDate().getMonth(), saveData.getDate().getDay(), saveData.getDate().getYear());
			box.updatePriorityStatus(saveData.getStatus().getStatusText());
			
			window.index.load(box);
		}
		
	}
	
	/**
	 * Saves the contents of the TodoData to the specified file using an ObjectOutputStream.
	 * @param object the TodoData object to be saved to file
	 * @param file the file to be saved to
	 */
	public static void saveFile(Object object, File file){
	    try{
	      if(!file.exists()) {
	    	  file.createNewFile();
	      }else {
	    	  file.delete();
	      }
	      file.createNewFile();
	      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
	      oos.writeObject(object);
	      oos.flush();
	      oos.close();
	    }
	    catch (Exception e){
	      e.printStackTrace();
	    }
	  }
	 
	/**
	 * Takes to contents of the file and attempts to read them as an object.
	 * @param file the file to read from
	 * @return the object to be casted into a TodoData
	 */
	  public static Object loadFile(File file){
	    try{
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
	      Object object = ois.readObject();
	      ois.close();
	      return object;
	    }
	    catch (Exception e) {}
	    return null;
	  }

	  /**
		 * returns the saved data
		 * @return the saved data 
		 */
	  public ArrayList<ListBoxData> getAllSaveData() {
			return allSaveData;
	  }

	  /**
		 * changes the value of the saved data to the passed parameter. If the file is not saved, these changes are not permanent. 
		 * @param allSaveData the saved data to be used
		 */
	  public void setAllSaveData(ArrayList<ListBoxData> allSaveData) {
			this.allSaveData = allSaveData;
	  }

}
