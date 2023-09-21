package com.training.studentservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.studentservice.model.Student;
import com.training.studentservice.repository.StudentRepository;

@RestController  //Combination of@Controller + @ResponseBody
@RequestMapping("/studapi")
public class StudentController {
	
	@Autowired
	StudentRepository repository;
	
	@GetMapping("/stud")
	public List<Student> getstud() {
		
//		studList.add(new Student("Raj" , 18, "A+"));
//		studList.add(new Student("Adi" , 21, "B+"));
//		studList.add(new Student("Shruti" , 23, "A"));
//		studList.add(new Student("Rahul" , 20, "F"));
//		
		return repository.findAll();
	}
	
	@PostMapping("/post")
	public void post(@RequestBody Student stud) {
		repository.save(stud);
	}
	
	@GetMapping("stud/{id}")
	public Student getStudentById(@PathVariable("id") long id) {
		
		Optional<Student> stud = repository.findById(id);
		
		if(stud.isPresent()) {
			return stud.get();
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/stud")
	public Student getstud(@RequestBody long id) {
		
		Optional<Student> stud = repository.findById(id);
		
		if(stud.isPresent()) {
			return stud.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/stud/{id}")
		public void updateUser(@PathVariable("id") long id, @RequestBody Student stud) {
		Optional<Student> oldData = repository.findById(id);
		
		if(oldData.isPresent()) {
			Student std = oldData.get();
			std.setAge(stud.getAge());
			std.setGrade(stud.getGrade());
			std.setStudName(stud.getStudName());
			repository.save(std);
		}
		else {
			System.out.println("No Data Found");
		}
		}
	
	@DeleteMapping("/stud/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		Optional<Student> stud = repository.findById(id);
		if(stud.isPresent()) {
			repository.delete(stud.get());
		}
		else {
			return;
		}
	}
}

