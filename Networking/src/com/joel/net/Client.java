package com.joel.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 
 * @author joel
 * @date 27-Apr-2018
 * Client class. Should be run after the server is started.
 * 
 */
public class Client {

	public static void main(String[] args) {
		
		Socket socket = null;
		BufferedReader br = null;
		PrintStream ps = null;

		try {

			//1. Create a socket connection to a server (localhost:6123)
			socket = new Socket("localhost", 6123);

			//2. Read input from the user
			Scanner sc = new Scanner(System.in);
			System.out.println("Client- Please enter a number: ");
			int num = sc.nextInt();

			//Scanner resSc = new Scanner(socket.getInputStream()); //For reading the output from the server

			//3. Send the data to the server
			System.out.println("Client- Sending value " + num + " to server");
			ps = new PrintStream(socket.getOutputStream());
			ps.println(num);

			//4. Read the result from the server
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String resultLine = null;
			while ((resultLine = br.readLine()) != null) {
				sb.append(resultLine);
			}

			System.out.println(sb.toString());
			int output = Integer.parseInt(sb.toString());
			
			//int output = resSc.nextInt(); //This can also be used instead of reading from the BufferedReader

			System.out.println("Client- Result from Server: " + output);

		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != ps) {
				ps.close();
			}
			if (null != socket) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}//end try-catch

	}//end main method

}
