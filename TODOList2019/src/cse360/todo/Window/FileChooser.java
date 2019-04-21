package cse360.todo.Window;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import cse360.todo.Window.Window;

public class FileChooser 
{	
	private JFileChooser chooser;
	public Window window;
	
	public FileChooser(Window window) 
	{
		this.window = window;
		chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "TODO2019 Save Files (*.todo2019)";
			}
			@Override
			public boolean accept(File file) {
		       if (file.isDirectory()) {
		           return true;
		       } else {
		           String filename = file.getName().toLowerCase();
		           return filename.endsWith(".todo2019");
		       }
			}
		});
	}
	
	
	//this function shows the Save As window frame
	public void showSaveAs()
	{
		chooser.setDialogTitle("Save As");
		chooser.setApproveButtonText("Save");
		
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
			int result = window.data.openFile(chooser.getSelectedFile().getAbsolutePath(), window);
			if(result == 1)
			{
				JOptionPane.showMessageDialog(window.frame,
						"Please select a save file with extension \".todo2019\".",
						"Incompatible file type",
						JOptionPane.ERROR_MESSAGE
						);
			}
		}
	}

}
