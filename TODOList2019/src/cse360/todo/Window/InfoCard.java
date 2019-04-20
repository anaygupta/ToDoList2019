package cse360.todo.Window;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InfoCard extends JPanel{

	private static final long serialVersionUID = -631142821277540340L;
	
	private JLabel currentPath;
	private TodoButton add;
	public Window window;
	
	public InfoCard(Window window) {
		this.window = window;
		this.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(10, 10, 0, 10));
		GridBagConstraints c = new GridBagConstraints();
		currentPath = new JLabel("Current Path: None");
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1.0;
		c.weightx = 0.5;
		this.add(currentPath, c);
		c.weightx = 0.0;
		c.gridx = 1;
		c.insets = new Insets(0,0,5,0);
		add = new TodoButton("Add Task");
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AddTaskWindow atw = new AddTaskWindow(InfoCard.this.window, null);
				atw.start();
			}
		});
		this.add(add, c);
		
	}
	
	public void setCurrentPathText(String currentPath) {
		this.currentPath.setText(currentPath);
	}

}
