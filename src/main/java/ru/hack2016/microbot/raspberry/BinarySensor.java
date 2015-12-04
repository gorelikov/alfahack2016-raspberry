package ru.hack2016.microbot.raspberry;

import java.io.IOException;

/**
 * @author mgorelikov
 * @since 04/12/15
 */
public interface BinarySensor {
  /**
   * Just returns state of sensor.
   * @return state of true or false
   * @throws IOException
   */
  boolean getState() throws IOException;
}
