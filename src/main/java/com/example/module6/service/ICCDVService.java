package com.example.module6.service;

import com.example.module6.model.User;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ICCDVService {
    List<User> findTop3Renters(Long ccdvId);

    List<User> findTop3RecentRenters(@Param("bookedUserId") Long bookedUserId);


}
