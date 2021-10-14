package club.forhouse.mappers;

import club.forhouse.dto.profiles.PriceListCompanyDto;
import club.forhouse.entities.profiles.PriceListCompany;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface PriceListCompanyMapper {
    PriceListCompanyDto toDto(PriceListCompany priceListCompany);

    @InheritInverseConfiguration
    PriceListCompany toItem(PriceListCompanyDto priceListCompanyDto);
}
