package com.example.studentresults;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResultsDao {
	@Autowired
	ResultsRepository resRepo;
	
	public String addResults(List<Results >res) {
		 resRepo.saveAll(res);
		 return "Success";
	}

	public Results getResults(int id) {
		return resRepo.findById(id).get();
	}

	public String updateResults(Results id) {
		resRepo.save(id);
		return "SUccessFully updated";
	}

	public String deleteResults(int id) {
		resRepo.deleteById(id);
		return "Successfully Deleted";
	}

	public String addResultsList(List<Results> res) {
		resRepo.saveAll(res);
		return "Successfully Saved to list";
	}

	public List<Results> getResultsList() {
		return resRepo.findAll();
	}
	
	public List<Results> getResultsRange() {
		return resRepo.getResultsRange();
	}
	
	public List<Results> getResultsTopThree() {
		return resRepo.getResultsTopThree();
	}
}
