package api.favqs.helpers.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

/**
 * Store helper logic relates test data generation
 */
public class RandomGenerator {
    private static Random random;

    static {
        random = new Random();
    }

    public static String generateRandomEmail() {
        return "email" + genNumericString(10) + "@test.com";
    }

    public static String generateRandomName(String name) {
        return name + genNumericString(6);
    }

    public static String genRandomPassword() {
        String password = generateAlphabeticalString(1).toUpperCase() + generateAlphabeticalString(3).toLowerCase() + genNumericString(4) + "!";

        return password;
    }

    private static String generateAlphabeticalString(int number) {
        return RandomStringUtils.randomAlphabetic(number);
    }

    private static String genNumericString(int number) {
        return RandomStringUtils.randomNumeric(number);
    }

}
