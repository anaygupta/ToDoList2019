package cse360.todo.Window;

import javax.swing.JButton;

public class TodoButton extends JButton{

	private static final long serialVersionUID = 5185402498022600108L;
	/**
	 * Creates todo button without a name
	 */
	public TodoButton() {
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
	/**
	 * Creates the todo butotn with the passed name
	 * @param name the name
	 */
	public TodoButton(String name) {
		this.setText(name);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}

}
