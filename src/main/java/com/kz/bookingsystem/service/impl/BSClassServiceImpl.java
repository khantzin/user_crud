package com.kz.bookingsystem.service.impl;

import com.kz.bookingsystem.common.GenericService;
import com.kz.bookingsystem.data.BSClassData;
import com.kz.bookingsystem.exception.CoreApiException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BSClassServiceImpl implements BSClassService {

    private final BSClassRepository repository;

    public BSClassServiceImpl(BSClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public GenericService<Integer> save(BSClassDTO bsClassDTO) {
        GenericService<Integer> result = new GenericService<>();
        try{
            var existing = repository.findByCode(bsClassDTO.getCode());
            if(existing.isPresent()) {
                result.fail(new Throwable(){}, "Class already exists");
                throw new CoreApiException("Fail to create Class", result.getCode(), "Class already exists");
            }

            var bsClass = BSClass.builder()
                    .code(bsClassDTO.getCode())
                    .name(bsClassDTO.getName())
                    .slot(bsClassDTO.getTotalSlot())
                    .countryId(bsClassDTO.getCountryId())
                    .bookedSlot(0)
                    .build();
            repository.save(bsClass);

            result.setStatus(Boolean.TRUE);
            result.success(bsClass.getId());
        }
        catch (Exception ex) {
            result.fail(ex, "Failed to create Class");
            throw new CoreApiException(ex.getMessage());
        }

        return result;
    }

    @Override
    public GenericService<List<BSClassData>> getAllClasses() {
        GenericService<List<BSClassData>> result = new GenericService<>();
        try {
            List<BSClassData> list = new ArrayList<>();
            var classDatas = repository.findAll();
            classDatas.forEach(item -> {
                var data = covertEntityToData(item);
                list.add(data);
            });

            result.setStatus(Boolean.TRUE);
            result.setData(list);
        }
        catch (Exception ex){
            result.fail(ex, "Failed to get classes");
            throw new CoreApiException(ex.getMessage());
        }
        return result;
    }

    private BSClassData covertEntityToData(BSClass item) {
        return BSClassData.builder()
                .id(item.getId())
                .code(item.getCode())
                .name(item.getName())
                .totalSlot(item.getSlot())
                .bookedSlot(item.getBookedSlot())
                .availabelSlot(item.availabelSlot())
                .countryName(item.getCountry().getName())
                .build();

    }
}
