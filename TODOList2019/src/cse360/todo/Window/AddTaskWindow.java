package cse360.todo.Window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cse360.todo.Data.ListBoxData;
import cse360.todo.ListBox.ListBox;
import cse360.todo.ListBox.ModifiableListBox;

public class AddTaskWindow extends JDialog{
	
	private Window window;
	private ListBox box;
	private ModifiableListBox lbox;
	
	public AddTaskWindow(Window window,ListBox box) {
		this.window = window;
		this.box = box;
	}
	
	private void build() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if(box == null) {
			this.setTitle("Add Task");
		}else {
			this.setTitle("Edit Task");
		}
		this.setLayout(new GridBagLayout());
		this.setSize(screenSize.width / 3, screenSize.height / 3);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;


		
		//0 0
		c.gridy = 0;
		c.gridx = 0;
		JPanel filler = new JPanel();
		this.add(filler, c);
		c.weightx = 0;
		c.gridx = 1;
		if(box == null) {
			TodoButton add = new TodoButton("Add");
			this.add(add, c);
			add.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ListBox add = new ListBox(AddTaskWindow.this.window, false);
					
					add.setSaveData(lbox.getSaveData());
					add.updateNameText(lbox.getSaveData().getName());
					add.updatePriorityDisplay(lbox.getSaveData().getPriority());
					add.updateDueDateDisplay(lbox.getSaveData().getDate().getMonth(), lbox.getSaveData().getDate().getDay(), lbox.getSaveData().getDate().getYear());
					add.getText().setText(lbox.getSaveData().getText());
					
					boolean valid = true;
					
					for(ListBox boxes : AddTaskWindow.this.window.index.getListBoxes()) {
						if(boxes.getText().getText().equals(add.getText().getText())) {
							JOptionPane.showMessageDialog(AddTaskWindow.this, "Description must be unique!", "Add Error", JOptionPane.ERROR_MESSAGE);
							valid = false;
							break;
						}
					}
					
					if(valid) {
						window.index.add(add);
						AddTaskWindow.this.dispose();
					}
				}
				
			});
		}else {
			TodoButton edit = new TodoButton("Apply");
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					boolean valid = true;
					
					for(ListBox boxes : AddTaskWindow.this.window.index.getListBoxes()) {
						if(boxes.getText().getText().equals(lbox.getText().getText()) && lbox.getSaveData().getPriority() != boxes.getSaveData().getPriority()) {
							JOptionPane.showMessageDialog(AddTaskWindow.this, "Description must be unique!", "Add Error", JOptionPane.ERROR_MESSAGE);
							valid = false;
							break;
						}
					}
					
					if(valid) {
						box.setSaveData(lbox.getSaveData());
						box.updateNameText(lbox.getSaveData().getName());
						box.updatePriorityDisplay(lbox.getSaveData().getPriority());
						box.updateDueDateDisplay(lbox.getSaveData().getDate().getMonth(), lbox.getSaveData().getDate().getDay(), lbox.getSaveData().getDate().getYear());
						box.getText().setText(lbox.getSaveData().getText());
						AddTaskWindow.this.dispose();
					}
				}
				
			});
			this.add(edit, c);
		}
		
		
		//0 1
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;
		if(box == null) {
			lbox = new ModifiableListBox(window);
			this.add(lbox, c);
		}else {
			lbox = new ModifiableListBox(window);
			lbox.setSaveData(box.getSaveData());
			lbox.updateNameText(box.getSaveData().getName());
			lbox.updatePriorityDisplay(box.getSaveData().getPriority());
			lbox.updateDueDateDisplay(box.getSaveData().getDate().getMonth(), box.getSaveData().getDate().getDay(), box.getSaveData().getDate().getYear());
			lbox.getText().setText(box.getSaveData().getText());
			this.add(lbox, c);
		}
		
		
		this.setResizable(true);
		if(box != null) {
			this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		}else {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
		this.setVisible(true);
		
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
