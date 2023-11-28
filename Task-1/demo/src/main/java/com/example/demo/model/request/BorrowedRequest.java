package com.example.demo.model.request;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowedRequest {
    private int id;

    private int user_id;

    private List<Integer> borrowedItemsId;
}
