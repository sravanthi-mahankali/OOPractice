package org.example.bank;

import org.example.bank.AUD;
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


    @Test
    void greaterThanOrEqual() {
        assertFalse(new AUD(5).greaterThanOrEqual(new AUD(10)));
        assertTrue(new AUD(10).greaterThanOrEqual(new AUD(5)));
        assertTrue(new AUD(10).greaterThanOrEqual(new AUD(10)));
    }

    @Test
    void greaterThan() {
        assertFalse(new AUD(5).greaterThan(new AUD(10)));
        assertTrue(new AUD(10).greaterThan(new AUD(5)));
        assertFalse(new AUD(10).greaterThan(new AUD(10)));
    }

    @Test
    void lessThan() {
        assertFalse(new AUD(50).lessThan(new AUD(10)));
        assertTrue(new AUD(10).lessThan(new AUD(50)));
        assertFalse(new AUD(10).lessThan(new AUD(10)));
    }
    @Test
    void toStringMethod() {
        assertEquals("10.0", new AUD(10).toString());
    }
}
