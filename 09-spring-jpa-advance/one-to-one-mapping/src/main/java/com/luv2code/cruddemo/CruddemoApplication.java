package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDao){
		return runner -> {
			// createInstructor(appDao);
			// findInstructor(appDao);
			deleteInstructor(appDao);
		};
	}

	private void deleteInstructor(AppDAO appDao) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDao.deleteInstructorById(theId);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDao) {
		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDao) {
//		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@lub2code.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube,.com", "Luv 2 code !!!");


		Instructor tempInstructor = new Instructor("Madhu", "Patel", "Patel@lub2code.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube,.com", "Guitar");


		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor " + tempInstructor);
		appDao.save(tempInstructor);

		System.out.println("Done!");
	}
}
