package club.forhouse.mappers;

import club.forhouse.dto.PriceListCompanyDto;
import club.forhouse.entities.PriceListCompany;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface PriceListCompanyMapper {
    PriceListCompanyDto toDto(PriceListCompany priceListCompany);

    @InheritInverseConfiguration
    PriceListCompany toItem(PriceListCompanyDto priceListCompanyDto);
}
