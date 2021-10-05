package club.forhouse.configuration;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkTemplateMapper {
    private final ModelMapper modelMapper;


}
