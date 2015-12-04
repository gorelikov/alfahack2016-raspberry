package ru.hack2016.microbot.raspberry;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author mgorelikov
 * @since 04/12/15
 */
public class RaspberryColorSensor implements BinarySensor {
  private final int border;
  private final int sensor_pin;
  private final I2CDevice device;
  private static final Logger log = LoggerFactory.getLogger(RaspberryColorSensor.class.getName());

  /**
   *
   * @param border lower border on which lights is on
   * @param sensor_pin sensor pin num on PCF8591(0-3)
   * @throws IOException
   */
  public RaspberryColorSensor(int border, int sensor_pin) throws IOException {
    this.border = border;
    this.sensor_pin = sensor_pin;
    I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
    device = bus.getDevice(0x48);
  }

  /**
   * Returns binary state of color sensor
   * @return true means light is on, otherwise returns false
   * @throws IOException
   */
  @Override
  public boolean getState() throws IOException {
    device.write((byte) (0x40 | (sensor_pin & 3)));
    device.read();
    int lightValue = (short) ((short) 0x00FF & (byte) device.read());
    log.debug("Light: " + lightValue);
    if(lightValue < border) {
      return true;
    } else {
      return false;
    }
  }
}
