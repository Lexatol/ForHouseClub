package club.forhouse.services.tenders;

import club.forhouse.dto.tenders.TenderPlatformDto;
import club.forhouse.entities.tenders.TenderPlatform;
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

    public List<TenderPlatformDto> findAll() {
        return tenderPlatformMapper.toListDto(tendersPlatformRepository.findAll());
    }

    public TenderPlatformDto findById(Long id) {
        TenderPlatform tenderPlatform = tendersPlatformRepository.findById(id).orElseThrow();
        return tenderPlatformMapper.toDto(tenderPlatform);
    }
}
