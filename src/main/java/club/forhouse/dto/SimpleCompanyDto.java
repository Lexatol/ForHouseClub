package club.forhouse.dto;

import club.forhouse.entities.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleCompanyDto {
    private Long id;
    private String name;

    public SimpleCompanyDto(Company company) {
        this.id = company.getCompanyId();
        this.name = company.getCompanyName();
    }
}
