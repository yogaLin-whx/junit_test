package parking;

import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class CarDaoImpl implements CarDao{
    @Override
    public boolean isVip(String carName) {
//        return (new Random()).nextBoolean();
       return StringUtils.contains(carName, "A");
    }
}
