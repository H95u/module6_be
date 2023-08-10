package com.example.module6.service;
import com.example.module6.model.UserBookingCountDTO;
import com.example.module6.model.UserBookingEndTimeDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICCDVService {
    List<UserBookingCountDTO> findTop3Renters(Long ccdvId);

    List<UserBookingEndTimeDTO> findTop3RecentRenters(Long bookedUserId);


}
