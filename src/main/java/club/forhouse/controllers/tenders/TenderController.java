package club.forhouse.controllers.tenders;

import ch.qos.logback.core.status.Status;
import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TCompanyDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.tenders.StatusTender;
import club.forhouse.entities.tenders.Tender;
import club.forhouse.exceptions.MarketError;
import club.forhouse.services.profiles.CompanyService;
import club.forhouse.services.tenders.StatusTenderService;
import club.forhouse.services.tenders.TenderPlatformService;
import club.forhouse.services.tenders.TendersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tenders")
public class TenderController {
    private final TendersService tendersService;
    private  final TenderPlatformService tenderPlatformService;
    private final CompanyService companyService;
    private final StatusTenderService statusTenderService;

    @GetMapping("id/{id}")
    public TenderDto findById(@PathVariable Long id) {
        return tendersService.findById(id);
    }

    @GetMapping("comp/{c}")
    public List<TenderDto> findByCompanyCustomer(@PathVariable String c) {
        Company company = companyService.findByName(c);
        return tendersService.findByCompanyCustomer(company);
    }

    @GetMapping("chosen/{c}")
    public List<TenderDto> findByCompanyContractor(@PathVariable String c) {
        Company company = companyService.findByName(c);
        return tendersService.findByCompanyContractor(company);
    }

    @GetMapping
    public List<TenderDto> findAll() {
        return tendersService.findAll();
    }

    @PutMapping("/add")
    public ResponseEntity<?> save(@RequestBody SystemTenderDto systemTenderDto) {
        if (systemTenderDto.getTitle() == null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect tender title"), HttpStatus.BAD_REQUEST);
        }
        if (systemTenderDto.getPrice() == null || systemTenderDto.getPrice() < 0) {
            systemTenderDto.setPrice(0L);
        }
        if (systemTenderDto.getAddress() == null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "You need add address"), HttpStatus.BAD_REQUEST);
        }
        tendersService.save(systemTenderDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping
    public void delete(@RequestParam Long tenderId) {
        tendersService.delete(tenderId);
    }

    @GetMapping("/set_contractor/{c}/{id}")
    public void setCompanyContractor(@PathVariable String c, @PathVariable Long id) {
        Company company = companyService.findByName(c);
        Tender tender = tendersService.findTenderById(id);
        TCompanyDto tCompanyDto = new TCompanyDto();
        tCompanyDto.setCompanyName(c);
        tender.setContractor(company);
        StatusTender status = statusTenderService.findByTitle("ждет подтверждения");
        tender.setStatus(status);
        tendersService.saveOrUpdate(tender);
    }

    @GetMapping("/remove_contractor/{id}")
    public void removeCompanyContractor(@PathVariable Long id) {
        Tender tender = tendersService.findTenderById(id);
        tender.setContractor(null);
        StatusTender status = statusTenderService.findByTitle("объявлен тендер");
        tender.setStatus(status);
        tendersService.saveOrUpdate(tender);
    }

    @GetMapping("/approve_contractor/{id}")
    public void approveCompanyContractor(@PathVariable Long id) {
        Tender tender = tendersService.findTenderById(id);
        StatusTender status = statusTenderService.findByTitle("в работе");
        tender.setStatus(status);
        tendersService.saveOrUpdate(tender);
    }

    @GetMapping("/close_tender/{id}")
    public void closeTender(@PathVariable Long id) {
        Tender tender = tendersService.findTenderById(id);
        StatusTender status = statusTenderService.findByTitle("тендер завершен");
        tender.setStatus(status);
        tendersService.saveOrUpdate(tender);
    }
}
