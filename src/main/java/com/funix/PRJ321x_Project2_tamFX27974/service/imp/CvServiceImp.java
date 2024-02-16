package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Cv;
import com.funix.PRJ321x_Project2_tamFX27974.repository.CvRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.CvService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CvServiceImp implements CvService {

    private final CvRepository cvRepository;

    public CvServiceImp(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public void save(Cv cv) {
        if (cv.getUser() == null) {
            cvRepository.save(cv);
        }
        // check if user has already uploaded cv
        else if (cvRepository.existsByUserId(cv.getUser().getId())) {
            // update file name
            cvRepository.updateFileNameByUserId(cv.getUser().getId(), cv.getFileName());
        } else {
            cvRepository.save(cv);
        }
    }

    @Override
    public Cv findByUserId(int id) {
        try {
            return cvRepository.findByUserId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateFileNameById(int id, String fileName) {
        cvRepository.updateFileNameById(id, fileName);
    }

    @Override
    public void deleteById(int id) {
        cvRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserId(int id) {
        return cvRepository.existsByUserId(id);
    }
}
