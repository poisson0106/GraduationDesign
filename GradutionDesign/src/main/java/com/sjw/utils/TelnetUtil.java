package com.sjw.utils;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class TelnetUtil {
	private TelnetClient telnet = new TelnetClient();  
    private InputStream in;  
    private PrintStream out;  
    private char prompt;  
    public TelnetUtil(String server, String user, String password) {  
        try {  
            telnet.connect(server, 4555);  
            in = telnet.getInputStream();  
            out = new PrintStream(telnet.getOutputStream());  
            readUntil("Login id:");  
            write(user);  
            readUntil("Password:");  
            write(password);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    public void su(String password) {  
        try {  
            write("su");  
            readUntil("Password: ");  
            write(password);  
            prompt = '#';  
            readUntil(prompt + " ");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    public String readUntil(String pattern) {  
        try {  
            char lastChar = pattern.charAt(pattern.length() - 1);  
            StringBuffer sb = new StringBuffer();  
            boolean found = false;  
            char ch = (char) in.read();
            String temp=in.toString();
            while (true) {  
                System.out.print(ch);  
                sb.append(ch);  
                if (ch == lastChar) {  
                    if (sb.toString().endsWith(pattern)) {  
                        return sb.toString();  
                    }  
                }  
                ch = (char) in.read();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public void write(String value) {  
        try {  
            out.println(value);  
            out.flush();  
            System.out.println(value);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    public String sendCommand(String command) {  
        try {  
            write(command);  
            return "success";  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public void disconnect() {  
        try {  
            telnet.disconnect();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}
