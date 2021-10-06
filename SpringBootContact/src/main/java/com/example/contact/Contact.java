package com.example.contact;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Contact {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;
}
