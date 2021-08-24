package runner;

import java.io.IOException;

import controller.Controller;

public class Run {
	public static void main(String[] args) {
		try {
			new Controller();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
