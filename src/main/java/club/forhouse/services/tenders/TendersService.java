package club.forhouse.services.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.entities.tenders.Tender;
import club.forhouse.mappers.TenderMapper;
import club.forhouse.repositories.tenders.StatusTenderRepository;
import club.forhouse.repositories.tenders.TenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TendersService {
    private final TenderRepository tenderRepository;
    private final TenderMapper tenderMapper;

    public List<TenderDto> findAll() {
        return tenderMapper.toListDto(tenderRepository.findAll());
    }

    public TenderDto findById(Long id) {
        Tender tender = tenderRepository.findById(id).orElseThrow();
        return tenderMapper.toDto(tender);
    }

    public TenderDto save(SystemTenderDto systemTenderDto) {
        Tender tender = new Tender();
        tender.setTitle(systemTenderDto.getTitle());
        tender.setDataStart(systemTenderDto.getDataStart());
        tender.setCustomer(systemTenderDto.getCustomer());
        tender.setAddress(systemTenderDto.getAddress());
        tender.setDescription(systemTenderDto.getDescription());
        tender.setPrice(systemTenderDto.getPrice());
        //TODO необходимо на фронте установить по умолчанию статус "черновик"
        // и добавить кнопку опубликовать тендер и после этого изменить статус на другой
        tender.setStatus(systemTenderDto.getStatus());
        tender = tenderRepository.save(tender);
        return tenderMapper.toDto(tender);
    }
}
