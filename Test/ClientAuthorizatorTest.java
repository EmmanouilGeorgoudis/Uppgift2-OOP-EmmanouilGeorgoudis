import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientAuthorizatorTest {

    Client client = new Client();
    ClientAuthorizator clientAuthorizator = new ClientAuthorizator();

    @Test
    public void isAuthorizedTest(){
        LocalDate now = LocalDate.now();

        //två år sedan sista inbetalning
        client.setLastPaymentDate(new int[]{now.getYear() - 2, now.getMonthValue(), now.getDayOfMonth()});
        assertTrue(!clientAuthorizator.isAuthorized(client.getLastPaymentDate()));

        //exakt för ett år sedan (programmets körning)
        client.setLastPaymentDate(new int[]{now.getYear() - 1, now.getMonthValue(), now.getDayOfMonth()});
        assertTrue(clientAuthorizator.isAuthorized(client.getLastPaymentDate()));

        //samma år
        client.setLastPaymentDate(new int[]{now.getYear(), now.getMonthValue()-2, now.getDayOfMonth()-3});
        assertTrue(clientAuthorizator.isAuthorized(client.getLastPaymentDate()));
    }

    //Skapa ett med en given Client och anropa ClientAuthorizator för att se om man får ut det från motsvarande klass.
    @Test
    public void ClientAuthorizatorTest() {
        int[] fisrtPay = new int[]{2004, 04, 15};
        int[] lastPay = new int[]{2025, 05, 11};
        client = new Client("Name", "Adress", "email", "personalID", fisrtPay, lastPay, MembershipType.PLATINA);

        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clientAuthorizator = new ClientAuthorizator(clients);

        //Har en AuthorizedClient lagts till i listan?
        int expected = clientAuthorizator.authorizedClients.size();
        assertEquals(1, expected);

        int[] lastPay1 = new int[]{2025, 05, 11};
        client.setLastPaymentDate(lastPay1);

        //Har en UnauthorizedClient lagts till i listan?
        int expected1 = clientAuthorizator.unauthorizedClients.size();
        assertEquals(1, expected);
    }
}