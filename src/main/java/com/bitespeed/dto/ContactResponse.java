package com.bitespeed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {
    private int primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Integer> secondaryContactIds;
}
