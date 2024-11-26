package com.example.JpaBoard_admin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields { // 자동으로 세팅할수 있게 하기 위해 setter를 추가 안한다

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)// 파싱이 잘 될수 있게 추가
    @CreatedDate
    @Column(nullable = false,updatable = false) //updatable = false는 업데이트가 불가 하다는 뜻이다
    protected LocalDateTime createdAt; // 생성일시

    @CreatedBy
    @Column(nullable = false,updatable = false ,length = 100) //updatable = false는 업데이트가 불가 하다는 뜻이다
    protected String createdBy; // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifiedAt; // 수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    protected String modifiedBy; // 수정자

}
