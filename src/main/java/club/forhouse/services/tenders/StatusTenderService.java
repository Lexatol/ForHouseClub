package club.forhouse.services.tenders;

import club.forhouse.entities.tenders.StatusTender;
import club.forhouse.repositories.tenders.StatusTenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusTenderService {
    private final StatusTenderRepository statusTenderRepository;

    public StatusTender findByTitle (String title) {
        return statusTenderRepository.findByTitle(title);
    }
}
