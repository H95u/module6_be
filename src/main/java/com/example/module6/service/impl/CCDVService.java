package com.example.module6.service.impl;

import com.example.module6.model.UserBookingCountDTO;
import com.example.module6.model.UserBookingEndTimeDTO;
import com.example.module6.repository.ICCDVRepository;
import com.example.module6.service.ICCDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CCDVService implements ICCDVService {
    @Autowired
    @Lazy
    private ICCDVRepository iccdvRepository;

    @Override
    public List<UserBookingCountDTO> findTop3Renters(Long ccdvId) {
        List<UserBookingCountDTO> rents = iccdvRepository.findTop3Renters(ccdvId);
        if (rents.size() > 3) {
            rents = rents.subList(0, 3);
        }
        return rents;
    }

    @Override
    public List<UserBookingEndTimeDTO> findTop3RecentRenters(Long bookedUserId) {
        List<UserBookingEndTimeDTO> recentRenters = iccdvRepository.findTop3RecentRenters(bookedUserId);
        if (recentRenters.size() > 3) {
            recentRenters = recentRenters.subList(0, 3);
        }
        return recentRenters;
    }


}
