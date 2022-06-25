package com.alex.controller;

import com.alex.DTO.BmpDataDTO;
import com.alex.exceptions.ItemNotFoundException;
import com.alex.models.BmpData;
import com.alex.service.BmpDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

    @RestController
    @RequestMapping(path = "/bmp_data")
    public class BmpDataController {
        private static final Logger LOGGER = LoggerFactory.getLogger(BmpDataController.class);

        @Autowired
        private BmpDataService bmpDataService;

        @PostMapping
        public ResponseEntity<BmpDataDTO> createBmpData(@Valid @RequestBody final BmpData bmpData) {
            LOGGER.info("Added new data values");
            return new ResponseEntity<BmpDataDTO>(new BmpDataDTO(bmpDataService.addData(bmpData)), HttpStatus.OK);
        }

        @PutMapping(path = "/{id}")
        public ResponseEntity<BmpDataDTO> updateBmpData(@PathVariable("id") final int id,
                                                      @Valid @RequestBody final BmpData bmpData) {

            if (bmpDataService.getDataById(id) == null) {
                LOGGER.error("Can't put(updateBmpData) an client with non-existing id: " + id);
                throw new ItemNotFoundException("Can't put(updateBmpData) an BmpData with non-existing id: " + id);
            }
            LOGGER.info("Successfully updated BmpData with id: " + id);
            bmpData.setId(id);
            return new ResponseEntity<BmpDataDTO>(new BmpDataDTO(bmpData), HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<BmpDataDTO>> getAllData() {
            LOGGER.info("Gave away all Bmp Sensor Data ");
            List<BmpData> bmpDatas = bmpDataService.getData();
            List<BmpDataDTO> bmpDataDTOS = new ArrayList<>();
            for (BmpData bmpData:bmpDatas) {
                BmpDataDTO bmpDataDTO = new BmpDataDTO(bmpData);
                bmpDataDTOS.add(bmpDataDTO);
            }
            return new ResponseEntity<List<BmpDataDTO>>(bmpDataDTOS, HttpStatus.OK);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<BmpDataDTO> getData(@PathVariable(name = "id")final Integer id){
            if (bmpDataService.getDataById(id) == null) {
                LOGGER.error("Can't get(getData) an Bmp_Data with non-existing id: " + id);
                throw new ItemNotFoundException("Can't get(getData) an Bmp_Data with non-existing id: " + id);
            }
            LOGGER.info("Successfully get an Bmp_Data with id: " + id);
            BmpData bmpData = bmpDataService.getDataById(id);
            return new ResponseEntity<BmpDataDTO>(new BmpDataDTO(bmpData), HttpStatus.OK);
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity<BmpData> deleteDataById(@PathVariable("id") final Integer id) {
            if (bmpDataService.getDataById(id) == null) {
                LOGGER.error("Can't delete(deleteDataById) an Bmp_Data with non-existing id: " + id);
                throw new ItemNotFoundException("Can't delete(deleteDataById) an Bmp_Data with non-existing id: " + id);
            }
            LOGGER.info("Successfully deleted Bmp_Data with id: " + id);
            bmpDataService.deleteDataById(id);
            return ResponseEntity.noContent().build();
        }
    }

