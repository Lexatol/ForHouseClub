package club.forhouse.services;

import club.forhouse.dto.PriceListCompanyDto;
import club.forhouse.entities.PriceListCompany;
import club.forhouse.mappers.PriceListCompanyMapper;
import club.forhouse.repositories.PriceListCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceListCompanyService {
    private final PriceListCompanyRepository priceListCompanyRepository;
    private final PriceListCompanyMapper priceListCompanyMapper;

    public PriceListCompanyDto findById(Long id) {
        PriceListCompany priceListCompany = priceListCompanyRepository.findById(id).orElseThrow();
        return priceListCompanyMapper.toDto(priceListCompany);
    }
}
