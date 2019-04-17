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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import cse360.todo.Data.ListBoxData;
import cse360.todo.Window.DueDateWindow;
import cse360.todo.Window.TodoButton;
import cse360.todo.Window.Window;

public class ModifiableListBox extends JPanel{
	private TodoTextArea listItem;
	private TodoButton date, status;
	private JTextField name;
	//private JComboBox<String> status;
	private JLabel priority;
	//public UpDownArrowPanel ud;
	private Window window;
	//public BasicArrowButton up, down;
	
	ListBoxData saveData;
	
	public ModifiableListBox(Window window) {
		this.window = window;
		saveData = new ListBoxData();
		
		
		//window.data.getAllSaveData().add(saveData);
		
		this.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(10, 0, 0, 0));
		//this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10))));
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
		
		name = new JTextField("");
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		name.setColumns(10);
		name.setToolTipText("Name");
		name.setMinimumSize(new Dimension(110, 18));

		data.add(name, c);
		
		name.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {}

			@Override
			public void keyReleased(KeyEvent arg0) {
				saveData.setName(name.getText());
			}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		
		//1
		c.gridx = 1;
		c.weighty = 0.0;
		c.weightx = 0.0;
		priority = new JLabel("Priority Level: " + saveData.getPriority());
		priority.setToolTipText("Priority Level");
		data.add(priority, c);
		
		//2
		c.gridx = 2;
		date = new TodoButton(saveData.getDate().getMonth() + "/" + saveData.getDate().getDay() + "/" + saveData.getDate().getYear());
		date.setToolTipText("Set Due Date");
		data.add(date, c);
		
		date.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DueDateWindow ddw = new DueDateWindow(ModifiableListBox.this);
				ddw.start();
				
			}
			
		});
		
		//3 
		c.gridx = 3;
		c.insets = new Insets(0,0,0,0);
		c.weighty = 1.0;
		c.weightx = 1.0;
		listItem = new TodoTextArea(this);
		data.add(listItem, c);
		
		c.gridx = 4;
		c.weighty = 0;
		c.weightx = 0;
		
		status = new TodoButton("Status");
		data.add(status);
		
		return data;
		
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
		return listItem.getTextArea();
	}

	public ListBoxData getSaveData() {
		return saveData;
	}
	
	public void setSaveData(ListBoxData saveData){
		this.saveData = saveData;
	}

}
