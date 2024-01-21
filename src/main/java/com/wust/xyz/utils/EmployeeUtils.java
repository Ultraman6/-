package com.wust.xyz.utils;

import java.util.Random;

public class EmployeeUtils {
    // Sample arrays of Chinese first and last names
    private static final String[] FIRST_NAMES = {"李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴"};
    private static final String[] LAST_NAMES = {"伟", "芳", "娜", "强", "磊", "军", "洋", "勇", "艳", "杰", "静", "敏", "燕", "良", "明", "健", "飞"};
    private static final String[] GENDERS = {"男", "女"};
    private static final int MIN_AGE = 18; // Minimum age
    private static final int MAX_AGE = 65; // Maximum age
    private static final Random RANDOM = new Random();
    public static String getRandomName() {
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }
    public static int getRandomAge() {
        return MIN_AGE + RANDOM.nextInt(MAX_AGE - MIN_AGE + 1);
    }
    public static String getRandomGender() {
        return GENDERS[RANDOM.nextInt(GENDERS.length)];
    }
}
