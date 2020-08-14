package com.theonetech.android;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.theonetech.android.domain.utils.DateUtils;
import com.theonetech.android.domain.utils.Logger;
import com.theonetech.android.domain.utils.ValidationUtils;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.theonetech.android", appContext.getPackageName());
    }


    //********************** Test Case of ValidationUtils class ********************
    @Test
    public void emailValidator_IsBlank_Type_Test() {
        //Expected (java.lang.AssertionError)
        assertTrue(ValidationUtils.isBlank("test"));
    }

    @Test
    public void emailValidator_IsBlank_Type_Empty() {
        //it's valid because, Input is blank
        assertTrue(ValidationUtils.isBlank(""));
    }


    //isValidEmail

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        //it's valid because, Input is format is valid
        assertTrue(ValidationUtils.isValidEmail("kaneriya@gmail.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        //it's valid because, Input is format is valid
        assertTrue(ValidationUtils.isValidEmail("name@email.co.uk"));
    }



    @Test
    public void emailValidatore_IsMinLength_Equal_Size() {
        //Exception (java.lang.AssertionError)
        assertTrue(ValidationUtils.isMinLength("test@gmail.com", 14));
    }
    @Test
    public void emailValidatore_IsMinlengthSingleChar() {
        //Exception (java.lang.AssertionError)
        assertTrue(ValidationUtils.isMinLength("test", 4));
    }
    @Test
    public void emailValidatore_IsMinLength_Single_IsValide() {
        //it's valid length of input text
        assertTrue(ValidationUtils.isMinLength("one", 2));
    }



    @Test
    public void emailValidatore_DateFormateCheck_Valid_Format() {
        //yyyy-MM-dd'T'HH:ss it's valid format
        String date = DateUtils.parseDateToddMMYY("2020-10-01T09:45");
        assertTrue(date, true);
    }

    @Test
    public void emailValidatore_DateFormateCheck_Invalid_Format() {
        //Exception (java.text.ParseException: Unparseable date: "2020-10-01T09-45")
        String date = DateUtils.parseDateToddMMYY("2020-10-01T09-45");
        assertFalse(date, true);
    }

    @Test
    public void emailValidatore_DateFormateCheck_Invalid_Format_Year() {
        //Exception (java.text.ParseException: Unparseable date: "200-10-01T09-45")
        String date = DateUtils.parseDateToddMMYY("200-10-01T09-45");
        assertFalse(date, true);
    }




    @Test
    public void emailValidatore_DateFormateCheck_DayFromString() {
        //Exception (java.text.ParseException: Unparseable date: "200-10-01T09-45")
        String date = DateUtils.getDayFromString("200-10-01T09-45");
        assertFalse(date, true);
    }
    @Test
    public void emailValidatore_DateFormateCheck_DayFromString_Year() {
        //Exception (java.text.ParseException: Unparseable date: "200-10-01T09-45")
        String date = DateUtils.getDayFromString("200-10-01T09-45");
        assertFalse(date, true);
    }

    @Test
    public void emailValidatore_DateFormate_DayFromString_Format() {
        //yyyy-MM-dd'T'HH:ss it's valid format
        String date = DateUtils.getDayFromString("2020-10-01T09:45");
        Log.e("====>",""+date);
        assertTrue(date, true);
    }



    @Test
    public void Logger_Verbose(){
        Logger.v("Test Verbose"+"Log");
    }
    @Test
    public void Logger_Debug(){
        Logger.d("Test Debug"+"Log");
    }
    @Test
    public void Logger_Info(){
        Logger.i("Test Info"+"Log");
    }
    @Test
    public void Logger_Warning(){
        Logger.w("Test Warning"+"Log");
    }
    @Test
    public void Logger_Error(){
        Logger.e("Test Error"+"Log");
    }


    //********************** Common Test Case ********************
    @Test
    public void testAssertArrayEquals_Not() {
        //Expected :end of array (byte arrays not same: array lengths differed, expected.length=5 actual.length=9; arrays first differed at element [5])
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial test".getBytes();
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    @Test
    public void testAssertArrayEquals() {
        //it's valid because, byte is equal
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    @Test
    public void testAssertEquals() {
        assertEquals("failure - strings are not equal", "text", "text");
    }

    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    @Test
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertNotSame() {
        assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertNull() {
        assertNull("should be null", null);
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        assertSame("should be same", aNumber, aNumber);
    }


    // ---------------- Common JUnit Matchers assertThat -----------------
    @Test
    public void testAssertThatBothContainsString() {
        assertThat("albumen", both(containsString("a")).and(containsString("b")));
    }

    @Test
    public void testAssertThatHasItems() {
        assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
    }

    @Test
    public void testAssertThatEveryItemContainsString() {
        assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")));
    }

    // Core Hamcrest Matchers with assertThat
    @Test
    public void testAssertThatHamcrestCoreMatchers() {
        assertThat("good", allOf(equalTo("good"), startsWith("good")));
        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        assertThat(new Object(), not(sameInstance(new Object())));
    }

    @Test
    public void testAssertTrue() {
        assertTrue("failure - should be true", true);
    }


    // ---------------- Common Collection class JUnit assert test -----------------

    private List<String> generateStingList() {
        String[] sentence = {"android", "context", "service", "manifest", "layout", "resource", "broadcast", "receiver", "gradle"};
        return Arrays.asList(sentence);
    }

    @Test
    public void testWithAsserts() {
        List<String> list = generateStingList();
        assertTrue(list.contains("android"));
        assertTrue(list.contains("context"));
        assertTrue(list.size() > 4);
        assertTrue(list.size() < 13);
    }

    @Test
    public void testWithBigAssert() {
        List<String> list = generateStingList();
        assertTrue(list.contains("android") && list.contains("context") && list.size() > 3 && list.size() < 12);
    }

    @Test
    public void testFailureWithAsserts() {
        List<String> list = generateStingList();
        assertTrue(list.contains("android"));
        assertTrue(list.contains("service"));
        assertTrue(list.size() > 3);
        assertTrue(list.size() < 12);
    }
}
