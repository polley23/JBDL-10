package com.gfg.majorprojectjbdl10.models;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    String user;
    String message;
    String subject;
}
