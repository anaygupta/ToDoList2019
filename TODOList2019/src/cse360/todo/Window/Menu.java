package cse360.todo.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu {

	private JMenuBar menu;
	private JMenu file, edit;
	private JMenuItem newF, open, save, saveAs, exportTxt, removeAll;
	public Window window;
	FileChooser chooser;
	
	public Menu(Window window) {
		this.window = window;
		chooser = new FileChooser(window);
		menu = new JMenuBar();
		file = new JMenu("File");
		newF = new JMenuItem("New");
		newF.setToolTipText("Create a new TODO list");
		newF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Menu.this.window.index.clearLibrary();
				Menu.this.window.data.reset();
				Menu.this.window.view.removeAll();
				Menu.this.window.view.revalidate();
			}
			
		});
		open = new JMenuItem("Open...");
		open.setToolTipText("Open an existing TODO list");
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooser.showOpen();
			}
			
		});
		save = new JMenuItem("Save");
		save.setToolTipText("Save the current TODO list");
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.data.saveFile();
			}
			
		});
		saveAs = new JMenuItem("Save As...");
		saveAs.setToolTipText("Save the current TODO list to a file");
		saveAs.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooser.showSaveAs();
			}
			
		});
		
		exportTxt = new JMenuItem("Export to .txt...");
		exportTxt.setToolTipText("Export file to a .txt file");
		exportTxt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
		
		edit.add(removeAll);
		
		menu.add(file);
		menu.add(edit);

	}
	
	public JMenuBar getMenu(){
		return menu;
	}

}
