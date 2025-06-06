package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Student;

@RestController
@RequestMapping("/api")
public class StudentController {

	private List<Student> students;
	
	public StudentController() {
		this.students = new ArrayList<Student>(List.of(
				new Student(1, "NoName", 60),
				new Student(2, "Vishal", 100)));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return this.students;
	}
}
