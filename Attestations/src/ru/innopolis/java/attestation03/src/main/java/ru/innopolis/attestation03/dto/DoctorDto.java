package ru.innopolis.attestation03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.models.TimeSlot;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {
    private Long id;
    private Integer speciality;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Long phoneNumber;
//    private List<Appointment> appointmentList;
//    private List<TimeSlot> timeSlotsList;

    public static DoctorDto from(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .speciality(doctor.getSpeciality())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .patronymic(doctor.getPatronymic())
                .phoneNumber(doctor.getPhoneNumber())
//                .timeSlotsList(doctor.getTimeSlotsList())
//                .appointmentList(doctor.getAppointmentList())
                .build();
    }

    public static List<DoctorDto> from(List<Doctor> doctors) {
        return doctors.stream().map(DoctorDto::from).collect(Collectors.toList());
    }
}
