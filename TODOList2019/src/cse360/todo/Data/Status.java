package cse360.todo.Data;

import java.io.Serializable;

public class Status implements Serializable {

	private static final long serialVersionUID = 5374996775728072736L;
	
	public static final String NOT_STARTED = "Not started";
	public static final String IN_PROGRESS = "In progress";
	public static final String FINISHED = "Finished";
	
	private int statusIndex;
	
	/**
	 * The startDate of the status
	 */
	private DueDate startDate;
	/**
	 * The endDate of the status
	 */
	private DueDate endDate;
	
	/**
	 * Default constructor. Sets the value of status index to zero and the DueDates to NULL
	 */
	public Status() {
		setStatusIndex(0);
		setStartDate(new DueDate());
		setEndDate(new DueDate());
	}
	/**
	 * @return the current status index of the status object
	 */
	public int getStatusIndex() {
		return statusIndex;
	}

	/**
	 * Sets the objects status index to the passed value
	 * @param statusIndex the new value of the status index
	 */
	public void setStatusIndex(int statusIndex) {
		this.statusIndex = statusIndex;
	}

	/**
	 * @return the start date of the status object in the form of a DueDate object
	 */
	public DueDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the new DueDate objects for the startDate for the task
	 */
	public void setStartDate(DueDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the end date of the status
	 */
	public DueDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the new DueDate object for the endDate
	 */
	public void setEndDate(DueDate endDate) {
		this.endDate = endDate;
	}
	/**
	 * Sets the status depending on status index
	 */
	public String getStatusText() {
		if(statusIndex == 0) {
			return Status.NOT_STARTED;
		}else if(statusIndex == 1) {
			return Status.IN_PROGRESS;
		}else {
			return Status.FINISHED;
		}
	}

	
}
