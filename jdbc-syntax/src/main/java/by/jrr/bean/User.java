package by.jrr.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String login;
    private String name;
    private String bio;
    private byte[] image;
    private LocalDate birth;
    private LocalDateTime registered;
    private LocalDateTime updated;
    private Character gender;
    private Double credit;
    private Boolean active;
    private int likes;
}
