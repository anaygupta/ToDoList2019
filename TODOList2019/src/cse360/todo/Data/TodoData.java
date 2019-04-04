package cse360.todo.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import cse360.todo.ListBox.ListBox;
import cse360.todo.Window.Window;

public class TodoData implements Serializable{
	
	private static final long serialVersionUID = 5910887681330772799L;
	private String filePath;
	private ArrayList<ListBoxData> allSaveData;
	
	
	public TodoData() {
		setAllSaveData(new ArrayList<>());
	}
	
	public void reset(){
		setAllSaveData(new ArrayList<>());
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public void saveAs(String path, String name){
		this.filePath = path + FileSystems.getDefault().getSeparator() + name + ".todo2019";
		File f = new File(filePath);
		saveFile(this, f);
	}
	
	public void saveFile(){
		if(this.filePath != null){
			saveFile(this, new File(this.getFilePath()));
		}
	}
	
	public void openFile(String path, Window window){
		Object o = loadFile(new File(path));
		TodoData data = (TodoData) o;
		
		remove(window);
		create(window, data);
	}
	
	private void remove(Window window){
		window.index.clearLibrary();
		window.view.removeAll();
	}
	
	private void create(Window window, TodoData newData){
		
		allSaveData = newData.getAllSaveData();
		
		filePath = newData.getFilePath();

		
		for(ListBoxData saveData : allSaveData){
			ListBox box = new ListBox(window, true);
			box.setSaveData(saveData);
			box.updateNameText(saveData.getName());
			box.updatePriorityDisplay(saveData.getPriority());
			box.getText().setText(saveData.getText());
			box.updateDueDateDisplay(saveData.getDate().getMonth(), saveData.getDate().getDay(), saveData.getDate().getYear());
			//box.updateStatus(saveData.getStatus().getStatusIndex());
			
			window.index.load(box);
		}
		
	}
	
	public static void saveFile(Object object, File file){
	    try{
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

	
	public ArrayList<ListBoxData> getAllSaveData() {
		return allSaveData;
	}

	public void setAllSaveData(ArrayList<ListBoxData> allSaveData) {
		this.allSaveData = allSaveData;
	}

}
