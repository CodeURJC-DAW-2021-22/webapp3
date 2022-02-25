package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Monitor;
import webapp3.webapp3.repository.MonitorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorService {

    @Autowired
    private MonitorRepository repository;

    public Optional<Monitor> findById(long id){
        return repository.findById(id);
    }

    public boolean exist(long id){
        return repository.existsById(id);
    }

    public List<Monitor> findAll() {
        return repository.findAll();
    }

    public void save(Monitor monitor) {
        repository.save(monitor);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
