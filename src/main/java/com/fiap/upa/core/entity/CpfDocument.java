package com.fiap.upa.core.entity;

public class CpfDocument extends Document {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        String cleanedCpf = cpf.replaceAll("[^0-9]", "");

        if (cleanedCpf.length() != 11) {
            return false;
        }

        if (cleanedCpf.matches("(\\d)\\1{10}")) { // e.g., "00000000000", "11111111111"
            return false;
        }

        int sum = 0;
        int remainder;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cleanedCpf.charAt(i)) * (10 - i);
        }
        remainder = 11 - (sum % 11);
        int firstVerifierDigit = (remainder == 10 || remainder == 11) ? 0 : remainder;

        if (firstVerifierDigit != Character.getNumericValue(cleanedCpf.charAt(9))) {
            return false;
        }

        sum = 0; // Reset sum
        for (int i = 0; i < 10; i++) { // Now iterate through the first 10 digits (including the first verifier)
            sum += Character.getNumericValue(cleanedCpf.charAt(i)) * (11 - i);
        }
        remainder = 11 - (sum % 11);
        int secondVerifierDigit = (remainder == 10 || remainder == 11) ? 0 : remainder;

        if (secondVerifierDigit != Character.getNumericValue(cleanedCpf.charAt(10))) {
            return false;
        }

        return true;
    }

    public CpfDocument(String value) {
        super();
        if (isValidCPF(value))
            this.setValue(value);
        else
            throw new IllegalStateException("Invalid CPF");
    }
}
