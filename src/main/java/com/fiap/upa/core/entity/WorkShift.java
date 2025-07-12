package com.fiap.upa.core.entity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

public class WorkShift {

    private DayOfWeek dayOfWeek;

    private LocalDateTime start;

    private LocalDateTime end;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    private WorkShift(LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start, "Work shift start time cannot be null.");
        Objects.requireNonNull(end, "Work shift end time cannot be null.");
        if (start.isAfter(end)) {
            var message = String.format("Work shift start time cannot be after end time: %s", start);
            throw new IllegalArgumentException(message);
        }
        this.start = start;
        this.end = end;
        this.dayOfWeek = start.getDayOfWeek();
    }

    public boolean contains(LocalDateTime time) {
        return !time.isBefore(this.start) && time.isBefore(this.end);
    }

    static public WorkShift createWorkShift(LocalDateTime start, LocalDateTime end) {
        return new WorkShift(start, end);
    }
}
