/****************************************************************************
 *
 *   Copyright (C) Micro Focus 1984-2018. All rights reserved.
 *
 *   This sample code is supplied for demonstration purposes only
 *   on an "as is" basis and is for use at your own risk.
 *
 ****************************************************************************/

package com.microfocus.autoscale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Application used to simulate large amount of almost simultaneous calls towards the Loan Amort API
 * 
 * @author Micro Focus
 *
 */
public class MainClass {

	// CONFIGURATION
	// the lambda URL address
	private static final String lambdaUrl = "https://ifcfatn1wh.execute-api.us-east-1.amazonaws.com/loanamort";
	// the amount, time and rate arguments
	private static final String arguments = "?p=10000&t=240&r=2.3";
	// number of threads in a pool
	private static final int nOfThreads = 35;
	// n of total calls
	private static final int nOfCalls = 10000;
	
	
	public static void main(String[] args) {
		
		ExecutorService es = Executors.newFixedThreadPool(nOfThreads);
		for(int i = 0; i < nOfCalls; i++) {
			es.execute(new LoanAmortRunnable(lambdaUrl + arguments));
		}
		es.shutdown();
	}

}
