package club.forhouse.services.estimate;

import club.forhouse.dto.estimate.EstimateBaseDto;
import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.dto.estimate.EstimateWorkDto;
import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.dto.profiles.UserDto;
import club.forhouse.entities.estimate.Estimate;
import club.forhouse.entities.estimate.EstimateWork;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.worktemplate.WorkCategory;
import club.forhouse.entities.worktemplate.WorkTemplate;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.CompanyMapper;
import club.forhouse.mappers.UserMapper;
import club.forhouse.repositories.estimate.EstimateRepository;
import club.forhouse.repositories.estimate.EstimateWorkRepository;
import club.forhouse.repositories.worktemplate.WorkCategoryRepository;
import club.forhouse.repositories.worktemplate.WorkTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstimateService {

    private final EstimateRepository estimateRepository;
    private final EstimateWorkRepository estimateWorkRepository;
    private final WorkTemplateRepository workTemplateRepository;
    private final WorkCategoryRepository workCategoryRepository;

    private final ModelMapper modelMapper;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;

    public EstimateBaseDto createNew(UserDto user, CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        Pageable request = PageRequest.of(0, 1, Sort.Direction.DESC, "number");
        Optional<Estimate> found = estimateRepository.findTop1ByCompany(company, request).stream().findFirst();
        int number = found.map(value -> value.getNumber() + 1).orElse(1);
        Estimate estimate = new Estimate();
        estimate.setNumber(number);
        estimate.setCompany(company);
        estimate.setAuthor(userMapper.toEntity(user));
        estimate.setDate(LocalDateTime.now());
        estimate.setSum(0);
        return modelMapper.map(estimateRepository.save(estimate), EstimateBaseDto.class);
    }

    public Page<EstimateBaseDto> findAll(CompanyDto companyDto, int page) {
        Company company = companyMapper.toEntity(companyDto);
        Pageable request = PageRequest.of(page, 5, Sort.Direction.DESC, "date");
        return estimateRepository.findAllByCompany(company, request).map(it -> modelMapper.map(it, EstimateBaseDto.class));
    }

    public EstimateDto findByCompanyAndId(CompanyDto company, Long estimateId) {
        Estimate found = getEstimate(estimateId);
        EstimateDto estimateDto = modelMapper.map(found, EstimateDto.class);
        if (Objects.equals(estimateDto.getCompany().getCompanyId(), company.getCompanyId())) {
            return estimateDto;
        } else {
            throw new ResourceNotFoundException("Unable to find Estimate with id " + estimateId + " and Company " + company.getCompanyName());
        }
    }

    public EstimateDto save(EstimateDto estimateDto) {
        return modelMapper.map(
                estimateRepository.save(
                        modelMapper.map(estimateDto, Estimate.class)
                ), EstimateDto.class);
    }

    public List<EstimateWorkDto> getWorksForEstimateAndCategory(Long estimateId, Long categoryId) {
        Estimate estimate = getEstimate(estimateId);

        WorkCategory category = workCategoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Category with id " + categoryId)
        );

        return estimateWorkRepository.findAllByEstimateAndCategory(estimate, category).stream()
                .map(it -> modelMapper.map(it, EstimateWorkDto.class))
                .collect(Collectors.toList());
    }


    public List<EstimateWorkDto> getWorksForEstimate(Long estimateId) {
        Estimate estimate = getEstimate(estimateId);

        return estimateWorkRepository.findAllByEstimate(estimate).stream()
                .map(it -> modelMapper.map(it, EstimateWorkDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public EstimateWorkDto addWork(Long estimateId, Long workTemplateId) {
        Estimate estimate = getEstimate(estimateId);

        WorkTemplate workTemplate = workTemplateRepository.findById(workTemplateId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Work Template with id " + workTemplateId)
        );

        EstimateWork estimateWork = new EstimateWork();
        estimateWork.setEstimate(estimate);
        estimateWork.setWorkTemplate(workTemplate);
        estimate.addWork(estimateWork);

        estimateWorkRepository.save(estimateWork);
        estimateRepository.save(estimate);
        return modelMapper.map(estimateWork, EstimateWorkDto.class);
    }

    private Estimate getEstimate(Long estimateId) {
        return estimateRepository.findById(estimateId).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Estimate with id " + estimateId)
        );
    }

}
