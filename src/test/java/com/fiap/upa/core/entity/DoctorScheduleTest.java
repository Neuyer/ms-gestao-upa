package com.fiap.upa.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.fiap.upa.core.entity.DoctorSchedule.createDoctorSchedule;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DoctorSchedule Tests")
public class DoctorScheduleTest {

    // Helper method to create a WorkShift for testing (using dummy date for DayOfWeek)
    private WorkShift createWorkShift(DayOfWeek day, int startHour, int endHour) {
        // Use a fixed date to derive the DayOfWeek for testing purposes
        LocalDateTime startDate = LocalDateTime.of(2025, 7, (7 + day.getValue() - 1), startHour, 0); // July 7, 2025 is a Monday
        LocalDateTime endDate = LocalDateTime.of(2025, 7, (7 + day.getValue() - 1), endHour, 0);
        return WorkShift.createWorkShift(startDate, endDate);
    }

    @Nested
    @DisplayName("Constructor and Basic Operations")
    class ConstructorTests {

        @Test
        @DisplayName("Should create DoctorSchedule with empty list when no arguments")
        void shouldCreateEmptyScheduleWithNoArgs() {
            DoctorSchedule schedule = DoctorSchedule.createDoctorEmptySchedule();
            assertNotNull(schedule.getWorkShifts());
            assertTrue(schedule.getWorkShifts().isEmpty());
        }

        @Test
        @DisplayName("Should create DoctorSchedule with provided work shifts")
        void shouldCreateScheduleWithProvidedShifts() {
            WorkShift shift1 = createWorkShift(DayOfWeek.MONDAY, 9, 17);
            List<WorkShift> shifts = Collections.singletonList(shift1);

            DoctorSchedule schedule = createDoctorSchedule(shifts);
            assertNotNull(schedule.getWorkShifts());
            assertFalse(schedule.getWorkShifts().isEmpty());
            assertEquals(1, schedule.getWorkShifts().size());
            assertTrue(schedule.getWorkShifts().contains(shift1));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException if work shifts list contains duplicate days")
        void shouldThrowExceptionForDuplicateDays() {
            WorkShift shift1 = createWorkShift(DayOfWeek.MONDAY, 9, 17);
            WorkShift shift2 = createWorkShift(DayOfWeek.MONDAY, 8, 12); // Duplicate day
            List<WorkShift> shifts = Arrays.asList(shift1, shift2);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                createDoctorSchedule(shifts);
            });

            assertEquals("There is already a work shift for this day: 2025-07-07T08:00", exception.getMessage());
        }

        @Test
        @DisplayName("Should allow different days of the week")
        void shouldAllowDifferentDays() {
            WorkShift shiftMonday = createWorkShift(DayOfWeek.MONDAY, 9, 17);
            WorkShift shiftTuesday = createWorkShift(DayOfWeek.TUESDAY, 8, 12);
            List<WorkShift> shifts = Arrays.asList(shiftMonday, shiftTuesday);

            DoctorSchedule schedule = createDoctorSchedule(shifts);
            assertNotNull(schedule.getWorkShifts());
            assertEquals(2, schedule.getWorkShifts().size());
            assertTrue(schedule.getWorkShifts().contains(shiftMonday));
            assertTrue(schedule.getWorkShifts().contains(shiftTuesday));
        }

        @Test
        @DisplayName("Should throw NullPointerException if initial workShifts list is null")
        void shouldThrowNPEForNullWorkShiftsList() {
            assertThrows(NullPointerException.class, () -> {
                createDoctorSchedule(null);
            }, "Work shifts list cannot be null.");
        }
    }

    @Nested
    @DisplayName("Availability Checks")
    class AvailabilityTests {

        private DoctorSchedule doctorSchedule;
        private WorkShift mondayShift;
        private WorkShift wednesdayShift;

        @BeforeEach
        void setUp() {
            mondayShift = createWorkShift(DayOfWeek.MONDAY, 9, 17); // 9:00 to 17:00
            wednesdayShift = createWorkShift(DayOfWeek.WEDNESDAY, 10, 14); // 10:00 to 14:00
            doctorSchedule = createDoctorSchedule(Arrays.asList(mondayShift, wednesdayShift));
        }

        @Test
        @DisplayName("Should be available within a scheduled shift")
        void shouldBeAvailableWithinShift() {
            // Monday check
            assertTrue(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 10, 0))); // Monday 10:00 (within shift)
            assertTrue(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 9, 1))); // Monday 9:00 (start of shift)
            assertTrue(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 16, 59))); // Monday 16:59 (just before end)

            // Wednesday check
            assertTrue(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 9, 11, 30))); // Wednesday 11:30 (within shift)
        }

        @Test
        @DisplayName("Should not be available outside a scheduled shift on a scheduled day")
        void shouldNotBeAvailableOutsideShiftOnScheduledDay() {
            // Monday check
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 8, 59)));  // Just before shift
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 17, 0)));  // At end of shift (exclusive)
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 18, 0)));  // After shift

            // Wednesday check
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 9, 9, 59)));  // Just before shift
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 9, 14, 0)));  // At end of shift (exclusive)
        }

        @Test
        @DisplayName("Should not be available on unscheduled days")
        void shouldNotBeAvailableOnUnscheduledDays() {
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 8, 10, 0))); // Tuesday
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 10, 10, 0))); // Thursday
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 11, 10, 0))); // Friday
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 12, 10, 0))); // Saturday
            assertFalse(doctorSchedule.isAvailableAt(LocalDateTime.of(2025, 7, 13, 10, 0))); // Sunday
        }

        @Test
        @DisplayName("Should handle empty schedule gracefully")
        void shouldHandleEmptySchedule() {
            DoctorSchedule emptySchedule = DoctorSchedule.createDoctorEmptySchedule();
            assertFalse(emptySchedule.isAvailableAt(LocalDateTime.of(2025, 7, 7, 10, 0)));
        }
    }
}
