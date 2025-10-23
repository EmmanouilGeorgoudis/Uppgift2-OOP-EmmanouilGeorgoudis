import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
class ClientTest {

    Client c = new Client();

    @Test
    public void setLastPaymentDateTest(){
        c.setLastPaymentDate(new int[]{2005, 05, 15});

        int[] expected = new int[]{2005, 05, 15};
        int[] unexpected = new int[]{2004, 15, 12};

        assertTrue(Arrays.equals(expected, c.getLastPaymentDate()));
        assertTrue(!Arrays.equals(unexpected, c.getLastPaymentDate()));
    }

    @Test
    public void newPaymentTest() {
        LocalDate now = LocalDate.now();
        int[] currentDateArray = new int[]{now.getYear(), now.getMonthValue(), now.getDayOfMonth()};

        //Sätter in och testar en äldre betalning
        c.setLastPaymentDate(new int[]{2005, 12,20});
        int[] expected = new int[]{2005, 12, 20};
        assertTrue(Arrays.equals(expected, c.getLastPaymentDate()));

        //Testar om första betalning är null
        assertTrue(Arrays.equals(null, c.getFirstPaymentDate()));

        //Börjar med test på anropning av newPayment på sista betalningen
        c.newPayment();
        assertTrue(Arrays.equals(currentDateArray, c.getLastPaymentDate()));

        //Testar newPayment på första betalning som var null (ny medlem)
        assertTrue(Arrays.equals(currentDateArray, c.getFirstPaymentDate()));

        //Testar newPayment på första betalning om den inte hade varit null (befintlig medlem)
        int[] expected2 = new int[]{2004, 14, 15};
        c.setFirstPaymentDate(new int[]{2004, 14,15});
        c.newPayment();
        assertTrue(!Arrays.equals(currentDateArray, c.getFirstPaymentDate()));
        assertTrue(Arrays.equals(expected2, c.getFirstPaymentDate()));
    }
}