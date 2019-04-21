package cse360.todo.Window;
import javax.swing.JFileChooser;

import cse360.todo.Window.Window;

public class FileChooser 
{	
	private JFileChooser chooser;
	public Window window;
	/**
	 * Creates a file chooser
	 */
	public FileChooser(Window window) 
	{
		this.window = window;
		chooser = new JFileChooser();
	}
	
	/**
	 * Dispalys the save as window frame
	 */
	public void showSaveAs()
	{
		chooser.setDialogTitle("Save As");
		chooser.setApproveButtonText("Save");
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int selection = chooser.showSaveDialog(window.frame);
		String path = chooser.getSelectedFile().getAbsolutePath();
		String filename = chooser.getSelectedFile().getName();

		if(selection == JFileChooser.APPROVE_OPTION)
		{
			window.data.saveAs(path, filename, this.window);
		}
	}
	/**
	 * Displays the export to text frame
	 */
	public void showExportToTxt()
	{
		chooser.setDialogTitle("Export to Text File (.txt)");
		chooser.setApproveButtonText("Export");
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int selection = chooser.showSaveDialog(window.frame);
		String path = chooser.getSelectedFile().getAbsolutePath();
		String filename = chooser.getSelectedFile().getName();
		
		if(selection == JFileChooser.APPROVE_OPTION)
		{
			window.data.exportToTxt(path, filename, this.window);
		}
	}
	/**
	 * Displays the open frame
	 */
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
