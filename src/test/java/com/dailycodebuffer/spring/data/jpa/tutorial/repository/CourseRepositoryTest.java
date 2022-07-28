package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses(){
        List<Course> courses = repository.findAll();

        System.out.println("Courses = " + courses);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPagewithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                repository.findAll(firstPagewithThreeRecords).getContent();

        long totalElements =
                repository.findAll(firstPagewithThreeRecords).getTotalElements();

        long totalPages =
                repository.findAll(firstPagewithThreeRecords).getTotalPages();
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit")));
    }

    @Test
    public void printfindByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses = repository.findByTitleContaining("D", firstPageTenRecords).getContent();

        System.out.println("Courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .LastName("Morgen")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        repository.save(course);

    }
}