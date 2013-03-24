package com.mike724.email;

public class EmailAlter {

    public final String IN_BETWEEN_PAGES;
    public final String SUBJECT;
    public final String CONTENT;

    public EmailAlter(String between, String sub, String con) {
        this.IN_BETWEEN_PAGES = between;
        this.SUBJECT = sub;
        this.CONTENT = con;
    }

    public String replaceVariables(String orig, String name, String title, String content) {
        String fixed = orig.replace("$BOOK_CONTENT", content);
        fixed = fixed.replace("$BOOK_TITLE", title);
        fixed = fixed.replace("$PLAYER_NAME", name);
        fixed = fixed.replace("$NL", "\n");
        return fixed;
    }
}
