package android.unitedremote.com.unitedremotemobilechallenge;


import android.unitedremote.com.unitedremotemobilechallenge.util.StarsConverter;

import org.junit.Test;


import static org.junit.Assert.*;

public class StarsConverterTest {

    @Test
    public void convertStarsBelow() {
        assertEquals(StarsConverter.convert(500), "500");
    }

    @Test
    public void convertStarsEqual() {
        assertEquals(StarsConverter.convert(1010), "1k");
    }

    @Test
    public void convertStarsAbove() {
        assertEquals(StarsConverter.convert(1900),"1,9k");
    }
}