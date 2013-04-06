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

import java.util.ArrayList;

public class EmailTask implements Runnable {

    private EmailTransfer mailman;
    private ArrayList<String> toEmail;
    private String emailSubject;
    private String emailContent;

    public EmailTask(EmailTransfer mailman, ArrayList<String> toEmail, String emailSubject, String emailContent) {
        this.mailman = mailman;
        this.toEmail = toEmail;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
    }

    @Override
    public void run() {
        mailman.send(toEmail, emailSubject, emailContent);
    }

}
