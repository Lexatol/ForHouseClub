package club.forhouse.services;

import club.forhouse.entities.Specialization;
import club.forhouse.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecializationServices {
    private final SpecializationRepository specializationRepository;

    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    public Optional<Specialization> findById(Long id) {
        return specializationRepository.findById(id);
    }

}
