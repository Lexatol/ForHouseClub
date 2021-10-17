package club.forhouse.mappers;

import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.entities.profiles.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyDto toDto(Company company);

    List<CompanyDto> toListDto(List<Company> companyList);
}
