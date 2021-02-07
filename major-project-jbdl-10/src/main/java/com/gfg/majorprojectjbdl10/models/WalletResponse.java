package com.gfg.majorprojectjbdl10.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WalletResponse {

    Float balance;
    String username;

}
