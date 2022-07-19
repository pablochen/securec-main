package com.securec.main.domain;


import com.securec.main.util.BooleanToYNConverter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalTime.now;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User extends BaseEntity{

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userSeq;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column
    private String userName;

    @Column
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int passwordErrorCount;

    @Column(nullable = false)
    @ColumnDefault("'N'")
    @Convert(converter= BooleanToYNConverter.class)
    private boolean lockYn;

    @Column
    private LocalDateTime lockedAt;

    @Column(nullable = false)
    @ColumnDefault("'N'")
    @Convert(converter= BooleanToYNConverter.class)
    private boolean dormantYn;

    @Column
    private LocalDateTime dormantAt;

    @Column(nullable = false)
    @ColumnDefault("'N'")
    @Convert(converter= BooleanToYNConverter.class)
    private boolean leaveYn;

    @Column
    private LocalDateTime leftAt;

    /**
     * User:Auth = 다:1
     * 양방향 연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authCode")
    private Auth auth;

    public Auth getAuth() {
        return this.auth;
    }

    public void setAuth(Auth auth) {
        if(this.auth != null) {
            this.auth.removeUser(this);
        }
        this.auth = auth;
        if(!this.containsAuth(auth)) {
            auth.addUser(this);
        }
    }

    public boolean containsAuth(Auth auth) {
        return auth.getUsers().contains(this);
    }

    /**
     * 기본생성자
     */
    @Builder
    public User(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
        this.password = user.getPassword();
    }

    /**
     * isNull 메소드
     */
    public boolean userNameIsNull() {
        return this.userName == null ? true : false;
    }

    public boolean nickNameIsNull() {
        return this.nickName == null ? true : false;
    }

    public boolean authIsNull() {
        return this.auth == null ? true : false;
    }

    /**
     * 유저 상태 전환 메소드
     */
    public boolean lock() {
        try {
            this.setLockYn(true);
            this.setLockedAt(LocalDateTime.now());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean unLock() {
        try {
            this.setLockYn(false);
            this.setPasswordErrorCount(0);
            this.setLockedAt(null);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean dormant() {
        try {
            this.setDormantYn(true);
            this.setDormantAt(LocalDateTime.now());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean unDormant() {
        try {
            this.setDormantYn(false);
            this.setDormantAt(null);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean leave() {
        try {
            this.setLeaveYn(true);
            this.setLeftAt(LocalDateTime.now());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

}