package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import de.tekup.soap.models.whitetest.Address;
import de.tekup.soap.models.whitetest.Exam;
import de.tekup.soap.models.whitetest.ObjectFactory;
import de.tekup.soap.models.whitetest.Student;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;

@Service
public class ServiceDs {
	
	List<Student> students = new ArrayList<>();
	List<Exam> exams = new ArrayList<>();
	List<Address> addresses=new ArrayList<>();
	
	public WhiteTestResponse checkEligebilty(StudentRequest request) {
		
		 WhiteTestResponse response = new ObjectFactory().createWhiteTestResponse();

			
			
			Address adrAddress1=new Address();
			adrAddress1.setCity("nabeul");
			adrAddress1.setStreet("habib bourgiba");
			adrAddress1.setPostecode("8015");
			
			Address adrAddress2=new Address();
			adrAddress1.setCity("Tunis");
			adrAddress1.setStreet("habib thameur");
			adrAddress1.setPostecode("4000");
			
			Student student1= new Student();
			student1.setId(1);
			student1.setName("samer");
			student1.setAddress(adrAddress1);
			
			Student student2= new Student();
			student2.setId(2);
			student2.setName("Houssine");
			student2.setAddress(adrAddress1);
			
			addresses.add(adrAddress1);
			addresses.add(adrAddress2);
			
			
			students.add(student1);
			students.add(student2);
			
	  
			
			Exam exam1= new Exam();
			exam1.setCode("az01");
			exam1.setName("aaaa");
			
			Exam exam2=new Exam();
			exam2.setCode("az02");
			exam2.setName("bbb");
			
		    exams.add(exam1);
		    exams.add(exam2);
			
		    // Create Date Object
	        Date current_date = new Date();
	  
	        // current date time in standard format
	        System.out.println("Standard Format :- "
	                           + current_date);
	  
	        XMLGregorianCalendar xmlDate = null;
	  
	        // Gregorian Calendar object creation
	        GregorianCalendar gc = new GregorianCalendar();
	  
	        // giving current date and time to gc
	        gc.setTime(current_date);
	  
	     

	     
	     
	        
	        
		    for(Student st: students)
		    {	

		   	
		   		
		   	 for(Exam ex: exams)
			    {	
		   		if(st.getId()==request.getStudentId()&& ex.getCode().equals(request.getExamCode())) {

	
		   	   		
		   	   try {
		            xmlDate = DatatypeFactory.newInstance()
		                          .newXMLGregorianCalendar(gc);
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		   	   
		   		response.setDate(xmlDate);
		   		response.setStudent(st);
		   		response.setExam(ex);

		   	
		   	}
		   		
		   		else if(st.getId()==request.getStudentId()) {
		   		
		   			response.setBadRequests("Exam code not found");
			   		response.setStudent(st);
			   		
		   			
		   			
		   		}
		 
		   		else if(ex.getCode().equals(request.getExamCode())) {
			   		
				   	   
			   		response.setExam(ex);
			   		response.setBadRequests("ID student not found");
		   			
		   			
		   		}
		   		else if(response.getExam()== null && response.getStudent()==null) {
		   			response.setBadRequests("ID student and Exam Code not found !! ");
		   		}
		   		
		   	
		 
		   	}
		           
		    
	        }
		    
		  	    
			return response;
			
		}
		
	//Question 3
	public void getExams() {
		for (Exam exam:exams) {
			System.out.print(exam.getCode()+" "+exam.getName()+"\n");
		}
	}
		
}