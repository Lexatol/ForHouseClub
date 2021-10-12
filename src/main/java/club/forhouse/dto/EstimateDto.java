package club.forhouse.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class EstimateDto {
    private Long estimateId;
    private CompanyDto company;
    private Integer number;
    private LocalDate date;
    private String address;
}
