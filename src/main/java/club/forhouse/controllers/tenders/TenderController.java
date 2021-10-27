package club.forhouse.controllers.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.exceptions.MarketError;
import club.forhouse.services.profiles.CompanyService;
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
    private final CompanyService companyService;

    @GetMapping("id/{id}")
    public TenderDto findById(@PathVariable Long id) {
        return tendersService.findById(id);
    }

    @GetMapping("comp/{c}")
    public List<TenderDto> findByCompanyCustomer(@PathVariable String c) {
        System.out.println(c);
        Company company = companyService.findByName(c);
        return tendersService.findByCompanyCustomer(company);
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
}
