package ru.innopolis.attestation03.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class HelperTest {
    MockedStatic<Helper> mockedHelper;
    @Before
    public void setup() {
        mockedHelper = Mockito.mockStatic(Helper.class);

        Map<String, String> testObject = new HashMap<>();
        testObject.put("1", "1");
        testObject.put("2", "2");
        testObject.put("3", "3");

        mockedHelper.when(() ->
                        Helper.getFullName("1", "2", "3"))
                .thenReturn("1 2 3");
        mockedHelper.when(() ->
                        Helper.asJsonString(testObject)).thenReturn("{ 1: 1, 2: 2, 3: 3}");
    }


    @Test
    public void getFullNameTest() {
        Map<String, String> testObject = new HashMap<>();
        testObject.put("1", "1");
        testObject.put("2", "2");
        testObject.put("3", "3");

        Assertions.assertNotNull(Helper.getFullName("1", "2", "3"));
        Assertions.assertEquals(Helper.getFullName("1", "2", "3"), "1 2 3");
        Assertions.assertNotNull(Helper.asJsonString(testObject));
        Assertions.assertEquals(Helper.asJsonString(testObject), "{ 1: 1, 2: 2, 3: 3}");
    }

    @After
    public void teardown() {
        mockedHelper.close();
    }
}
