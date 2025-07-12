package com.fiap.upa.core.entity;

import java.util.regex.Pattern;

public class CpfDocument extends Document {

    public static boolean isValidCPF(String cpf) {
        // Regex for CPF format: 000.000.000-00
        String formattedRegex = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        String regex = "^\\d{11}$";
        var formattedPattern = Pattern.compile(formattedRegex);
        var formattedMatcher = formattedPattern.matcher(cpf);
        var numberPattern = Pattern.compile(regex);
        var numberMatcher = numberPattern.matcher(cpf);
        return formattedMatcher.matches() || numberMatcher.matches();
    }

    public CpfDocument(String value) {
            super();
        if(isValidCPF(value))
            this.setValue(value);
        else
            throw new IllegalStateException("Invalid CPF");
    }
}
