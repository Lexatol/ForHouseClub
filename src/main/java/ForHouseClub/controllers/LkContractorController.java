package ForHouseClub.controllers;

import ForHouseClub.dto.LkContractorDto;
import ForHouseClub.entities.LkContractor;
import ForHouseClub.exceptions.ResourceNotFoundException;
import ForHouseClub.services.LkContractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/LkContractor")
public class LkContractorController {
    private final LkContractorService lkContractorService;

    @GetMapping("/{id}")
    public LkContractorDto findLkContractorById(@PathVariable Long id) {
        LkContractor lkC = lkContractorService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with id: " + id));
        return new LkContractorDto(lkC);
    }

    @GetMapping
    public List<LkContractorDto> findAll() {
        return lkContractorService.findAll().stream().map(LkContractorDto::new).collect(Collectors.toList());
    }





}
