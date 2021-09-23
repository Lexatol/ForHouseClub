package club.forhouse.controllers;

import club.forhouse.dto.LkUserDto;
import club.forhouse.entities.LkUser;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.LkUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lk_users")
public class LkUserController {
    private final LkUserService lkUserService;

    @GetMapping
    public List<LkUserDto> findAll() {
        return lkUserService.findAll();
    }

    @GetMapping("/{id}")
    public LkUserDto findById(Long id) {
        LkUser lkU = lkUserService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK user with id: " + id));
        return new LkUserDto(lkU);
    }
}

