package com.sopt.Server.domain;

import static com.sopt.Server.exception.Error.INVALID_AGE;
import static com.sopt.Server.exception.Error.INVALID_NAME;

import com.sopt.Server.exception.model.CustomException;
import jakarta.persistence.*;
import java.util.regex.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBERS")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    private String name;

    private int realAge;

    @Builder
    public Member(String name, int realAge) {
        validateName(name);
        validateRealAge(realAge);

        this.name = name;
        this.realAge = realAge;
    }

    private void validateRealAge(int realAge){
        if(realAge < 20 || realAge > 80){
            throw new CustomException(INVALID_AGE, INVALID_AGE.getMessage());
        }
    }

    private void validateName(String name){
        if(!Pattern.matches("^[가-힣\\s]*$", name)){
            throw new CustomException(INVALID_NAME, INVALID_NAME.getMessage());
        }
    }
}
