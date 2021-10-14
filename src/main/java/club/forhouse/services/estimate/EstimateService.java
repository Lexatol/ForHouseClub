package club.forhouse.services.estimate;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.estimate.Estimate;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.estimate.EstimateMaterialRepository;
import club.forhouse.repositories.estimate.EstimateOperationRepository;
import club.forhouse.repositories.estimate.EstimateRepository;
import club.forhouse.repositories.estimate.EstimateWorkRepository;
import club.forhouse.repositories.profiles.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final EstimateWorkRepository estimateWorkRepository;
    private final EstimateOperationRepository estimateOperationRepository;
    private final EstimateMaterialRepository estimateMaterialRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public EstimateDto createNew(Long userId, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Company with id: " + companyId));

        Pageable request = PageRequest.of(0, 1, Sort.Direction.DESC, "number");
        Optional<Estimate> found = estimateRepository.findTop1ByCompany(company, request).stream().findFirst();
        int number = found.map(value -> value.getNumber() + 1).orElse(1);
        Estimate estimate = new Estimate();
        estimate.setNumber(number);
        estimate.setCompany(company);
        estimate.setAuthor(userRepository.getById(userId));
        estimate.setDate(LocalDate.now());
        return modelMapper.map(estimateRepository.save(estimate), EstimateDto.class);
    }

}
