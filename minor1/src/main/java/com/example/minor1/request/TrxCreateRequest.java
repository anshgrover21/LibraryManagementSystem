package com.example.minor1.request;


import com.example.minor1.model.TrxStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrxCreateRequest {

  private Long studentContact;

      private String bookNo;

    private Integer paidAmnt;


}
