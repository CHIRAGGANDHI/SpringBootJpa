package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("gandhi.ck@gmail.com")
                .firstName("Chirag")
                .lastName("Gandhi")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .email("gandhi.ck@gmail.com")
                .mobile("1234567890")
                .name("Chirag")
                .build();

        Student student = Student.builder()
                .firstName("Hiral")
                .emailId("gandhi.hr@gmail.com")
                .lastName("Gandhi")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("StudentList= " + studentList);

    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("chirag");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("hi");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Chirag");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("gandhi.ck@gmail.com");

        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("gandhi.ck@gmail.com");

        System.out.println("student = " + firstName);
    }

    @Test
    public void printStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("gandhi.ck@gmail.com");

        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("Chirag Gandhi", "gandhi.ck@gmail.com");
    }
}