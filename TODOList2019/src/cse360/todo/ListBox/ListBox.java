package cse360.todo.ListBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import cse360.todo.Data.ListBoxData;
import cse360.todo.Window.AddTaskWindow;
import cse360.todo.Window.DueDateWindow;
import cse360.todo.Window.InfoCard;
import cse360.todo.Window.TodoButton;
import cse360.todo.Window.Window;

public class ListBox extends JPanel{
	
	private static final long serialVersionUID = 7375636548822753668L;
	
	private JTextArea listItem;
	private TodoButton remove, status, edit;
	private JTextField name, date;
	//private JComboBox<String> status;
	private JLabel priority;
	public UpDownArrowPanel ud;
	private Window window;
	public BasicArrowButton up, down;
	
	ListBoxData saveData;
	
	public ListBox(Window window, boolean load) {
		this.window = window;
		saveData = new ListBoxData();
		if(!load){
			window.data.getAllSaveData().add(saveData);
		}
		this.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(10, 0, 0, 0));
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10))));
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;

		
		//0 0
		c.gridy = 0;
		c.gridx = 0;
		this.add(buildUtilityRow(), c);
		
		
		
		//0 1 
		c.gridy = 1;
		c.gridx = 0;
		this.add(buildDataRow(), c);
		
		
	}
	
	public JPanel buildDataRow() {
		
		JPanel data = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//0
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,5);
		
		name = new JTextField("");
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		name.setColumns(10);
		name.setToolTipText("Name");
		name.setMinimumSize(new Dimension(110, 18));
		name.setBackground(Color.white);
		name.setEditable(false);

		data.add(name, c);
		
		
		//1
		c.gridx = 1;
		c.weighty = 0.0;
		c.weightx = 0.0;
		priority = new JLabel("Priority Level: " + saveData.getPriority());
		priority.setToolTipText("Priority Level");
		data.add(priority, c);
		
		//2
		c.gridx = 2;
		date = new JTextField(saveData.getDate().getMonth() + "/" + saveData.getDate().getDay() + "/" + saveData.getDate().getYear());
		date.setBorder(BorderFactory.createLineBorder(Color.black));
		date.setBackground(Color.white);
		date.setColumns(10);
		date.setToolTipText("Name");
		date.setMinimumSize(new Dimension(110, 18));
		date.setEditable(false);
		date.setToolTipText("Due Date");
		data.add(date, c);
		
		//3 
		c.gridx = 3;
		c.insets = new Insets(0,0,0,0);
		c.weighty = 1.0;
		c.weightx = 1.0;
		listItem = new JTextArea();
		listItem.setLineWrap(true);
		listItem.setWrapStyleWord(true);
		listItem.setBorder(BorderFactory.createLineBorder(Color.black));
		listItem.setToolTipText("Description");
		listItem.setEditable(false);
		data.add(listItem, c);
		
		
		
		return data;
		
	}
	
	public JPanel buildUtilityRow() {
		JPanel utility = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.insets = new Insets(0,0,0,5);
		
		remove = new TodoButton("Remove");
		utility.add(remove, c);
		
		c.gridx = 1;
		c.insets = new Insets(0,0,0,0);
		
		edit = new TodoButton("Edit");
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddTaskWindow atw = new AddTaskWindow(ListBox.this.window, ListBox.this);
				atw.start();
			}
			
		});
		utility.add(edit, c);
		
		JPanel padding = new JPanel();
		c.gridx = 2;
		c.weightx = 1;
		
		utility.add(padding, c);
		
		
		c.gridx = 3;
		c.weighty = 0;
		c.weightx = 0;
		
		status = new TodoButton("Status");
		utility.add(status);
		
		/*
		status = new JComboBox<String>();
		status.addItem("Not Started");
		status.addItem("In Progress");
		status.addItem("Finished");
		data.add(status, c);
		
		status.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveData.getStatus().setStatusIndex(status.getSelectedIndex());
				
			}
			
		});
		*/
		c.weightx = 0;
		c.gridx = 4;
		ud = new UpDownArrowPanel(window, this);
		utility.add(ud, c);
		
		
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				window.index.remove(ListBox.this);
			}
			
		});
		
		return utility;
	}
	
	
	public void updateNameText(String nameText) {
		if(this.name != null) {
			this.name.setText(nameText);
		}
	}
	
	public void updatePriorityDisplay(int priority) {
		if(this.priority != null) {
			this.priority.setText("Priority level: " + priority);
		}
	}
	
	public void updateDueDateDisplay(int month, int day, int year) {
		if(this.date!= null) {
			this.date.setText(month + "/" + day + "/" + year);
		}
	}

	
	public JTextArea getText(){
		return listItem;
	}

	public ListBoxData getSaveData() {
		return saveData;
	}
	
	public void setSaveData(ListBoxData saveData){
		this.saveData = saveData;
	}

	
}
