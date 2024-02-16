package com.kz.bookingsystem.service.impl;

import com.kz.bookingsystem.common.GenericService;
import com.kz.bookingsystem.exception.CoreApiException;
import com.kz.bookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPackageServiceImpl implements UserPackageService {

    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final UserPackageRepository userPackageRepository;

    public UserPackageServiceImpl(PackageRepository packageRepository, UserRepository userRepository, UserPackageRepository userPackageRepository) {
        this.packageRepository = packageRepository;
        this.userRepository = userRepository;
        this.userPackageRepository = userPackageRepository;
    }


    @Override
    public GenericService<Integer> buyUserPackge(BuyPackageDTO buyPackageDTO) {
        GenericService<Integer> result = new GenericService<>();
        try {
            if(buyPackageDTO.getPackageId() == null) {
                result.fail(new Throwable() {}, "Invalid package id");
                throw new CoreApiException("Fail to buy package", result.getCode(), "Invalid package id");
            }
            if(buyPackageDTO.getUserId() == null) {
                result.fail(new Throwable() {}, "Invalid package id");
                throw new CoreApiException("Fail to buy package", result.getCode(), "Invalid user id");
            }
            var bsPackage = packageRepository.findById(buyPackageDTO.getPackageId());
            if(bsPackage.isEmpty()) {
                result.fail(new Throwable() {}, "Package not exists");
                throw new CoreApiException("Fail to buy package", result.getCode(), "Package not exists");
            }
            var user = userRepository.findById(buyPackageDTO.getUserId());
            if(user.isEmpty()) {
                result.fail(new Throwable() {}, "User not exists");
                throw new CoreApiException("Fail to buy package", result.getCode(), "User not exists");
            }

            var bsUserPackage = BSUserPackage
                    .builder()
                    .packageId(buyPackageDTO.getPackageId())
                    .userId(buyPackageDTO.getUserId())
                    .credit(bsPackage.get().getCredit())
                    .build();
            userPackageRepository.save(bsUserPackage);

            result.setStatus(Boolean.TRUE);
            result.success(bsUserPackage.getId());
        }
        catch (Exception ex) {
            result.fail(ex, "Fail to buy package");
            throw new CoreApiException(ex.getMessage());
        }

        return result;
    }

    @Override
    public GenericService<List<BSUserPackage>> getUserPackageByUserId(Integer userId) {
        GenericService<List<BSUserPackage>> result = new GenericService<>();
        try {
            List<BSUserPackage> list = userPackageRepository.getBSUserPackageByUserId(userId);

            result.setStatus(Boolean.TRUE);
            result.success(list);
        }
        catch (Exception ex) {
            result.fail(ex, "Fail to get user package");
        }
        return result;
    }
}
