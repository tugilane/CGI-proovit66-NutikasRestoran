package com.org.NutikasRestoran_service.laud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaudService {

    @Autowired
    private LaudRepository laudRepository;

    public List<Laud> findAll() {
        return laudRepository.findAll();
    }

    public Laud save(Laud laud) {
        return laudRepository.save(laud);
    }

    public Optional<Laud> findById(long id) {
        return laudRepository.findById(id);
    }


}
