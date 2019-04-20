package cse360.todo.Data;

import java.io.Serializable;

public class ListBoxData implements Serializable{

	private static final long serialVersionUID = 3875878272603603242L;
	/**
	 * Description of the task
	 */
	private String text;
	/**
	 * Status of the task
	 */
	private Status status;
	/**
	 * Priority of the task
	 */
	private int priority;
	/**
	 * Due date of the task
	 */
	private DueDate date;

	/**
	 * Constructor. Sets string values to empty string, status to default status, priority to 1, and dueDate to default due data.
	 */
	public ListBoxData() {
		setText("");
		setStatus(new Status());
		setPriority(1);
		setDate(new DueDate());
	}
	/**
	 * Returns the description of the task
	 * @return the description
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the value of the descriptor
	 * @param text new value for description
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the priority
	 * @return object priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority of the ListBoxData
	 * @param priority new priority for task
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * Returns the due date
	 * @return the due date
	 */
	public DueDate getDate() {
		return date;
	}
	/**
	 * Sets the value of due date to the passed data object
	 * @param date DueDate object to be used
	 */
	public void setDate(DueDate date) {
		this.date = date;
	}

	/**
	 * Returns the current name of the task
	 * @return The status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Changes the current status to the passed Status object
	 * @param status New status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}



	

}
