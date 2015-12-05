package ru.hack2016.microbot.raspberry;

/**
 * @author mgorelikov
 * @since 05/12/15
 */
public interface LCDController {
  void writeText(int line, String text);
}
