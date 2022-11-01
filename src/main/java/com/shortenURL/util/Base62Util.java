package com.shortenURL.util;

import org.springframework.stereotype.Component;

@Component
public class Base62Util implements ParseUtil {

    static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    @Override
    public String encoding(int value) {
        final StringBuilder sb = new StringBuilder();
        do {
            int i = value % 62;
            sb.append(BASE62[i]);
            value /= 62;
        } while (value > 0);
        return sb.toString();
    }

}