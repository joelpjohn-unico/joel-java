package com.joel.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author joel
 * @date 27-Apr-2018
 * Server class. Should be started first.
 *
 */
public class Server {

	public static void main(String args[]) {

		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintStream ps = null;

		try {
			//1. Create a Server Socket
			serverSocket = new ServerSocket(6123);
	
			//2. Listen for requests on this port
			System.out.println("Server- waiting for request");
			socket = serverSocket.accept();
	
			//3. Read the data
			System.out.println("Server- request received: " + socket.toString());
			Scanner sc = new Scanner(socket.getInputStream());
			int inputVal = sc.nextInt();
	
			//4. Process the data
			int outputVal = inputVal * inputVal;
	
			//5. Write the result back to the client
			System.out.println("Server- Calculated Value: " + outputVal);
			ps = new PrintStream(socket.getOutputStream());
			ps.println(outputVal);
	
			System.out.println("Server- Process complete");
	

		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
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
			if (null != serverSocket) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}//end try-catch

	}//end main

}
