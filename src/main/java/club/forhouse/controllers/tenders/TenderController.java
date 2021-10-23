package club.forhouse.controllers.tenders;

import club.forhouse.dto.tenders.SystemTenderDto;
import club.forhouse.dto.tenders.TenderDto;
import club.forhouse.exceptions.MarketError;
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

    @GetMapping("id/{id}")
    public TenderDto findById(@PathVariable Long id) {
        return tendersService.findById(id);
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
}
