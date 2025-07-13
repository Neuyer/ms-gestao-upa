package com.fiap.upa.core.entity;

import java.util.*;

public class UPA {

    private final UUID id;
    private String nickName;
    private Address address;
    private final List<Doctor> doctors;
    private final List<Attendant> attendants;

    private UPA(UUID id, String nickName, Address address) {
        this.id = Objects.requireNonNull(id, "UBS ID cannot be null");
        this.nickName = Objects.requireNonNull(nickName, "UBS nickname cannot be null");
        this.address = Objects.requireNonNull(address, "UBS address cannot be null");
        this.doctors = new ArrayList<>();
        this.attendants = new ArrayList<>();
    }

    public static UPA createNewUPA(String nickName, Address address) {
        return new UPA(UUID.randomUUID(), nickName, address);
    }

    public static UPA loadUPA(UUID id, String nickName, Address address, List<Doctor> doctors, List<Attendant> attendants) {
        UPA upa = new UPA(id, nickName, address);
        if (doctors != null) {
            upa.doctors.addAll(doctors);
        }
        if (attendants != null) {
            upa.attendants.addAll(attendants);
        }
        return upa;
    }

    public UUID getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public Address getAddress() {
        return address;
    }

    public List<Attendant> getAttendants() {
        return attendants;
    }

    public List<Doctor> getDoctors() {
        return Collections.unmodifiableList(doctors);
    }

    public void updateNickName(String newNickName) {
        if (newNickName == null || newNickName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be null or empty.");
        }
        this.nickName = newNickName;
    }

    public void updateAddress(Address newAddress) {
        if (newAddress == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        this.address = newAddress;
    }

    public void addDoctor(Doctor doctor) {
        Objects.requireNonNull(doctor, "Doctor cannot be null.");
        if (doctors.contains(doctor)) {
            throw new IllegalArgumentException("Doctor already associated with this UBS.");
        }
        this.doctors.add(doctor);
    }

    public void addDAttendant(Attendant attendant) {
        Objects.requireNonNull(attendant, "Attendant cannot be null.");
        if (attendants.contains(attendant)) {
            throw new IllegalArgumentException("Attendant already associated with this UBS.");
        }
        this.attendants.add(attendant);
    }

    public void removeDoctor(Doctor doctor) {
        Objects.requireNonNull(doctor, "Doctor cannot be null.");
        if (!doctors.contains(doctor)) {
            throw new IllegalArgumentException("Doctor not found in this UBS: " + doctor);
        }
        this.doctors.remove(doctor);
    }

    public void removeAttendant(Attendant attendant) {
        Objects.requireNonNull(attendant, "Attendant cannot be null.");
        if (!attendants.contains(attendant)) {
            throw new IllegalArgumentException("Attendant not found in this UBS: " + attendant);
        }
        this.attendants.remove(attendant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UPA ubs = (UPA) o;
        return Objects.equals(id, ubs.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
