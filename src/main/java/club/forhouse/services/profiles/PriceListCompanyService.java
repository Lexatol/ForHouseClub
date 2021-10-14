package club.forhouse.services.profiles;

import club.forhouse.configuration.OperationMapper;
import club.forhouse.dto.profiles.PriceListCompanyDto;
import club.forhouse.dto.profiles.ProfileContractorDto;
import club.forhouse.dto.operation.OperationDto;
import club.forhouse.entities.operation.Operation;
import club.forhouse.entities.profiles.PriceListCompany;
import club.forhouse.mappers.PriceListCompanyMapper;
import club.forhouse.repositories.profiles.PriceListCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceListCompanyService {
    private final PriceListCompanyRepository priceListCompanyRepository;
    private final PriceListCompanyMapper priceListCompanyMapper;
    private final OperationMapper operationMapper;
    private final ProfileCompanyService profileCompanyService;

    public PriceListCompanyDto findById(Long id) {
        PriceListCompany priceListCompany = priceListCompanyRepository.findById(id).orElseThrow();
        return priceListCompanyMapper.toDto(priceListCompany);
    }

    //TODO необходимо протестить, не проверял, уточнить возможность передачи двух requestbody
    public PriceListCompanyDto addNewOperation(ProfileContractorDto profileContractorDto, OperationDto operationDto) {
        ProfileContractorDto proContractDto  = profileCompanyService.findById(profileContractorDto.getPriceListCompany().getId());
        PriceListCompany priceListCompany = priceListCompanyMapper.toItem(proContractDto.getPriceListCompany());
        Operation operation = operationMapper.toEntity(operationDto);
        priceListCompany.getOperation().add(operation);
        return priceListCompanyMapper.toDto(priceListCompany);
    }
}
