/****************************************************************************
 *
 *   Copyright (C) Micro Focus 1984-2019. All rights reserved.
 *
 *   This sample code is supplied for demonstration purposes only
 *   on an "as is" basis and is for use at your own risk.
 *
 ****************************************************************************/

package com.microfocus.autoscale;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Runnable for simulating large amount of calls to Loan Amort API
 * 
 * @author Micro Focus
 *
 */
public class LoanAmortRunnable implements Runnable {

	private String urlAddress = null;

	/**
	 * {@link Constructor} for {@link LoanAmortRunnable}
	 * 
	 * @param urlAddress the URL address of the API
	 */
	public LoanAmortRunnable(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	@Override
	public void run() {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlAddress);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			switch (connection.getResponseCode()) {
			case HttpURLConnection.HTTP_OK:
				System.out.println(Thread.currentThread().getName() + " OK");
				break;
			default:
				System.out.println(Thread.currentThread().getName() + " FAILED");
				break;
			}
		} catch (IOException e) {
			// skip
		} finally {
			if (connection != null)
				connection.disconnect();
		}

	}

}
