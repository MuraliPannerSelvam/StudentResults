package com.example.studentresults;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResultsController {
	@Autowired
	ResultsService resSer;

	@Autowired
	RestTemplate restTemp;

	@PostMapping(value = "/addStudentMuraliResults")
	public String addStudentResults() {
		String url1 = "http://localhost:8080/getStudentList";
		String url2 = "http://localhost:8081/getMarkSheetList";
		List<Results> res = new ArrayList<>();
		
		ResponseEntity<List<Student>> r1 = restTemp.exchange(url1, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
		List<Student> s = r1.getBody();
		
		ResponseEntity<List<MarkSheet>> r2 = restTemp.exchange(url2, HttpMethod.GET, null, new ParameterizedTypeReference<List<MarkSheet>>() {});
		List<MarkSheet> m = r2.getBody();
		
		
		int id= 0;
		int rollNumber = 0;
		String name = null;
		int totalMarks= 0;
		int percentage = 0;
		
		for(int i=0; i<s.size(); i++) {
			id = s.get(i).getId();
			rollNumber = s.get(i).getRollNumber();
			name = s.get(i).getName();
			
			if (s.get(i).getAttendance() > 90) {
			totalMarks = ((m.get(i).getSem1_total() + m.get(i).getSem2_total())/2 ) + 5;
			}
			else {
				totalMarks = ((m.get(i).getSem1_total() + m.get(i).getSem2_total())/2 );
			}
			if(((m.get(i).getSem1_total() + m.get(i).getSem2_total())/2) + 5 > 100) {
				totalMarks = 100;
			}
			
			percentage = totalMarks;
			
			res.add( new Results(id,rollNumber,name,totalMarks, percentage));
		}
		
		return resSer.addResults(res);
	}
		
		

//		@PostMapping(value = "/addStudentResults")
//		public String addStudentResults(@ResquestBody) {
//			String url1 = "http://localhost:8080/getStudentList";
//			String url2 = "http://localhost:8081/getMarkSheetList";
//		ResponseEntity<Student> response1 = restTemp.exchange(url1, HttpMethod.GET, null,
//				Student.class);
//		Student result1 = response1.getBody();
//
//		res.setName(result1.getName());
//
//		ResponseEntity<MarkSheet> response2 = restTemp.exchange(url2, HttpMethod.GET, null,
//				MarkSheet.class);
//
//		MarkSheet result2 = response2.getBody();
//
//		if (result1.getAttendance() >= 90) {
//			res.setTotalMarks(((result2.getSem1_total() + result2.getSem2_total()) / 2) + 5);
//
//		} else {
//			res.setTotalMarks((result2.getSem1_total() + result2.getSem2_total()) / 2);
//		}
//
//		if (res.getTotalMarks() > 100) {
//			res.setTotalMarks(100);
//		}
//
//		res.setPercentage(res.getTotalMarks());
//
//		return resSer.addResults(res);
		

	@GetMapping(value = "/getResults/{id}")
	public Results getResults(@PathVariable int id) {
		return resSer.getResults(id);
	}

	@PutMapping(value = "/updateResults/{id}")
	public String updateResults(@RequestBody Results id) {
		return resSer.updateResults(id);
	}

	@DeleteMapping(value = "/deleteResults/{id}")
	public String deleteResults(@PathVariable int id) {
		return resSer.deleteResults(id);
	}

	@PostMapping(value = "/addResultList")
	public String addResultsList(@RequestBody List<Results> res) {
		return resSer.addResultsList(res);
	}

	@GetMapping(value = "/getResults")
	public List<Results> getResultsList() {
		return resSer.getResultsList();
	}

	@GetMapping(value = "/getResultsRange")
	public List<Results> getResultsRange() {
		return resSer.getResultsRange();
	}

	@GetMapping(value = "/getTopperResults")
	public Results getTopperResults() {
		return resSer.getTopperResults();
	}
	
	@GetMapping(value="/getResultsTopThree")
	public List<Results> getResultsTopThree() {
		return resSer.getResultsTopThree();
	}

}
