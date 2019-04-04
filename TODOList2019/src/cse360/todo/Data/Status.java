package cse360.todo.Data;

import java.io.Serializable;

public class Status implements Serializable {

	private static final long serialVersionUID = 5374996775728072736L;
	
	private int statusIndex;
	
	private DueDate startDate;
	private DueDate endDate;
	
	public Status() {
		setStatusIndex(0);
		setStartDate(null);
		setEndDate(null);
	}

	public int getStatusIndex() {
		return statusIndex;
	}

	public void setStatusIndex(int statusIndex) {
		this.statusIndex = statusIndex;
	}

	public DueDate getStartDate() {
		return startDate;
	}

	public void setStartDate(DueDate startDate) {
		this.startDate = startDate;
	}

	public DueDate getEndDate() {
		return endDate;
	}

	public void setEndDate(DueDate endDate) {
		this.endDate = endDate;
	}

}
