package com.example.module6.service;
import com.example.module6.model.UserBookingDTO;
import java.util.List;

public interface ICCDVService {
    List<UserBookingDTO> findTop3Renters(Long ccdvId);

    List<UserBookingDTO> findTop3RecentRenters(Long bookedUserId);


}
