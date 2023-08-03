package com.example.module6.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOptionRequest {
    private List<Long> optionIds;
}
