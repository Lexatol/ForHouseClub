package club.forhouse.services.profiles;

import club.forhouse.configuration.OperationMapper;
import club.forhouse.dto.pricelist.PriceListCompanyDto;
import club.forhouse.entities.pricelist.PriceListCompany;
import club.forhouse.exceptions.ResourceNotFoundException;
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
    private final CompanyService companyService;

    public PriceListCompanyDto findById(Long id) {
        PriceListCompany priceListCompany = priceListCompanyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Pricelist with id: " + id));
        return priceListCompanyMapper.toDto(priceListCompany);
    }
//TODO не работает, надо тестировать и править
//    public ProfileContractorDto addNewOperation(Long companyId, OperationDto operationDto) {
//        ProfileContractorDto proContractDto  = profileCompanyService.findById(companyId);
//        Company company = companyService.findByName(proContractDto.getCompany().getCompanyName());
//        PriceListCompany priceListCompany = priceListCompanyMapper.toEntity(proContractDto.getPriceListCompany());
//        Operation operation = operationMapper.toEntity(operationDto);
//        priceListCompany.getOperation().add(operation);
//        proContractDto.setPriceListCompany(priceListCompanyMapper.toDto(priceListCompany));
//        return proContractDto;
//    }
}
