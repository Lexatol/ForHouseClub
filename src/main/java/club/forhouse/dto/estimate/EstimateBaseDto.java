package club.forhouse.dto.estimate;

import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.dto.profiles.UserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class EstimateBaseDto {
    private Long estimateId;
    private CompanyDto company;
    private Integer number;
    private Integer sum;
    private LocalDateTime date;
    private String address;
    private UserDto author;
}
