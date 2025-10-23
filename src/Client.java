/*
Alla sparas i en lista. Därifrån läser programmet och sorterar till behörig/obehörig, så att det är uppdaterat efter
aktuellt datum
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

    private int[] firstPaymentDate;
    private int[] lastPaymentDate;
    private MembershipType membershipType;

    List<Client> clients = new ArrayList<>();
    List<AuthorizedClient> authorizedClients = new ArrayList<>(); //Måste de vara private eller private final tom?
    List<UnauthorizedClient> unauthorizedClients = new ArrayList<>();

    public Client() {}

    public Client(String name, String adress, String email, String personalID, int[] firstPaymentDate, int[] lastPaymentDate, MembershipType membershipType) {
        super(name, adress, email, personalID);
        this.firstPaymentDate = firstPaymentDate;
        this.lastPaymentDate = lastPaymentDate;
        this.membershipType = membershipType;
    }

    public int[] getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(int[] firstPaymentDate) {
        this.firstPaymentDate = new int[3];
        for (int i = 0; i < 3; i++) {
            this.firstPaymentDate[i] = firstPaymentDate[i];
        }
    }

    public String getFirstPaymentDateToString() {
        for (int i = 0; i < 3; i++) {
            this.firstPaymentDate[i] = firstPaymentDate[i];
        }
        String s = String.format("%04d-%02d-%02d", firstPaymentDate[0], firstPaymentDate[1], firstPaymentDate[2]);
        return s;
    }

    public int[] getLastPaymentDate() {
        return lastPaymentDate;
    }

    public String getLastPaymentDateToString() {
        for (int i = 0; i < 3; i++) {
            this.lastPaymentDate[i] = lastPaymentDate[i];
        }
        String s = String.format("%04d-%02d-%02d", lastPaymentDate[0], lastPaymentDate[1], lastPaymentDate[2]);
            return s;
    }

    public void setLastPaymentDate(int[] lastPaymentDate) {
        this.lastPaymentDate = new int[3];
        for (int i = 0; i < 3; i++) {
            this.lastPaymentDate[i] = lastPaymentDate[i];
        }
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public String updateDateToString() {

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();

        String s = String.format("%04d-%02d-%02d", year, month, dayOfMonth);
        return s;
    }

    public void newPayment() { //Använts inte
        LocalDate now = LocalDate.now();

        if (getFirstPaymentDate() == null) {
            setFirstPaymentDate(new int[]{now.getYear(), now.getMonthValue(), now.getDayOfMonth()});
        }
        setLastPaymentDate(new int[]{now.getYear(), now.getMonthValue(), now.getDayOfMonth()});
    }
}