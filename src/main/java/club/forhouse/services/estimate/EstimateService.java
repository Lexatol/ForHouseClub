package club.forhouse.services.estimate;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.dto.profiles.UserDto;
import club.forhouse.entities.estimate.Estimate;
import club.forhouse.entities.profiles.Company;
import club.forhouse.mappers.CompanyMapper;
import club.forhouse.mappers.UserMapper;
import club.forhouse.repositories.estimate.EstimateMaterialRepository;
import club.forhouse.repositories.estimate.EstimateOperationRepository;
import club.forhouse.repositories.estimate.EstimateRepository;
import club.forhouse.repositories.estimate.EstimateWorkRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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

    private final ModelMapper modelMapper;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;

    public EstimateDto createNew(UserDto user, CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        Pageable request = PageRequest.of(0, 1, Sort.Direction.DESC, "number");
        Optional<Estimate> found = estimateRepository.findTop1ByCompany(company, request).stream().findFirst();
        int number = found.map(value -> value.getNumber() + 1).orElse(1);
        Estimate estimate = new Estimate();
        estimate.setNumber(number);
        estimate.setCompany(company);
        estimate.setAuthor(userMapper.toEntity(user));
        estimate.setDate(LocalDate.now());
        return modelMapper.map(estimateRepository.save(estimate), EstimateDto.class);
    }

    public Page<EstimateDto> findAll(UserDto user, CompanyDto companyDto, int page) {
        Company company = companyMapper.toEntity(companyDto);
        Pageable request = PageRequest.of(page, 10, Sort.Direction.DESC, "date");
        return estimateRepository.findAll(request).map(it -> modelMapper.map(it, EstimateDto.class));
    }
}
