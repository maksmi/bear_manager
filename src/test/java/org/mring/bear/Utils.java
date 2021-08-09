package org.mring.bear;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utils {
    public static void compareBears(Bear actual, Bear expected) {
        assertAll("Bears should be equal",
                () -> assertEquals(Float.parseFloat(expected.getBear_age()), Float.parseFloat(actual.getBear_age()), "Bear AGE"),
                () -> assertEquals(expected.getBear_name(), actual.getBear_name(), "Bear NAME"),
                () -> assertEquals(expected.getBear_type(), actual.getBear_type(), "Bear TYPE")
        );
    }

    public static Bear createBear(String name, String age, String type) {
        Bear bear = new Bear();
        bear.setBear_age(age);
        bear.setBear_name(name);
        bear.setBear_type(type);
        return bear;
    }

    static List<Bear> dataBearsProviderPositive() {
        List<Bear> bearList = new ArrayList() {{
            add(Utils.createBear("Bear_1", "0.5", BearTypes.POLAR.toString()));
            add(Utils.createBear("Bear_2", "1", BearTypes.BROWN.toString()));
            add(Utils.createBear("Bear_3", "9.5", BearTypes.GUMMY.toString()));
            add(Utils.createBear("Bear_4", "10", BearTypes.BLACK.toString()));
        }};
        return bearList;
    }

    static List<Bear> dataBearsProviderNegative() {
        List<Bear> bearList = new ArrayList() {{
            add(Utils.createBear("Bear_3", null, BearTypes.BROWN.toString()));
            add(Utils.createBear("Bear_4", "-1", BearTypes.POLAR.toString()));
            add(Utils.createBear("Bear_5", "0.01", "green"));
            add(Utils.createBear(null, "0.01", null));
        }};
        return bearList;
    }
}
