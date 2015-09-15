package main.java.group37.bejeweled.model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
  private static final String LOG_FILE = "log.txt";
  private static FileWriter write = null;
  private static PrintWriter writer = null;
  public static boolean consoleLog = true;
  private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss.SSS");

  /**
   * Initialize logger.
   */
  public static void init() {
    try {
      if (write == null) {
        write = new FileWriter(LOG_FILE,true);
        writer = new PrintWriter(write);
      }
    } catch (Exception ex) {
      System.out.println("Can't open log file");
      return;
    }
  }

  /**
   * Write content to log file.
   * @param message to log.
   */
  public static void log(String message) {
    if (message != null) {
      Date date = new Date();
      message = dateFormat.format(date) + " - " + message;
      if (consoleLog) {
        System.out.println(message);
      }
      writer.println(message);
      writer.flush();
    }
  }
  
  /**
   * Clean up resources PrintWriter.
   */
  public static void close() {
    writer.close();
  }
}