package club.forhouse.services.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.tenders.Tender;
import club.forhouse.entities.tenders.TenderPlatform;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.TenderMapper;
import club.forhouse.repositories.tenders.TenderRepository;
import club.forhouse.services.profiles.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TendersService {
    private final TenderRepository tenderRepository;
    private final TenderMapper tenderMapper;
    private final CompanyService companyService;
    private final TenderPlatformService tenderPlatformService;

    public List<TenderDto> findAll() {
        return tenderMapper.toListDto(tenderRepository.findAll());
    }

    public TenderDto findById(Long id) {
        Tender tender = tenderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Tender with id: " + id));
        return tenderMapper.toDto(tender);
    }

    public TenderDto save(SystemTenderDto systemTenderDto) {
        Company company = companyService.findByName(systemTenderDto.getCustomer().getCompanyName());
        Tender tender = new Tender();
        tender.setTitle(systemTenderDto.getTitle());
        tender.setDataStart(systemTenderDto.getDataStart());
        tender.setCustomer(company);
        tender.setAddress(systemTenderDto.getAddress());
        tender.setDescription(systemTenderDto.getDescription());
        tender.setPrice(systemTenderDto.getPrice());
        TenderPlatform tenderPlatform = tenderPlatformService.findByTitle(systemTenderDto.getTitlePlatform());
        //TODO необходимо на фронте установить по умолчанию статус "черновик"
        // и добавить кнопку опубликовать тендер и после этого изменить статус на другой
//        tender.setStatus(systemTenderDto.getStatus());
        tender = tenderRepository.save(tender);
        tenderPlatformService.save(tenderPlatform, tender);
        return tenderMapper.toDto(tender);
    }

    public void delete(TenderDto tenderDto) {
        Tender tender = tenderMapper.toEntity(tenderDto);
        tenderRepository.delete(tender);
    }

    public Tender findByTitle(String title) {
        return tenderRepository.findByTitle(title);

    }
}
