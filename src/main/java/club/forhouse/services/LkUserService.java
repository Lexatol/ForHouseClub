package club.forhouse.services;

import club.forhouse.dto.LkUserDto;
import club.forhouse.entities.LkUser;
import club.forhouse.repositories.LkUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LkUserService {
    private final LkUserRepository lkUserRepository;

    public List<LkUserDto> findAll() {
        return lkUserRepository.findAll().stream().map(LkUserDto::new).collect(Collectors.toList());
    }

    public Optional<LkUser> findById(Long id) {
        return lkUserRepository.findById(id);
    }
}

