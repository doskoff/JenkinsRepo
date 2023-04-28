package models;

import lombok.Data;

@Data
public class UserForId {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}