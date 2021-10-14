package club.forhouse.mappers;

import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.entities.tenders.Tender;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TenderMapper {
    TenderDto toDto(Tender tender);
    List<TenderDto> toListDto(List<Tender> tenders);
}
