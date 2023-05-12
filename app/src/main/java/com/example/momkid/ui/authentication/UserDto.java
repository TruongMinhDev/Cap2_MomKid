package com.example.momkid.ui.authentication;

public class UserDto {
    private static int id;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String role;


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserDto.id = id;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        UserDto.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        UserDto.lastName = lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserDto.email = email;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserDto.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
