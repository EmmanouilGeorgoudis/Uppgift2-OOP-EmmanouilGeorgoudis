import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTypeTest {

    Client c = new Client();


    @Test
    void getMembershipTypeTest() {
        c.setMembershipType(MembershipType.GULD);
        String expected = "Guld";
        assertEquals(expected, c.getMembershipType().toString());

        c.setMembershipType(MembershipType.PLATINA);
        String expected2 = "Platina";
        assertEquals(expected2, c.getMembershipType().toString());

        c.setMembershipType(MembershipType.STANDARD);
        String expected3 = "Standard";
        assertEquals(expected3, c.getMembershipType().toString());
    }

}