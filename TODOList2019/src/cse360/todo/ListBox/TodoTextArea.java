package cse360.todo.ListBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TodoTextArea extends JPanel{
	
	private static final long serialVersionUID = -3244471190812479265L;
	
	private JTextArea todo;
	public TodoTextArea(ModifiableListBox box) {
		this.setLayout(new BorderLayout());
		todo = new JTextArea();
		todo.setLineWrap(true);
		todo.setWrapStyleWord(true);
		todo.setBorder(BorderFactory.createLineBorder(Color.black));
		todo.setToolTipText("Description");
		todo.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {}

			@Override
			public void keyReleased(KeyEvent arg0) {
				box.getSaveData().setText(todo.getText());
			}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		this.add(todo);
	}
	
	public JTextArea getTextArea(){
		return todo;
	}
	
	

}
