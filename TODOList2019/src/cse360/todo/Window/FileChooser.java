package cse360.todo.Window;
import javax.swing.JFileChooser;

import cse360.todo.Window.Window;

public class FileChooser {
	
	private JFileChooser chooser;
	public Window window;
	
	public FileChooser(Window window) {
		this.window = window;
		chooser = new JFileChooser();
	}
	
	public void showSaveAs(){
		chooser.setDialogTitle("Save As");
		chooser.setApproveButtonText("Save");
		int selection = chooser.showOpenDialog(window.frame);
		
		if(selection == JFileChooser.APPROVE_OPTION){
			window.data.saveAs(chooser.getCurrentDirectory().getAbsolutePath(), chooser.getSelectedFile().getName(), this.window);
		}
	}
	
	public void showExportToTxt(){
		chooser.setDialogTitle("Export to .txt");
		chooser.setApproveButtonText("Export");
		int selection = chooser.showOpenDialog(window.frame);
		
		if(selection == JFileChooser.APPROVE_OPTION){
			window.data.exportToTxt(chooser.getCurrentDirectory().getAbsolutePath(), chooser.getSelectedFile().getName(), this.window);
		}
	}
	
	public void showOpen(){
		chooser.setDialogTitle("Open");
		chooser.setApproveButtonText("Open");
		int selection = chooser.showOpenDialog(window.frame);
		
		if(selection == JFileChooser.APPROVE_OPTION){
			window.data.openFile(chooser.getSelectedFile().getAbsolutePath(), window);
		}
	}

}
