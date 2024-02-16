package com.kz.bookingsystem.service.impl;

import com.kz.bookingsystem.common.GenericService;
import com.kz.bookingsystem.exception.CoreApiException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    private final PackageRepository repository;

    public PackageServiceImpl(PackageRepository repository) {
        this.repository = repository;
    }

    @Override
    public GenericService<List<BSPackage>> getAvailablePackage() {
        GenericService<List<BSPackage>> result = new GenericService<>();
        try {
            List<BSPackage> list = repository.findAll();

            result.setStatus(Boolean.TRUE);
            result.success(list);
        }
        catch(CoreApiException ex) {
            result.fail(ex, ex.getMessage());
            throw new CoreApiException(ex.getTitle(), ex.getCode(), ex.getMessage());
        }
        return result;
    }
}
