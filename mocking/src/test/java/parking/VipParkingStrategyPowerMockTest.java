package parking;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class, Calendar.class})
public class VipParkingStrategyPowerMockTest {

  @Test
  public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice()
      throws Exception {

    /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
     * by using PowerMock to mock static method */
    //given
    Calendar endOfMarch = Calendar.getInstance();
    endOfMarch.set(2020, Calendar.AUGUST, 30);
    PowerMockito.mockStatic(Calendar.class);
    Mockito.when(Calendar.getInstance()).thenReturn(endOfMarch);

    //when
    VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
    int result = vipParkingStrategy.calculateHourlyPrice();

    assertEquals(50, result);
  }

  @Test
  public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice() {

    /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
     * by using PowerMock to mock static method */

  }
}
