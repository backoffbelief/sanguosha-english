package com.sdsoft;

import java.util.Scanner;

import com.sdsoft.model.communication.GameClient;
import com.sdsoft.model.communication.GameServer;

public class TestDriver {
	public static void main(final String[] args) {
		final Scanner s = new Scanner(System.in);
		final boolean condition = true;
		GameServer server = null;
		int clientNumber = 0;
		while (condition) {
			final String text = s.next();
			System.out.println(text + " entered");
			if (text.equalsIgnoreCase("cs")) {
				server = new GameServer("testserver", 12345, 20, 8, true);
			} else if (text.equalsIgnoreCase("cc")) {
				new GameClient(12345, "192.168.0.104", "testclient" + clientNumber++);
			} else if (text.equalsIgnoreCase("start")) {
				if (server != null) {
					server.startGame();
				}
			} else if (text.equalsIgnoreCase("exit")) {
				break;
			}
		}
		// server.startGame();
		System.out.println("done");
	}
}
