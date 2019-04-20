package cse360.todo.Window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cse360.todo.Data.DueDate;
import cse360.todo.ListBox.ModifiableListBox;
import cse360.todo.Window.TodoButton;

public class DueDateWindow extends JDialog{
	
	private static final long serialVersionUID = -3829842515450002408L;
	
	public static final int DUE_DATE = 0;
	public static final int START_DATE = 1;
	public static final int END_DATE = 2;
	
	private ModifiableListBox box;
	private int mode;
	
	public DueDateWindow(ModifiableListBox box, int mode) {
		this.box = box;
		this.mode = mode;
	}
	
	private void build(){
		if(mode == DueDateWindow.DUE_DATE) {
			this.setTitle("Set Due Date");
		}else if(mode == DueDateWindow.START_DATE) {
			this.setTitle("Set Start Date");
		}else {
			this.setTitle("Set End Date");
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setSize(screenSize.width / 5, screenSize.height / 3);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		JPanel datePanel = datePanel();
		
		this.add(datePanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setResizable(true);
		this.setVisible(true);
		
	}
	
	
	
	private JPanel datePanel(){
		JPanel dueDate = new JPanel();
		if(mode == DueDateWindow.DUE_DATE) {
			dueDate.setBorder(BorderFactory.createTitledBorder("Due Date"));
		}else if(mode == DueDateWindow.START_DATE) {
			dueDate.setBorder(BorderFactory.createTitledBorder("Start Date"));
		}else {
			dueDate.setBorder(BorderFactory.createTitledBorder("End Date"));
		}
		
		
		JComboBox<String> months = new JComboBox<String>();
		for(String m : DueDate.monthNames()){
			months.addItem(m);
		}
		
		dueDate.add(months);
		
		JComboBox<Integer> days = new JComboBox<Integer>();
		for(int i = 0; i < DueDate.getDaysInMonth(0, DueDate.getCurrentYear()); i++){
			days.addItem(i + 1);
		}
		
		dueDate.add(days);
		JComboBox<Integer> years = new JComboBox<Integer>();
		for(int i = DueDate.getCurrentYear(); i <= DueDate.getCurrentYear() + 100; i++){
			years.addItem(i);
		}
		dueDate.add(years);
		
		
		if(mode == DueDateWindow.DUE_DATE) {
			if(box.getSaveData().getDate() != null){
				Date date = box.getSaveData().getDate().getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				months.setSelectedIndex(cal.get(Calendar.MONTH));
				years.setSelectedItem(cal.get(Calendar.YEAR));
				days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			}else{
				Calendar cal = Calendar.getInstance();
				months.setSelectedIndex(cal.get(Calendar.MONTH));
				years.setSelectedItem(cal.get(Calendar.YEAR));
				days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			}
		}else if(mode == DueDateWindow.START_DATE) {
			if(box.getSaveData().getStatus().getStartDate().getDate() != null){
				Date date = box.getSaveData().getStatus().getStartDate().getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				months.setSelectedIndex(cal.get(Calendar.MONTH));
				years.setSelectedItem(cal.get(Calendar.YEAR));
				days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			}else{
				Calendar cal = Calendar.getInstance();
				months.setSelectedIndex(cal.get(Calendar.MONTH));
				years.setSelectedItem(cal.get(Calendar.YEAR));
				days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			}
		}else {
			if(box.getSaveData().getStatus().getEndDate().getDate() != null){
				Date date = box.getSaveData().getStatus().getEndDate().getDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				months.setSelectedIndex(cal.get(Calendar.MONTH));
				years.setSelectedItem(cal.get(Calendar.YEAR));
				days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			}else{
				Calendar cal = Calendar.getInstance();
				months.setSelectedIndex(cal.get(Calendar.MONTH));
				years.setSelectedItem(cal.get(Calendar.YEAR));
				days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			}
		}
		
		
		
		months.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dayCount = DueDate.getDaysInMonth(months.getSelectedIndex(), years.getItemAt(years.getSelectedIndex()));
				int index = days.getSelectedIndex();
				days.removeAllItems();
				for(int i = 0; i < dayCount; i++){
					days.addItem(i + 1);
				}
				if(days.getItemCount() > index){
					days.setSelectedIndex(index);
				}
			}
		});
		
		years.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(months.getSelectedIndex() == 1){
					int dayCount = DueDate.getDaysInMonth(months.getSelectedIndex(), years.getItemAt(years.getSelectedIndex()));
					int index = days.getSelectedIndex();
					days.removeAllItems();
					for(int i = 0; i < dayCount; i++){
						days.addItem(i + 1);
					}
					if(days.getItemCount() > index){
						days.setSelectedIndex(index);
					}
				}
			}
		});
		
		
		TodoButton apply = new TodoButton("Apply");
		apply.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DueDate addDate = new DueDate(months.getSelectedIndex() + 1,  days.getItemAt(days.getSelectedIndex()), years.getItemAt(years.getSelectedIndex()));
				if(DueDate.isFutureDate(addDate)){
					if(mode == DueDateWindow.DUE_DATE) {
						box.getSaveData().setDate(addDate);
						box.updateDueDateDisplay(months.getSelectedIndex() + 1,  days.getItemAt(days.getSelectedIndex()), years.getItemAt(years.getSelectedIndex()));
					}else if(mode == DueDateWindow.START_DATE) {
						box.getSaveData().getStatus().setStartDate(addDate);
					}else {
						box.getSaveData().getStatus().setEndDate(addDate);
					}
					DueDateWindow.this.dispose();
				}else{
					JOptionPane.showMessageDialog(DueDateWindow.this, "Date must be in the future!", "Date Error", JOptionPane.WARNING_MESSAGE);
					Calendar cal = Calendar.getInstance();
					months.setSelectedIndex(cal.get(Calendar.MONTH));
					years.setSelectedItem(cal.get(Calendar.YEAR));
					days.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
				}
				
			}
			
		});
		dueDate.add(apply);
		return dueDate;
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
