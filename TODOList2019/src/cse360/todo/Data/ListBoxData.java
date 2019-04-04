package cse360.todo.Data;

import java.io.Serializable;

public class ListBoxData implements Serializable, Comparable<ListBoxData>{

	private static final long serialVersionUID = 3875878272603603242L;
	
	private String name;
	private String text;
	private Status status;
	private int priority;
	private DueDate date;


	public ListBoxData() {
		setName("");
		setText("");
		setStatus(new Status());
		setPriority(1);
		setDate(new DueDate());
	}

	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	
	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}

	public DueDate getDate() {
		return date;
	}

	public void setDate(DueDate date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}



	@Override
	public int compareTo(ListBoxData other) {
		if(this.priority > other.getPriority()) {
			return 1;
		}else if (this.getPriority() == other.getPriority()) {
			return 0;
		}else {
			return -1;
		}
	}

}
