package club.forhouse.mappers;

import club.forhouse.dto.SimpleCompanyDto;
import club.forhouse.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper
public interface SimpleCompanyMapper {
    SimpleCompanyMapper INSTANCE = Mappers.getMapper(SimpleCompanyMapper.class);

    @Mapping(source = "companyId", target = "id")
    SimpleCompanyDto toDto(Company company);

    @Mapping(source = "companyId", target = "id")
    List<SimpleCompanyDto> toListDto(List<Company> companies);


}
