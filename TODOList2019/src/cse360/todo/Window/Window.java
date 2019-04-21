package cse360.todo.Window;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;


import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;


import cse360.todo.Data.TodoData;
import cse360.todo.ListBox.ListBox;
//import cse360.todo.Data.ImageIconLib;
import cse360.todo.Data.ListBoxLibrary;



public class Window {
	
	public JFrame frame;
	public ListBoxLibrary index;
	public TodoData data;
	public ScrollPanel view;
	public InfoCard infoCard;
	//public ImageIconLib icon;
	
	public Window() {}
	
	private void build()
	{
		index = new ListBoxLibrary(this);
		frame = new JFrame("To-Do List 2019");
		JPanel container = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width / 2, (screenSize.height / 2) + (screenSize.height / 4));
		frame.setLocationRelativeTo(null);
		frame.add(container);
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		view = new ScrollPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		JScrollPane list = new JScrollPane();
		list.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		list.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		list.setWheelScrollingEnabled(true);
		data = new TodoData();
		ListBox b = new ListBox(this);
		index.add(b);
		c.gridy = 0;
		infoCard = new InfoCard(this);
		container.add(infoCard, c);
		c.weighty = 1.0;
		c.gridy = 1;
		container.add(list, c);
		list.setViewportView(view);
		Menu menu = new Menu(this);
		frame.setJMenuBar(menu.getMenu());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void start(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				build();
			}
		});
	}

}
