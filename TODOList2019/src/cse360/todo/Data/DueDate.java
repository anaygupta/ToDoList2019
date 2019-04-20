package cse360.todo.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DueDate implements Serializable{
	
	private static final long serialVersionUID = -6880459310047147770L;
	/**
	 * The day of MONTH and YEAR that the task is due
	 */
	/**
	 * The MONTH of YEAR that the task is due
	 */
	/**
	 * The year that the task is due
	 * 
	 */
	private int day, month, year;
	/**
	 * Date object to be used
	 */
	private Date date;
	/**
	 * Constructor that uses the default time zone defined in the java.util.Calendar library. Sets date to the value
	 * returned by the getTime function
	 */
	public DueDate(){
		
		Calendar cal = Calendar.getInstance();
		this.setDay(cal.get(Calendar.DAY_OF_MONTH));
		this.setMonth(cal.get(Calendar.MONTH) + 1);
		this.setYear(cal.get(Calendar.YEAR));
			
		date = cal.getTime();
	}
	/**
	 * Constructor that sets the dates to the passed values
	 * @param month 
	 * @param day
	 * @param year
	 */
	public DueDate(int month, int day, int year){
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
		
		String dateString = "" + month +"/" + day + "/" + year;
		
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
		} catch (ParseException e) {
			Calendar cal = Calendar.getInstance();
			this.setDay(cal.get(Calendar.DAY_OF_MONTH));
			this.setMonth(cal.get(Calendar.MONTH) + 1);
			this.setYear(cal.get(Calendar.YEAR));
			
			date = cal.getTime();
		}
	}
	
	
	
	public long getTimeToGo(){
		return date.getTime() - System.currentTimeMillis(); 
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getDate() {
		return date;
	}
	/**
	 * Returns true if the passed year is a leap year and false if not
	 * @param year the year to be checked
	 * @return true if leap year, false if not
	 */
	public static boolean isLeapYear(int year) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.YEAR, year);
		  return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
	}
	
	/**
	 * Checks if the Date passed is before or after the DueDate.
	 * @param date date to be chekced
	 * @return true if in the future, false if not
	 */
	public static boolean isFutureDate(DueDate date){
		 Calendar cal = Calendar.getInstance();
		 
		 if(cal.get(Calendar.DAY_OF_MONTH) == date.getDay() && cal.get(Calendar.MONTH) + 1 == date.getMonth() && cal.get(Calendar.YEAR) == date.getYear()) {
			 return true;
		 }
		 
		 if(cal.getTime().getTime() < date.getDate().getTime()){
			 return true;
		 }
		 return false;
	}
	
	/**
	 * 	Returns array of month names
	 * @return an array of that contains the name of each month in order
	 */
	public static String[] monthNames(){
		return new String[]{"January",
							"February",
							"March",
							"April",
							"May",
							"June",
							"July",
							"August",
							"September",
							"October",
							"November",
							"December"
						};
	}
	
	/**
	 * Returns current year based on default timezone.
	 * @return current years
	 */
	public static int getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * Returns the number of days in the month
	 * @param month month to be tested
	 * @param year year
	 * @return integer value of the month
	 */
	public static int getDaysInMonth(int month, int year){
		switch(month){
			case 0:
				return 31;
			case 1:
				if(isLeapYear(year)){
					return 29;
				}else{
					return 28;
				}
			case 2:
				return 31;
			case 3:
				return 30;
			case 4:
				return 31;
			case 5:
				return 30;
			case 6:
				return 31;
			case 7:
				return 31;
			case 8:
				return 30;
			case 9:
				return 31;
			case 10:
				return 30;
			case 11:
				return 31;
			default:
				return -1;
		
		}
	}

}
