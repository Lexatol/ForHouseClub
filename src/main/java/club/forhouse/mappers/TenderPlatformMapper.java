package club.forhouse.mappers;

import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.dto.tenders.TenderPlatformDto;
import club.forhouse.entities.tenders.Tender;
import club.forhouse.entities.tenders.TenderPlatform;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TenderPlatformMapper {
    TenderPlatformDto toDto(TenderPlatform tenderPlatform);

    List<TenderPlatformDto> toListDto(List<TenderPlatform> tendersPlatforms);

    @InheritInverseConfiguration
    TenderPlatform toEntity(TenderPlatformDto tenderPlatformDto);
}
