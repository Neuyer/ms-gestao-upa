package com.fiap.upa.core.entity;

import java.time.LocalDateTime;

public class ServiceNumberGenerator {

    public static String generateServiceNumber(Long position) {
        var date = LocalDateTime.now();
        String paddedStrNumber = String.format("%04d", position);
        return String.format("M%sD%s-%s", date.getMonth(), date.getDayOfMonth(),paddedStrNumber);
    }
}
