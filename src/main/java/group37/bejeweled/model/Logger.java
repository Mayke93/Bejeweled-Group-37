package main.java.group37.bejeweled.model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
  private static final String LOG_FILE = "log.txt";
  private static FileWriter fileWriter = null;
  private static PrintWriter writer = null;
  public static boolean consoleLog = true;
  private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss.SSS");

  private static Logger logger = new Logger();
  
  private Logger() {
  }
  
  public static Logger getInstance() {
    return logger;
  }

  /**
   * Initialize logger.
   */
  public static void init() {
    try {
      if (fileWriter == null) {
        fileWriter = new FileWriter(LOG_FILE,true);
        writer = new PrintWriter(fileWriter);
      }
    } catch (Exception ex) {
      System.out.println("Can't open log file");
      return;
    }
  }
  
  /**
   * Write warning to log file.
   * @param error String with error message.
   */
  public static synchronized void error(String error) {
    logger.writeToLog("ERROR: " + error);
  }
  
  /**
   * Write content to log file.
   * @param message to log.
   */
  private void writeToLog(String message) {
    if (message != null && fileWriter != null && writer != null) {
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
   * Write content to log file.
   * @param message to log.
   */
  public static synchronized void log(String message) {
    logger.writeToLog(message);
  }
  
  /**
   * Clean up resources PrintWriter.
   */
  public static synchronized void close() {
    writer.close();
  }
}