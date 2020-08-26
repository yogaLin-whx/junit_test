package parking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {
	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
      //given
      ParkingLot parkingLot = mock(ParkingLot.class);
      Car car = mock(Car.class);
      when(parkingLot.getName()).thenReturn("parkingLot");
      when(car.getName()).thenReturn("car");

      //when
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot,car);

      //then
      assertEquals("parkingLot",receipt.getParkingLotName());
      assertEquals("car",receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
      //given
      Car car = mock(Car.class);
      when(car.getName()).thenReturn("car");

      //when
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

      //then
      assertEquals("No Parking Lot",receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
      //given
      ParkingLot parkingLot = spy(new ParkingLot("parkingLot",0));
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);

      //when
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      inOrderParkingStrategy.park(parkingLots,new Car("car"));

      //then
      verify(parkingLot,times(1)).isFull();
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
      //given
      ParkingLot parkingLot = spy(new ParkingLot("parkingLot",1));
      InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);

      //when
      inOrderParkingStrategy.park(parkingLots,new Car("car"));

      //then
      verify(inOrderParkingStrategy,times(1)).createReceipt(any(),any());
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
      //given
      ParkingLot parkingLot = spy(new ParkingLot("parkingLot",0));
      InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);

      //when
      inOrderParkingStrategy.park(parkingLots,new Car("car"));

      //then
      verify(inOrderParkingStrategy,times(0)).createReceipt(any(),any());

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
