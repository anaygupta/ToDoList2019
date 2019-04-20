package cse360.todo.ListBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cse360.todo.Data.DueDate;
import cse360.todo.Data.ListBoxData;
import cse360.todo.Data.Status;
import cse360.todo.Window.DueDateWindow;
import cse360.todo.Window.TodoButton;

public class ModifiableListBox extends JPanel{
	private static final long serialVersionUID = -6067012390293090165L;
	
	private JTextArea listItem;
	private TodoButton date;
	private JComboBox<String> status;
	private JTextField priority;

	private JLabel priorityLabel, dueDateLabel, descriptionLabel, statusLabel;
	
	private boolean nonUserStatusUpdate;
	private int index;
	
	ListBoxData saveData;
	
	public ModifiableListBox(int index) {
		nonUserStatusUpdate = false;
		this.setIndex(index);
		saveData = new ListBoxData();
		this.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(10, 0, 0, 0));
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;

		
		//0 0
		c.gridy = 0;
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
		priority.setMinimumSize(new Dimension(110, 18));
		priority.setBackground(Color.white);
		data.add(priority, c);
		
		//2
		c.gridx = 2;
		dueDateLabel = new JLabel("Due Date: ");
		data.add(dueDateLabel, c);
		
		//3
		c.gridx = 3;
		date = new TodoButton(saveData.getDate().getMonth() + "/" + saveData.getDate().getDay() + "/" + saveData.getDate().getYear());
		date.setToolTipText("Set Due Date");
		data.add(date, c);
		
		date.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DueDateWindow ddw = new DueDateWindow(ModifiableListBox.this, DueDateWindow.DUE_DATE);
				ddw.start();
				
			}
			
		});
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
		status = new JComboBox<String>();
		status.addItem(Status.NOT_STARTED);
		status.addItem(Status.IN_PROGRESS);
		status.addItem(Status.FINISHED);
		data.add(status, c);
		
		status.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveData.getStatus().setStatusIndex(status.getSelectedIndex());
				if(status.getSelectedIndex() == DueDateWindow.START_DATE && !ModifiableListBox.this.nonUserStatusUpdate) {
					DueDateWindow ddw = new DueDateWindow(ModifiableListBox.this, DueDateWindow.START_DATE);
					ddw.start();
				}else if(status.getSelectedIndex() == DueDateWindow.END_DATE && !ModifiableListBox.this.nonUserStatusUpdate) {
					DueDateWindow ddw = new DueDateWindow(ModifiableListBox.this, DueDateWindow.END_DATE);
					ddw.start();
				}
				
				if(status.getSelectedIndex() == 0 && !ModifiableListBox.this.nonUserStatusUpdate) {
					saveData.getStatus().setStartDate(new DueDate());
					saveData.getStatus().setEndDate(new DueDate());
				}
				
				ModifiableListBox.this.nonUserStatusUpdate = false;
			}
			
		});
		data.add(status, c);	
		status.setSelectedIndex(saveData.getStatus().getStatusIndex());
		return data;
	}
	
	public JTextField getPriority() {
		return this.priority;
	}
	
	public void setSatusSelectedIndex(int index) {
		if(this.status != null && index >= 0 && index < 3) {
			nonUserStatusUpdate = true;
			this.status.setSelectedIndex(index);
		}
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

	public void updatePriorityStatus(int status) {
		if(this.status != null) {
			this.status.setSelectedIndex(status);
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	

}
