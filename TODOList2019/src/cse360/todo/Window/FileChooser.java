package cse360.todo.Window;
import javax.swing.JFileChooser;

import cse360.todo.Window.Window;

public class FileChooser 
{	
	private JFileChooser chooser;
	public Window window;
	
	public FileChooser(Window window) 
	{
		this.window = window;
		chooser = new JFileChooser();
	}
	
	//this function shows the Save As window frame
	public void showSaveAs()
	{
		chooser.setDialogTitle("Save As");
		chooser.setApproveButtonText("Save");
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int selection = chooser.showSaveDialog(window.frame);
		if(selection == JFileChooser.APPROVE_OPTION)
		{
			String path = chooser.getSelectedFile().getAbsolutePath();
			String filename = chooser.getSelectedFile().getName();
			window.data.saveAs(path, filename, this.window);
		}
	}
	
	public void showExportToTxt()
	{
		chooser.setDialogTitle("Export to Text File (.txt)");
		chooser.setApproveButtonText("Export");
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int selection = chooser.showSaveDialog(window.frame);
		if(selection == JFileChooser.APPROVE_OPTION)
		{
			String path = chooser.getSelectedFile().getAbsolutePath();
			String filename = chooser.getSelectedFile().getName();
			window.data.exportToTxt(path, filename, this.window);
		}
	}
	
	public void showOpen()
	{
		chooser.setDialogTitle("Open");
		chooser.setApproveButtonText("Open File");
		int selection = chooser.showOpenDialog(window.frame);
		
		if(selection == JFileChooser.APPROVE_OPTION)
		{
			window.data.openFile(chooser.getSelectedFile().getAbsolutePath(), window);
		}
	}

}
