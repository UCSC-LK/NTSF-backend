package com.cops.ntsf.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class DBBackup implements ServletContextListener {

    private Thread databaseThread = null;
    private ServletContext context;
    private boolean backupStatus = true;
    final private LocalTime fixedAfterTwelveAm = LocalTime.of(0, 0, 0);
    final private LocalTime fixedBeforeSixAm = LocalTime.of(6, 0, 0);

    final private String ip = "localhost";
    final private String port = "3306";
    final private String database = "ntsfdatabase";
    final private String user = "root";
    final private String password = "";

    public void contextInitialized(ServletContextEvent contextEvent) {
        databaseThread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        LocalTime nowTime = LocalTime.now();
                        if (fixedAfterTwelveAm.getHour() <= nowTime.getHour() && fixedBeforeSixAm.getHour() > nowTime.getHour()) {
                            if (backupStatus) {
                                backupStatus = false;
                                LocalDate localDate = LocalDate.now();
                                String path = "D:\\project\\NTSF-backend\\DbBackup\\" + localDate.getYear() + localDate.getMonth() + localDate.getDayOfMonth() + ".sql";

                                String dumbCommand = "mysqldump " + database + " -h " + ip + " -u " + user + " -p" + password;
                                Runtime runtime = Runtime.getRuntime();
                                File databaseFile = new File(path);
                                PrintStream printStream;

                                try {
                                    Process process = runtime.exec(dumbCommand);
                                    printStream = new PrintStream(databaseFile);
                                    InputStream inputStream = process.getInputStream();
                                    int nextByteOfData;
                                    while ((nextByteOfData = inputStream.read()) != -1) {
                                        printStream.write(nextByteOfData);
                                    }
                                    inputStream.close();
                                    printStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            if (!backupStatus) {
                                backupStatus = true;
                            }
                        }
                        Thread.sleep(1000 * 60 * 60 * 2);
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
