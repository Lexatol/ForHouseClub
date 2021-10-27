package club.forhouse.services.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.tenders.StatusTender;
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
    private final StatusTenderService statusTenderService;
    private final TenderPlatformService tenderPlatformService;

    public List<TenderDto> findAll() {
        return tenderMapper.toListDto(tenderRepository.findAll());
    }

    public TenderDto findById(Long id) {
        Tender tender = tenderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Tender with id: " + id));
        return tenderMapper.toDto(tender);
    }

    public Tender findTenderById(Long id) {
        Tender tender = tenderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Tender with id: " + id));
        return tender;
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
        StatusTender status = statusTenderService.findByTitle("объявлен тендер");
        tender.setStatus(status);
        tender = tenderRepository.save(tender);
//        TenderPlatform tenderPlatform = tenderPlatformService.findByTitle(systemTenderDto.getTitlePlatform());
//        tender.setTenderPlatform(tenderPlatform);
        return tenderMapper.toDto(tender);
    }

    public void delete(Long id) {
        Tender tender = tenderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Tender with id: " + id));
        tenderRepository.delete(tender);
    }

    public List<TenderDto> findByCompanyCustomer(Company company) {
        return tenderMapper.toListDto(tenderRepository.findAllByCustomer(company));
    }

    public void saveOrUpdate(Tender tender) {
        tenderRepository.save(tender);
    }

    public List<TenderDto> findByCompanyContractor(Company company) {
        return tenderMapper.toListDto(tenderRepository.findAllByContractor(company));
    }

    //public Tender findTenderByCompanyCustomer(Company company) {
        //return tenderMapper.toDto(tenderRepository.f)
    //}
}
