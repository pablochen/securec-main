package com.securec.main.domain;


import com.securec.main.util.BooleanToYNConverter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="auth")
public class Auth extends BaseEntity{

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID authSeq;

    @Column(nullable = false)
    private String authCode;

    @Column
    private String authName;

    @Column
    private int authOrd;

    @Column(nullable = false)
    @ColumnDefault("'Y'")
    @Convert(converter= BooleanToYNConverter.class)
    private boolean useYn;


    /**
     * Auth:User = 1:N
     * 양방향 연관관계
     */
    @OneToMany(mappedBy = "auth")
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
        if (user.getAuth() != this) {
            user.setAuth(this);
        }
    }

    public void removeUser(User user) {
        this.getUsers().remove(this);
    }


    /**
     * Auth:AuthMenuGroup = 1:N
     * 양방향 연관관계 (Auth:MenuGroup = N:M)
     */
    @OneToMany(mappedBy = "auth")
    private List<AuthMenuGroup> authMenuGroups = new ArrayList<>();

    public List<AuthMenuGroup> getAuthMenuGroups() {
        return this.authMenuGroups;
    }

    public void addAuthMenuGroup(AuthMenuGroup authMenuGroup) {
        this.authMenuGroups.add(authMenuGroup);
        if (authMenuGroup.getAuth() != this) {
            authMenuGroup.setAuth(this);
        }
    }

    public void removeAuthMenuGroup(AuthMenuGroup authMenuGroup) {
        this.getAuthMenuGroups().remove(this);
    }

    /**
     * 기본생성자
     */
    @Builder
    public Auth(Auth auth) {
        this.authCode = auth.getAuthCode();
        this.authName = auth.getAuthName();
        this.authOrd = auth.getAuthOrd();
    }
}