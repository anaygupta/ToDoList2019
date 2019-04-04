package cse360.todo.Window;

import javax.swing.JButton;

public class TodoButton extends JButton{

	private static final long serialVersionUID = 5185402498022600108L;

	public TodoButton() {
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
	
	public TodoButton(String name) {
		this.setText(name);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}

}
