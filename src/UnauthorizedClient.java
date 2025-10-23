public class UnauthorizedClient extends Client {

    public final boolean isAuthorized = false;

    public UnauthorizedClient(){}

    public UnauthorizedClient(Client client) {
        super(client.getName(), client.getAdress(), client.getEmail(), client.getPersonalID(), client.getFirstPaymentDate(),
                client.getLastPaymentDate(), client.getMembershipType());
    }

    @Override
    public String toString() {
        String s = String.format(getName() + " är registrerad medlem.\nMedlemskapet har gått ut. \n" + "Senaste betalning: "
                + "%04d-%02d-%02d",getLastPaymentDate()[0], getLastPaymentDate()[1], getLastPaymentDate()[2]);
        return s;
    }
}