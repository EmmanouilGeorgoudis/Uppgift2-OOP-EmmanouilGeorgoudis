public abstract class Person {

/*
namn,
address, email, födelsenummer, datum då personen blev medlem, när medlemsavgiften senast
betalades och vilken typ av medlemsskap varje person har.
Fredrik Berggren;Skolgränd 8,16819 Norrköping;fredde@fakemail.se;
851020-6728;2019-12-30;2021-12-30;Platina
 */

    private String name;
    private String adress;
    private String email;
    private String personalID;

    public Person(){}

    public Person(String name, String adress, String email, String personalID) {
    this.name = name;
    this.adress = adress;
    this.email = email;
    this.personalID = personalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }
}
