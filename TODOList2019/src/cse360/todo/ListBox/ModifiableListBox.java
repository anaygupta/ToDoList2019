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
	/**
	 * Creates a modifiable list box with the given index
	 * @param index the status index
	 */
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
	/**
	 * Builds the section of the row that contains: Priority, Due Date, Description, and status
	 */
	public JPanel buildDataRow() {
		
		JPanel data = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//0
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 25, 1, 25);
		priorityLabel = new JLabel("Priority Level: ");
		data.add(priorityLabel, c);

				
		//1
		c.gridy = 1;
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
		c.gridy = 2;
		dueDateLabel = new JLabel("Due Date: ");
		data.add(dueDateLabel, c);
		
		//3
		c.gridy = 3;
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
		c.gridy = 4;
		descriptionLabel = new JLabel("Description: ");
		data.add(descriptionLabel, c);
		
		//5 
		c.gridy = 5;
		c.weighty = 1.0;
		c.weightx = 1.0;
		listItem = new JTextArea(saveData.getText());
        listItem.getDocument().putProperty("filterNewlines", true);
		listItem.setBorder(BorderFactory.createLineBorder(Color.black));
		listItem.setToolTipText("Description");
		data.add(listItem, c);
		
		//6
		c.gridy = 6;
		c.weightx = 0;
		c.weighty = 0;
		statusLabel = new JLabel("Status: ");
		data.add(statusLabel, c);
		
		//7
		c.gridy = 7;
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
	/**
	 * @return the priority of the task in a JTextField
	 */
	public JTextField getPriority() {
		return this.priority;
	}
	/**
	 * Sets the status index to the given value.
	 * @param index the status index
	 */
	public void setSatusSelectedIndex(int index) {
		if(this.status != null && index >= 0 && index < 3) {
			nonUserStatusUpdate = true;
			this.status.setSelectedIndex(index);
		}
	}
	/**
	 * @param priority Sets the priority to priority
	 */
	public void updatePriorityDisplay(int priority) {
		if(this.priority != null) {
			this.priority.setText("" + priority);
		}
	}
	/**
	 * Updates the due date being displayed
	 * @param month new month
	 * @param day new day
	 * @param year new year
	 */
	public void updateDueDateDisplay(int month, int day, int year) {
		if(this.date!= null) {
			this.date.setText(month + "/" + day + "/" + year);
		}
	}
	/**
	 *Updates the priority status to the passed status
	 *@param status new status
	 */
	public void updatePriorityStatus(int status) {
		if(this.status != null) {
			this.status.setSelectedIndex(status);
		}
	}
	/**
	 * Returns the JTextArea of the list box
	 * @return listItem JTextArea that contains the description
	 */
	public JTextArea getText(){
		return listItem;
	}
	/**
	 * Returns the current saved data
	 */
	public ListBoxData getSaveData() {
		return saveData;
	}
	/**
	 * Sets the saved data to the passed data
	 * @param saveData the new saveData
	 */
	public void setSaveData(ListBoxData saveData){
		this.saveData = saveData;
	}
	/**
	 * Returns the status index
	 * @return the status index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * Sets the status index
	 * @param index the new status index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	

}
