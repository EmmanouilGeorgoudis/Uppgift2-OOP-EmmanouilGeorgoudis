import java.time.LocalDate;
import java.util.List;

public class ClientAuthorizator extends Client{

    public ClientAuthorizator(){}

    public ClientAuthorizator(List<Client> clients) {
        for (Client client : clients) {
            if (isAuthorized(client.getLastPaymentDate())) {
                authorizedClients.add(new AuthorizedClient(client));
            } else {
                unauthorizedClients.add(new UnauthorizedClient(client));
            }
        }
    }

    public List<AuthorizedClient> getAuthorizedClients() {
        return authorizedClients;
    }

    public List<UnauthorizedClient> getUnauthorizedClients() {
        return unauthorizedClients;
    }

    public boolean isAuthorized(int[] lastPaymentDate){
        boolean isAuthorized = true;

        int year = lastPaymentDate[0];
        int month = lastPaymentDate[1];
        int dayOfMonth = lastPaymentDate[2];
        LocalDate then = LocalDate.of(year, month, dayOfMonth);

        LocalDate now = LocalDate.now();
        if (now.getYear() - then.getYear() > 1) {
            isAuthorized = false;
        } else if (now.getYear() - then.getYear() == 1) {
            if (now.getMonthValue() < then.getMonthValue()) {
                isAuthorized = true;
            } else if (now.getMonthValue() == then.getMonthValue()) {
                if (now.getDayOfMonth() <= then.getDayOfMonth()) {
                    isAuthorized = true;
                }
            }
        } else {
            isAuthorized = true;
        }
        return isAuthorized;
    }
}
