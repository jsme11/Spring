package com.ktds.cocomo;

public class Student {

	public void study() {
		
		int mathmaticScore = 90;
		int scienceScore = 80;
		int programmingScore = 100;
		
		printSumScore(mathmaticScore, scienceScore, programmingScore);
		printAverageScore(mathmaticScore, scienceScore, programmingScore);
	}
	
	private void printSumScore(int ... scores) {
		int sumScore = computeSumScore(scores);
		System.out.println("합계 : " + sumScore);
	}

	private void printAverageScore(int ... scores) {
		int sumScore = computeSumScore(scores);
		double average = computeAverageScore(sumScore, scores); 
		System.out.println("평균 : " + average);
	}
	
	private int computeSumScore(int... scores) {
		int sumScore = 0;
		for (int i = 0; i < scores.length; i++) {
			sumScore += scores[i];
		}
		return sumScore;
	}
	
	private double computeAverageScore(int sumScore, int... scores) {
		double average = sumScore / (double)scores.length;
		return average;
	}
}
