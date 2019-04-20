package cse360.todo.ListBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import cse360.todo.Data.ListBoxData;
import cse360.todo.Window.AddTaskWindow;
import cse360.todo.Window.StartEndDateWindow;
import cse360.todo.Window.TodoButton;
import cse360.todo.Window.Window;

public class ListBox extends JPanel implements Comparable<ListBox>{
	
	private static final long serialVersionUID = 7375636548822753668L;
	
	public static final int COMPARE_PRIORITY = 0;
	public static final int COMPARE_DUE_DATE = 1;
	public static final int COMPARE_DESCRIPTION = 2;
	
	public static int compareMode = COMPARE_PRIORITY;
	
	private JTextArea listItem;
	private TodoButton remove, startEndDates, edit;
	private JTextField priority, date, status;
	private JLabel priorityLabel, dueDateLabel, descriptionLabel, statusLabel;
	public UpDownArrowPanel ud;
	private Window window;
	public BasicArrowButton up, down;
	
	ListBoxData saveData;
	
	public ListBox(Window window) {
		this.window = window;
		saveData = new ListBoxData();
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
		priorityLabel = new JLabel("Priority Level: ");
		data.add(priorityLabel, c);
	
				
		//1
		c.gridx = 1;
		c.weighty = 0.0;
		c.weightx = 0.0;
		priority = new JTextField("" + saveData.getPriority());
		priority.setBorder(BorderFactory.createLineBorder(Color.black));
		priority.setColumns(3);
		priority.setToolTipText("Priority Level");
		priority.setMinimumSize(new Dimension(35, 18));
		priority.setBackground(Color.white);
		priority.setEditable(false);
		data.add(priority, c);
		
		//2
		c.gridx = 2;
		dueDateLabel = new JLabel("Due Date: ");
		data.add(dueDateLabel, c);
		
		//3
		c.gridx = 3;
		date = new JTextField(saveData.getDate().getMonth() + "/" + saveData.getDate().getDay() + "/" + saveData.getDate().getYear());
		date.setBorder(BorderFactory.createLineBorder(Color.black));
		date.setBackground(Color.white);
		date.setColumns(10);
		date.setToolTipText("Name");
		date.setMinimumSize(new Dimension(76, 18));
		date.setEditable(false);
		date.setToolTipText("Due Date");
		data.add(date, c);
		
		//4
		c.gridx = 4;
		descriptionLabel = new JLabel("Description: ");
		data.add(descriptionLabel, c);
		
		//5 
		c.gridx = 5;
		c.weighty = 1.0;
		c.weightx = 1.0;
		listItem = new JTextArea(saveData.getText());
		listItem.setLineWrap(true);
		listItem.setWrapStyleWord(true);
		listItem.setBorder(BorderFactory.createLineBorder(Color.black));
		listItem.setToolTipText("Description");
		listItem.setMinimumSize(new Dimension(100, 18));
		listItem.setEditable(false);
		data.add(listItem, c);
		
		//6
		c.gridx = 6;
		c.weightx = 0;
		c.weighty = 0;
		statusLabel = new JLabel("Status: ");
		data.add(statusLabel, c);
		
		//7
		c.gridx = 7;
		c.insets = new Insets(0,0,0,0);
		status = new JTextField("" + saveData.getStatus().getStatusText());
		status.setBorder(BorderFactory.createLineBorder(Color.black));
		status.setBackground(Color.white);
		status.setColumns(10);
		status.setToolTipText("Status");
		status.setMinimumSize(new Dimension(110, 18));
		status.setEditable(false);
		data.add(status, c);
		
		
		
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
		
		startEndDates = new TodoButton("Start / End Dates");
		startEndDates.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				StartEndDateWindow sedw = new StartEndDateWindow(ListBox.this);
				sedw.start();
			}
			
		});
		utility.add(startEndDates);
		
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
	
	
	
	
	public void updatePriorityDisplay(int priority) {
		if(this.priority != null) {
			this.priority.setText("" + priority);
		}
	}
	
	public void updateDueDateDisplay(int month, int day, int year) {
		if(this.date!= null) {
			this.date.setText(month + "/" + day + "/" + year);
		}
	}

	public void updatePriorityStatus(String status) {
		if(this.status != null) {
			this.status.setText(status);
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
	
	@Override
	public int compareTo(ListBox other) {
		if(compareMode == COMPARE_PRIORITY) {
			if(this.getSaveData().getPriority() > other.getSaveData().getPriority()) {
				return 1;
			}else if (this.getSaveData().getPriority() == other.getSaveData().getPriority()) {
				return 0;
			}else {
				return -1;
			}
		}else if(compareMode == COMPARE_DUE_DATE) {
			if(this.getSaveData().getDate().getDate().getTime() > other.getSaveData().getDate().getDate().getTime()) {
				return 1;
			}else if (this.getSaveData().getDate().getDate().getTime() == other.getSaveData().getDate().getDate().getTime()) {
				return 0;
			}else {
				return -1;
			}
		}else {
			if(this.getSaveData().getText().compareToIgnoreCase(other.getSaveData().getText()) > 0) {
				return 1;
			}else if (this.getSaveData().getText().compareToIgnoreCase(other.getSaveData().getText()) < 0) {
				return -1;
			}else {
				return 0;
			}
		}
		
	}
	
}
