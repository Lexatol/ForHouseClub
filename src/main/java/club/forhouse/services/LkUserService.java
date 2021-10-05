package club.forhouse.services;

import club.forhouse.dto.LkUserDto;
import club.forhouse.entities.LkUser;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.LkUserMapper;
import club.forhouse.repositories.LkUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LkUserService {
    private final LkUserRepository lkUserRepository;
    private final LkUserMapper lkUserMapper;

    public List<LkUserDto> findAll() {
        return lkUserMapper.toListDto(lkUserRepository.findAll());
    }

    public LkUserDto findById(Long id) {
        LkUser lkUser = lkUserRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK user with id: " + id));
        return lkUserMapper.toDto(lkUser);
    }
}

