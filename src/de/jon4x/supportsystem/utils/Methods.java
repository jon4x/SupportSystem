package de.jon4x.supportsystem.utils;

import java.util.Map;

public class Methods {

    public static Object getKeyFromValue(Map hm, Object value) {
        for (final Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
