import org.example.AUD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AUDTest {
    @Test
    void oneAUDShouldBeOneAUD() {
        AUD oneAUD =  new AUD(1);
        AUD anotherOneAUD = new AUD(1);

        assertEquals(oneAUD, anotherOneAUD);
    }

    @Test
    public void oneAUDShouldNotBeEqualToTenAUD() {
        AUD oneAUD = new AUD(1);
        AUD tenAUD = new AUD(10);
        assertNotEquals(oneAUD, tenAUD);
    }

    @Test
    public void oneEuroShouldNotBeEqualToNull() {
        AUD oneAUD = new AUD(1);
        assertNotEquals(null, oneAUD);
    }

    @Test
    public void euroShouldNotBeEqualToAnotherType() {
        AUD oneAUD = new AUD(1);
        assertNotEquals(oneAUD, new Object());
    }

    @Test
    public void fiveAUDAddedToFiveAUDShouldBeEqualToTenAUD() {
        assertEquals(new AUD(10), new AUD(5).add(new AUD(5)));
    }

    @Test
    public void fiveAUDSubtractedWithThreeAUDShouldBeEqualToTwoAUD() {
        assertEquals(new AUD(2), new AUD(5).Subtract(new AUD(3)));
    }

    @Test
    public void threeAUDSubtractedWithFiveAUDShouldBeEqualToNegativeTwoAUD() {
        assertEquals(new AUD(-2), new AUD(3).Subtract(new AUD(5)));
    }
}
