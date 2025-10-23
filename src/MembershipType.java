//Exempel på AI användning som assistent. Beskrivit projektet och frågat om String eller enum passar bäst här
public enum MembershipType {
    GULD("Guld"),
    PLATINA("Platina"),
    STANDARD("Standard");

    private final String membershipType;

    MembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipType(){ //Fråga, returnerar String men i testet blev fel fast jag fick ut rätt text, AI säger att det är enum fortfarande. Testet blev grönt när jag skapade overriden
        return membershipType;
    }

    @Override
    public String toString(){
        return membershipType;
    }
}