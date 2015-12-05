package ru.hack2016.microbot.raspberry;

import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author mgorelikov
 * @since 05/12/15
 */
public class RaspberryLCDController implements LCDController {

  public final static int LCD_ROWS = 2;
  public final static int LCD_ROW_1 = 0;
  public final static int LCD_ROW_2 = 1;
  public final static int LCD_COLUMNS = 16;
  public final static int LCD_BITS = 4;

  private final GpioLcdDisplay lcd;

  public RaspberryLCDController() {

    final GpioController gpio = GpioFactory.getInstance();

    // initialize LCD
    lcd = new GpioLcdDisplay(LCD_ROWS,          // number of row supported by LCD
        LCD_COLUMNS,       // number of columns supported by LCD
        RaspiPin.GPIO_11,  // LCD RS pin
        RaspiPin.GPIO_10,  // LCD strobe pin
        RaspiPin.GPIO_03,  // LCD data bit 1
        RaspiPin.GPIO_02,  // LCD data bit 2
        RaspiPin.GPIO_01,  // LCD data bit 3
        RaspiPin.GPIO_00); // LCD data bit 4
    lcd.clear();
  }

  @Override
  public void writeText(int line, String text) {
    lcd.write(line, text, LCDTextAlignment.ALIGN_LEFT);
  }
}
