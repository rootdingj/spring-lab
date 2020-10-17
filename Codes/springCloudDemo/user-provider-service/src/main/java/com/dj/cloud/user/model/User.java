package com.dj.cloud.user.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: steven
 * @Date: 2020/10/5
 * @Description: 客户实体类
 */
@Entity
@Table(name = "T_USER")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
    @UpdateTimestamp
    @Column
    private Date updateTime;

}
