package com.cops.ntsf.service.SystemAvailableStatus;

import com.cops.ntsf.service.Email;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SignalIGPEmail implements ServletContextListener {
    private Thread databaseThread = null;
    private ServletContext context;
    final private String emailAddress = "igp.srilankapolice@gmail.com";
    final private String subject = "System Status";
    final private String message = "NTSF\n\nHourly system status";

    public void contextInitialized(ServletContextEvent contextEvent) {
        databaseThread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Email email = new Email();
                        email.sendMail(emailAddress, subject, message);
                        Thread.sleep(1000 * 60 * 60);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };
        databaseThread.start();
        context = contextEvent.getServletContext();
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
        databaseThread.interrupt();
    }
}
