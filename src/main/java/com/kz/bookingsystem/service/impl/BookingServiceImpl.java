package com.kz.bookingsystem.service.impl;

import com.kz.bookingsystem.common.GenericService;
import com.kz.bookingsystem.exception.CoreApiException;
import com.kz.bookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;

    private final UserPackageRepository userPackageRepository;

    private final BSClassRepository bsClassRepository;

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(UserRepository userRepository, UserPackageRepository userPackageRepository, BSClassRepository bsClassRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.userPackageRepository = userPackageRepository;
        this.bsClassRepository = bsClassRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public GenericService<Integer> saveBooking(BookingDTO bookingDTO) {
        GenericService<Integer> result = new GenericService<>();
        try {
            var user = userRepository.findById(bookingDTO.getUserId());
            if(user.isEmpty()) {
                result.fail(new Throwable(){}, "User not exists");
                throw new CoreApiException("Fail to save booking", result.getCode(), "User not exists");
            }


            var bsClass = bsClassRepository.findById(bookingDTO.getBsClassId());
            if(bsClass.isEmpty()) {
                result.fail(new Throwable(){}, "Class not exists");
                throw new CoreApiException("Fail to save booking", result.getCode(), "Class not exists");
            }

            var userPackageList = userPackageRepository.getBSUserPackageByUserId(bookingDTO.getUserId());
            BSUserPackage userPackage = null;

            for (BSUserPackage item: userPackageList) {
                if (item.getBsPackage().getCountryId().equals(bsClass.get().getCountryId())){
                    userPackage = item;
                    break;
                }
            }

            if(userPackage == null) {
                result.fail(new Throwable(){}, "User do not have valid package");
                throw new CoreApiException("Fail to Save booking", result.getCode(), "User do not have valid package");
            }

            var existing = bookingRepository.findByBsClassidAndUserPackageId(bsClass.get().getId(), userPackage.getId());
            if(existing.isPresent()){
                result.fail(new Throwable(){}, "Already booked");
                throw new CoreApiException("Fail to Save booking", result.getCode(), "Already booked");
            }

            var booking = BSBooking.builder()
                    .bsClassid(bsClass.get().getId())
                    .userPackageId(userPackage.getId())
                    .build();

            bookingRepository.save(booking);

            userPackage.reduceCredit();
            userPackageRepository.save(userPackage);

            bsClass.get().addBookedSlot();
            bsClassRepository.save(bsClass.get());

            result.setStatus(Boolean.TRUE);
            result.success(booking.getId());
        }
        catch (Exception ex) {
            result.fail(ex, "Failed to book");
            throw new CoreApiException(ex.getMessage());
        }
        return result;
    }
}
