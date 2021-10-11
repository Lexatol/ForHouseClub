package club.forhouse.mappers;

import club.forhouse.dto.CompanyDto;
import club.forhouse.entities.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyDto toDto(Company company);

    List<CompanyDto> toListDto(List<Company> companyList);
}
