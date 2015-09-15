package nl.group37.bejeweled.main.model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
  public static final String LOG_FILE = "log.txt";
  private static FileWriter write = null;
  private static PrintWriter writer = null;
  private static final boolean consoleLog = true;
  private static final String d = "dd-M-yy HH:mm:ss.S";

  /**
   * Write content to log file.
   * @param message to add.
   */
  public static void log(String message) {

    try {
      write = new FileWriter(LOG_FILE,true);
      writer = new PrintWriter(write);
    } catch (Exception ex) {
      System.out.println("Can't open log file");
    }

    if (message != null) {
      DateFormat dateFormat = new SimpleDateFormat(d);
      Date date = new Date();
      message = dateFormat.format(date) + " - " + message;
      if (consoleLog) {
        System.out.println(message);
      }
      writer.println(message);
      writer.close();
    }
  }
}