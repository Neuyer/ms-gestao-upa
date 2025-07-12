package com.fiap.upa.core.entity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoctorSchedule {
    private List<WorkShift> workShifts;

    private DoctorSchedule(List<WorkShift> workShifts) {
        validateSameDayWorkShift(workShifts);
        this.workShifts = workShifts;
    }

    private DoctorSchedule() {
        this.workShifts = Collections.emptyList();
    }


    public List<WorkShift> getWorkShifts() {
        return workShifts; // Already unmodifiable
    }

    static public DoctorSchedule createDoctorSchedule(List<WorkShift> workShifts) {
        return new DoctorSchedule(workShifts);
    }

    static public DoctorSchedule createDoctorEmptySchedule() {
        return new DoctorSchedule();
    }
    public boolean isAvailableAt(LocalDateTime dateTime) {
        DayOfWeek requestedDay = dateTime.getDayOfWeek();
        return workShifts.stream()
                .filter(ws -> ws.getDayOfWeek().equals(requestedDay))
                .anyMatch(ws -> ws.contains(dateTime));
    }

    private void validateSameDayWorkShift(List<WorkShift> workShifts) {
        Set<DayOfWeek> seenDays = new HashSet<>();
        for (WorkShift workShift : workShifts) {
            if (!seenDays.add(workShift.getStart().getDayOfWeek())) {
                var message = String.format("There is already a work shift for this day: %s", workShift.getStart());
                throw new IllegalArgumentException(message);
            }
        }
    }
}
