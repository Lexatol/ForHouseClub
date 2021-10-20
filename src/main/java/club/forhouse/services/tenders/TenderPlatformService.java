package club.forhouse.services.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.dto.tenders.TenderPlatformDto;
import club.forhouse.entities.tenders.Tender;
import club.forhouse.entities.tenders.TenderPlatform;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.TenderMapper;
import club.forhouse.mappers.TenderPlatformMapper;
import club.forhouse.repositories.tenders.TendersPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenderPlatformService {
    private final TendersPlatformRepository tendersPlatformRepository;
    private final TenderPlatformMapper tenderPlatformMapper;
    private final TenderMapper tenderMapper;
    private final TendersService tendersService;

    public List<TenderPlatformDto> findAll() {
        return tenderPlatformMapper.toListDto(tendersPlatformRepository.findAll());
    }

    public TenderPlatformDto findById(Long id) {
        TenderPlatform tenderPlatform = tendersPlatformRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Platform with id: " + id));
        return tenderPlatformMapper.toDto(tenderPlatform);
    }
    //TODO протестировать метод добавления тендера на площадку
    public TenderPlatformDto addTender(SystemTenderDto systemTenderDto) {
        TenderPlatform tenderPlatform = tendersPlatformRepository.findByTitle(systemTenderDto.getSystemPlatformDto().getTitle());
        Tender tender = tendersService.findByTitle(systemTenderDto.getTitle());
//        tenderPlatform.getTenders().add(tender);
        return tenderPlatformMapper.toDto(tenderPlatform);
    }
}
