package club.forhouse.mappers;

import club.forhouse.dto.SimpleCompanyDto;
import club.forhouse.entities.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SimpleCompanyMapper {
    SimpleCompanyDto toDto(Company company);
    List<SimpleCompanyDto> toListDto(List<Company> companies);


}
