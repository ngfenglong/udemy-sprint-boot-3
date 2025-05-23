package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
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
            // deleteCourse(appDao);
            // createCourseAndReview(appDao);
            // retrieveCourseAndReviews(appDao);
            // deleteCourseAndReviews(appDao);
            // createCourseAndStudents(appDao);
            // findCourseAndStudents(appDao);
            // findStudentAndCourses(appDao);
            // addMoreCoursesForStudent(appDao);

            deleteStudent(appDao);


        };
    }

    private void deleteStudent(AppDAO appDao) {
        int theId = 1;

        System.out.println("Deleting student id: "+ theId);
        appDao.deleteStudentById(theId);

        System.out.println("Done!");
    }

    private void addMoreCoursesForStudent(AppDAO appDao) {
        int theId = 2;
        Student tempStudent = appDao.findStudentAndCoursesByStudentId(theId);

        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Artari 2600 - Game Development");

        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Saving student: " + tempStudent);
        System.out.println("assicated courses: " + tempStudent.getCourses());

        appDao.update(tempStudent);
        System.out.println("Done");
    }

    private void findStudentAndCourses(AppDAO appDao) {
        int theId = 2;
        Student tempStudent = appDao.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loadedd students: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());
        System.out.println("Done");

    }

    private void findCourseAndStudents(AppDAO appDao) {
        int theId = 10;

        Course tempCourse = appDao.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded Course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());

        System.out.println("Done!");
    }

    private void createCourseAndStudents(AppDAO appDao) {
        Course tempCourse = new Course("Pacman - How to Score One Million Points");

        Student tempStudent1 = new Student("John", "Doe", "Doe@email.com");
        Student tempStudent2 = new Student("Mary", "Public", "Mary@email.com");

        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        System.out.println("Saving the course: "+ tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());
        appDao.save(tempCourse);

        System.out.println("Done");
    }

    private void deleteCourseAndReviews(AppDAO appDao) {
        int theId = 10;

        System.out.println("Deleting courses id : " + theId);

        appDao.deleteCourseById(theId);

        System.out.println("Done");
    }

    private void retrieveCourseAndReviews(AppDAO appDao) {
        int theId = 10;
        Course tempCourse = appDao.findCourseAndReviewById(theId);

        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        System.out.println("Done");
    }

    private void createCourseAndReview(AppDAO appDao) {
        Course tempCourse = new Course("Pacman - How to SCore One Million Points");
        tempCourse.addReview(new Review("Great Course...loved it"));
        tempCourse.addReview(new Review("Cool course, job well done"));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot"));

        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDao.save(tempCourse);
        System.out.println("Done");

    }

    private void deleteCourse(AppDAO appDao) {
        int theId = 10;

        System.out.println("Deleting course id: " + theId);

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
