package club.forhouse.controllers.profiles;

import club.forhouse.dto.profiles.SpecializationDto;
import club.forhouse.services.profiles.SpecializationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/spec")
public class SpecializationController {
    private final SpecializationServices specializationServices;

    @GetMapping
    public List<SpecializationDto> findAll() {
        return specializationServices.findAll();
    }

    @GetMapping("/{id}")
    public SpecializationDto findById(@PathVariable Long id) {
        return specializationServices.findById(id);
    }
}