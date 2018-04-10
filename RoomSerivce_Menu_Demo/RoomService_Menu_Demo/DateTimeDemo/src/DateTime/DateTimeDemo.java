package DateTime;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;


public class DateTimeDemo {

	public static void main(String[] args) {
		
		/*
			First Example: 
			--> Shows how both date and time together can be  
				displayed properly with formatting
		*/
		
		LocalDateTime dateTime = LocalDateTime.now();	//.now() will call the time base on your pc
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  

	    String formatDateTime = dateTime.format(formatter);

	    System.out.println("First Example: ");
	    System.out.println("Before format: " + dateTime);
	    System.out.println("After format: " + formatDateTime);
		
	    
	    /*
			Second Example: 
			--> Shows how both date and time can be store/use separately
				and/or formatted
	    */
	    
	    System.out.println("\nSecond Example: ");
	    
	    LocalDate date = LocalDate.now();
	    LocalTime time = LocalTime.now();
	    
	    //format Date
	    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	    String formatDateString = date.format(formatDate);
	    
	    System.out.println(formatDateString);
	    
	    
	    //format Time
	    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");	//display hours and minutes only
	    String formatTimeString = time.format(formatTime);
	    
	    System.out.println(formatTimeString);
	    System.out.println();
	    
	    
		

	}

}
