package club.forhouse.services;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.entities.Company;
import club.forhouse.entities.estimate.Estimate;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final EstimateWorkRepository estimateWorkRepository;
    private final EstimateOperationRepository estimateOperationRepository;
    private final EstimateMaterialRepository estimateMaterialRepository;
    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    private EstimateDto createNew(Long userId, Long company_id) {
        Company company = companyRepository.findById(company_id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find Company with id: " + company_id));
        return modelMapper.map(estimateRepository.save(new Estimate()), EstimateDto.class);
    }

}
