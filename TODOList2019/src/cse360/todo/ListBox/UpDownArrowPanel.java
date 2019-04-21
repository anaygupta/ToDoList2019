package cse360.todo.ListBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import cse360.todo.Window.TodoButton;
import cse360.todo.Window.Window;

public class UpDownArrowPanel extends JPanel{
	
	private static final long serialVersionUID = 8616691181302723179L;
	
	public TodoButton up, down;
	private ListBox box;
	private Window window;
	/**
	 * Creates a new UpDownArrowPanel, which extends JPanel
	 */
	public UpDownArrowPanel(Window window, ListBox box) {
		this.window = window;
		this.box = box;
		up = new TodoButton("^");
		up.setMargin(new Insets(0,0,0,0));
		this.add(up);
		up.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UpDownArrowPanel.this.window.index.swapUp(UpDownArrowPanel.this.box);
			}
			
		});
		down = new TodoButton("v");
		down.setMargin(new Insets(0,0,0,0));
		this.add(down);
		down.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UpDownArrowPanel.this.window.index.swapDown(UpDownArrowPanel.this.box);
			}
		
		});
	}
}
