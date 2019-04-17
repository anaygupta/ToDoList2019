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
import cse360.todo.ListBox.ListBox;
import cse360.todo.ListBox.ModifiableListBox;
import cse360.todo.Window.TodoButton;

public class DueDateWindow extends JDialog{
	
	private static final long serialVersionUID = -3829842515450002408L;
	
	private ModifiableListBox box;
	
	public DueDateWindow(ModifiableListBox box) {
		this.box = box;
	}
	
	private void build(){
		this.setTitle("Set Due Date");
		//this.setIconImages(window.icon.getIcons());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setSize(screenSize.width / 5, screenSize.height / 3);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		JPanel datePanel = datePanel();
		
		this.add(datePanel);
		
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
	
	
	
	
	
	
	private JPanel datePanel(){
		JPanel dueDate = new JPanel();
		dueDate.setBorder(BorderFactory.createTitledBorder("Due Date"));
		
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
				if(DueDate.isFutureDate(addDate.getDate())){
					box.getSaveData().setDate(addDate);
					box.updateDueDateDisplay(months.getSelectedIndex() + 1,  days.getItemAt(days.getSelectedIndex()), years.getItemAt(years.getSelectedIndex()));
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
