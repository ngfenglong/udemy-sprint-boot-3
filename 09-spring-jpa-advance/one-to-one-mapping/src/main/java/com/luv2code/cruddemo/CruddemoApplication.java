package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDao) {
        return runner -> {
            // createInstructor(appDao);
            // findInstructor(appDao);
            // deleteInstructor(appDao);
            // findInstructorDetails(appDao);
            // deleteInstructorDetail(appDao);
            // createInstructorWithCourses(appDao);
            // findInstructorWithCourses(appDao);
            // findCoursesForInstructor(appDao);
            // findInstructorWithCoursesJoinFetch(appDao);
            // updateInstructor(appDao);
            // updateCourse(appDao);
            deleteCourse(appDao);
        };
    }

    private void deleteCourse(AppDAO appDao) {
        int theId = 10;

        System.out.println("Delting course id: " + theId);

        appDao.deleteCourseById(theId);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDao) {
        int theId = 10;

        System.out.println("Finding course id" + theId);
        Course tempCourse = appDao.findCourseById(theId);

        System.out.println("Updating course id: " + theId);
        tempCourse.setTitle("Enjoy the Simple Thing");

        appDao.update(tempCourse);
        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDao) {
        int theId = 1;
        System.out.println("Finding instructor id : " + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);

        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setLastName("TESTER");

        appDao.update(tempInstructor);

        System.out.println("Done! ");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDao) {
        int theId = 1;

        System.out.println("Finding the id:" + theId);
        Instructor tempInstructor = appDao.findInstructorByJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("Courses : " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDao) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDao.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        System.out.println("Finding Courses for instructor id: " + theId);
        List<Course> courses = appDao.findCoursesByInstructorId(theId);
        tempInstructor.setCourses(courses);

        System.out.println("The associated courses: " + tempInstructor.getCourses());
        System.out.println("Done");
    }

    private void findInstructorWithCourses(AppDAO appDao) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDao.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDao) {
        Instructor tempInstructor = new Instructor("Susan", "Public", "Susan.public@lub2code.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");

        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        tempInstructor.setInstructorDetail(tempInstructorDetail);
        tempInstructor.addCourse(tempCourse1);
        tempInstructor.addCourse(tempCourse2);

        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDao.save(tempInstructor);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDao) {
        int theId = 2;
        System.out.println("Deleting instructor detail id : " + theId);

        appDao.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetails(AppDAO appDao) {
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDao.findInstructorDetailsById(theId);

        System.out.println("tempInstructorDetail: " + tempInstructorDetail);

        System.out.println("the associate instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Done!");
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
