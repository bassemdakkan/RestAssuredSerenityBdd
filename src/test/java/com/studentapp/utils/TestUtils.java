package com.studentapp.utils;

import com.studentapp.testbase.TestBase;

import java.util.Random;

public class TestUtils extends TestBase {

    public static String getRandomInt() {
        Random random = new Random();
        int randomInt = random.nextInt(1000000);
        return Integer.toString(randomInt);
    }
}
