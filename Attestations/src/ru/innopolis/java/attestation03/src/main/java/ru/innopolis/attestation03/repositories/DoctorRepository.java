package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.attestation03.models.Doctor;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value="SELECT doctors FROM Doctor doctors WHERE doctors.hasRemoved = false")
    List<Doctor> findAllByHasRemovedFalse();
}
