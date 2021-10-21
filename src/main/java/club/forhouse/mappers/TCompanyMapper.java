package club.forhouse.mappers;

import club.forhouse.dto.tenders.TCompanyDto;
import club.forhouse.entities.profiles.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TCompanyMapper {
    TCompanyDto toDto(Company company);

    List<TCompanyDto> toListDto(List<Company> companyList);
}
