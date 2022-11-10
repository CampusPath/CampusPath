package campuspath.app.service;

import campuspath.app.entity.Campus;
import campuspath.app.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Brady
 * @since 11/10/2022
 */
@Service
public final class CampusService {

    private final CampusRepository repo;

    public CampusService(@Autowired CampusRepository repo) {
        this.repo = repo;
    }

    public List<Campus> getAll() {
        return this.repo.findAll();
    }

    public Campus getById(final UUID uuid) {
        return this.repo.getReferenceById(uuid);
    }
}
