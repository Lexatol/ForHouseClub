package club.forhouse.mappers;

import club.forhouse.dto.pricelist.PriceListCompanyDto;
import club.forhouse.entities.pricelist.PriceListCompany;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface PriceListCompanyMapper {
    PriceListCompanyDto toDto(PriceListCompany priceListCompany);

    @InheritInverseConfiguration
    PriceListCompany toEntity(PriceListCompanyDto priceListCompanyDto);
}
