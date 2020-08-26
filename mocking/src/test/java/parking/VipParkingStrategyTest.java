package parking;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    private CarDaoImpl carDao;

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
      //given
      ParkingLot parkingLot1 = new ParkingLot("parkingLot",0);
      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot1);
      doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());
      //when
      vipParkingStrategy.park(parkingLots,new Car("Acar"));

      //then
      verify(vipParkingStrategy,times(1)).createReceipt(any(),any());
  }


    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        ParkingLot parkingLot1 = new ParkingLot("parkingLot",0);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        //when
        vipParkingStrategy.park(parkingLots,new Car("car"));

        //then
        verify(vipParkingStrategy,times(0)).createReceipt(any(),any());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = new Car("Acar");
        when(carDao.isVip(any())).thenReturn(true);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertTrue(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = new Car("1");
        when(carDao.isVip(any())).thenReturn(true);
        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertFalse(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = new Car("ACar");
        when(carDao.isVip(any())).thenReturn(false);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertFalse(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = new Car("cc");
        when(carDao.isVip(any())).thenReturn(false);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertFalse(result);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
