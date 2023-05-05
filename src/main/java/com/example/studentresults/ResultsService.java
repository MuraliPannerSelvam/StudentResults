package com.example.studentresults;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultsService {
	@Autowired
	ResultsDao resDao;

	public String addResults(List<Results> res) {
		
		return resDao.addResults(res);
	}

	public Results getResults(int id) {
		return resDao.getResults(id);
	}

	public String updateResults(Results id) {
		return resDao.updateResults(id);
	}

	public String deleteResults(int id) {
		return resDao.deleteResults(id);
	}

	public String addResultsList(List<Results> res) {
		return resDao.addResultsList(res);
	}

	public List<Results> getResultsList() {
		return resDao.getResultsList();
	}
	
	public List<Results> getResultsRange() {
		return resDao.getResultsRange();
	}

	public Results getTopperResults() {
		List<Results> all=resDao.getResultsList();
		int max=all.get(0).getTotalMarks();
		Results temp=all.get(0);
		for(Results x : all) {
			if(x.getTotalMarks()>max) {
				max=x.getTotalMarks();
				temp=x;
			}
		}
		return temp;
	}
	
	public List<Results> getResultsTopThree() {
		return resDao.getResultsTopThree();
	}

}
