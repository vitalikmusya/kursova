package com.alex.service;

import com.alex.models.BmpData;
import com.alex.repository.BmpDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BmpDataService {

    @Autowired
    BmpDataRepository bmpDataRepository;

    public BmpData addData(final BmpData bmpData){
        return bmpDataRepository.save(bmpData);
    }

    public BmpData updateData(final BmpData bmpData){
        return bmpDataRepository.save(bmpData);
    }

    public List<BmpData> getData() {
        return bmpDataRepository.findAll();
    }

    public BmpData getDataById(final Integer id){
        return bmpDataRepository.findById(id).orElse(null);
    }

    public void deleteDataById(final Integer id) {
        bmpDataRepository.deleteById(id);
    }
}


