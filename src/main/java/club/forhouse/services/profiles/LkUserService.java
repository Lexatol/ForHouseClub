package club.forhouse.services.profiles;

import club.forhouse.dto.profiles.LkUserDto;
import club.forhouse.entities.profiles.LkUser;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.LkUserMapper;
import club.forhouse.repositories.profiles.LkUserRepository;
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

