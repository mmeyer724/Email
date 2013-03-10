/*
    This file is part of Email.

    Email is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Email is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Email.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.mike724.email;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EmailTransfer {
	
	private Email plugin;
	private String host;
	private int port;
	private String user;
	private String password;
	
	public EmailTransfer(Email plugin, String host, int port, String user, String password) {
		this.plugin = plugin;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public void sendEmail(String to, String subject, String content) {
		String encodedUser = Base64Coder.encode(this.user.getBytes());
		String encodedPassword = Base64Coder.encode(this.password.getBytes());
		
		try {
			Socket socket = new Socket(this.host, this.port);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			dos.writeBytes("HELO\r\n");
			dos.writeBytes("AUTH LOGIN");
			dos.writeBytes("\r\n");
			dos.writeBytes(encodedUser);
			dos.writeBytes("\r\n");
			dos.writeBytes(encodedPassword);
			dos.writeBytes("\r\n");
			dos.writeBytes("MAIL FROM:<" + this.user + ">\r\n");
			dos.writeBytes("\r\n");
			dos.writeBytes("RCPT TO: <" + to + ">\r\n");
			dos.writeBytes("DATA\r\n");
			dos.writeBytes("Subject: " + subject + "\r\n");
			dos.writeBytes(content);
			dos.writeBytes("\r\n.\r\n");
			dos.writeBytes("QUIT\r\n");

			dos.flush();

			String responseline;
			while ((responseline = is.readLine()) != null) {
				plugin.getLogger().info(responseline);
			}

			is.close();
			dos.close();
			socket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
