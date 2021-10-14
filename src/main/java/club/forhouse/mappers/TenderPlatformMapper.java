package club.forhouse.mappers;

import club.forhouse.dto.tenders.TenderPlatformDto;
import club.forhouse.entities.tenders.TenderPlatform;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TenderPlatformMapper {
    TenderPlatformDto toDto(TenderPlatform tenderPlatform);
    List<TenderPlatformDto> toListDto(List<TenderPlatform> tendersPlatforms);
}
