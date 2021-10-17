package club.forhouse.controllers.profiles;

import club.forhouse.dto.profiles.LkUserDto;
import club.forhouse.services.profiles.LkUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profile_users")
public class LkUserController {
    private final LkUserService lkUserService;

    @GetMapping
    public List<LkUserDto> findAll() {
        return lkUserService.findAll();
    }

    @GetMapping("/{id}")
    public LkUserDto findById(@PathVariable Long id) {
        return lkUserService.findById(id);
    }
}

