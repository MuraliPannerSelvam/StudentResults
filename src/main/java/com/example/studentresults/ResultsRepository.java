package com.example.studentresults;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResultsRepository extends JpaRepository<Results, Integer> {

	@Query(value = "select * from results where total_marks>70 and total_marks<90", nativeQuery = true)
	public List<Results> getResultsRange();
	
	@Query(value = "SELECT * FROM results order by percentage desc limit 3", nativeQuery = true)
	public List<Results> getResultsTopThree();

}
