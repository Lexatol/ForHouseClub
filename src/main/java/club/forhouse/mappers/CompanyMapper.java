package club.forhouse.mappers;

import club.forhouse.dtoM.CompanyDtoM;
import club.forhouse.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "companyId", target = "id")
    CompanyDtoM toDto(Company company);

    @Mapping(source = "companyId", target = "id")
    List<CompanyDtoM> toListDto(List<Company> companies);

}
