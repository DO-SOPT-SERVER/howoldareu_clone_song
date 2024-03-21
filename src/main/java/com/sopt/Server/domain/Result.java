package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESULTS")
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @ManyToOne
    private Member member;

    private int resultAge;

    @CreatedDate
    private LocalDateTime testedDate;

    @Builder
    public Result(Long id, Member member, int resultAge, LocalDateTime testedDate) {
        this.id = id;
        this.member = member;
        this.resultAge = resultAge;
        this.testedDate = testedDate;
    }
}
