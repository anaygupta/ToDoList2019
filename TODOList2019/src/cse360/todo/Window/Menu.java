package cse360.todo.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu 
{
	private JMenuBar menu;
	private JMenu file, edit;
	private JMenuItem newF, open, save, saveAs, exportTxt, removeAll, sortByPriority, sortByDueDate, sortByDesc;
	public Window window;
	FileChooser chooser;
	/**
	 * Creates the menu objet
	 */
	public Menu(Window window) 
	{
		this.window = window;
		chooser = new FileChooser(window);
		menu = new JMenuBar();
		file = new JMenu("File");
		newF = new JMenuItem("New");
		newF.setToolTipText("Create a new TODO list");
		newF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Menu.this.window.index.clearLibrary();
				Menu.this.window.data.reset();
				Menu.this.window.view.removeAll();
				Menu.this.window.view.revalidate();
				Menu.this.window.infoCard.setCurrentPathText("Current Path: None");
			}
			
		});
		open = new JMenuItem("Open...");
		open.setToolTipText("Open an existing TODO list");
		open.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				chooser.showOpen();
			}
		});
		save = new JMenuItem("Save");
		save.setToolTipText("Save the current TODO list");
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int result = window.data.saveFile(Menu.this.window.index);
				if(result == 1)
				{
					JOptionPane.showMessageDialog(window.frame,
							"Can not save as there is no current file in the path.\n"
							+ "Please open a file using \"File > Open\", or save a new file using \"File > Save As\" first.");
				}
			}
		});
		saveAs = new JMenuItem("Save As...");
		saveAs.setToolTipText("Save the current TODO list to a file");
		saveAs.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				chooser.showSaveAs();
			}
		});
		
		exportTxt = new JMenuItem("Export to .txt...");
		exportTxt.setToolTipText("Export file to a .txt file");
		exportTxt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooser.showExportToTxt();
			}
			
		});
		
		file.add(newF);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(exportTxt);
		
		edit = new JMenu("Edit");
		removeAll = new JMenuItem("Remove All...");
		removeAll.setToolTipText("Remove all TODO list items");
		removeAll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Menu.this.window.view.removeAll();
				Menu.this.window.index.clearLibrary();
				Menu.this.window.view.revalidate();
			}
			
		});
		
		sortByPriority = new JMenuItem("Sort by priority");
		sortByPriority.setToolTipText("Sorts list by priority, smallest to largest");
		sortByPriority.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Menu.this.window.index.sortByPriority();
			}
			
		});
		
		sortByDueDate = new JMenuItem("Sort by due date");
		sortByDueDate.setToolTipText("Sorts list by due date, closest to farthest away");
		sortByDueDate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Menu.this.window.index.sortByDueDate();
			}
			
		});
		
		sortByDesc = new JMenuItem("Sort list by description");
		sortByDesc.setToolTipText("Sorts list by description in alphabetical order");
		sortByDesc.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Menu.this.window.index.sortByDescription();
			}
			
		});
		
		edit.add(sortByPriority);
		edit.add(sortByDueDate);
		edit.add(sortByDesc);
		edit.add(removeAll);
		
		menu.add(file);
		menu.add(edit);

	}
	/**
	 * Returns the menu
	 */
	public JMenuBar getMenu(){
		return menu;
	}

}
