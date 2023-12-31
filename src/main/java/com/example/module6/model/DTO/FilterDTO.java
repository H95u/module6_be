package com.example.module6.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FilterDTO {
    private Integer gender;
    private Long addressId;
    private Integer sortPrice;
    private Long rentCount;
    private List<Integer> ageRange;
    private String username;
}
