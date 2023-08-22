package com.speical.project.data.entity;


import com.speical.project.global.common.entity.BaseEntity;
import com.speical.project.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "data")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class DataEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @OneToOne(mappedBy = "data", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity user;

    @Column(name = "status_1")
    @ColumnDefault("10")
    private int status_1;

    @Column(name = "status_2")
    @ColumnDefault("10")
    private int status_2;

    @Column(name = "status_3")
    @ColumnDefault("10")
    private int status_3;


    @Column(name = "money")
    @ColumnDefault("0")
    private int money;

    @Column(name = "win_count")
    @ColumnDefault("0")
    private int winCount;

    @Column(name = "lose_count")
    @ColumnDefault("0")
    private int loseCount;

}
