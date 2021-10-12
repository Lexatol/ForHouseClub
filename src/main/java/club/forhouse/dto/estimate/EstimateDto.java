package club.forhouse.dto.estimate;

import club.forhouse.dto.CompanyDto;
import club.forhouse.dto.UserDto;
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
    private UserDto author;
}
