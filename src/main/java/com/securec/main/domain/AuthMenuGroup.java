package com.securec.main.domain;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="auth_menu_group")
public class AuthMenuGroup extends BaseEntity{

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID authMenuGroupSeq;

    /**
     * AuthMenuGroup:Auth = N:1
     * 양방향 연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_code")
    private Auth auth;

    public Auth getAuth() {
        return this.auth;
    }

    public void setAuth(Auth auth) {
        if(this.auth != null) {
            this.auth.removeAuthMenuGroup(this);
        }
        this.auth = auth;
        if(!this.containsAuth(auth)) {
            auth.addAuthMenuGroup(this);
        }
    }

    public boolean containsAuth(Auth auth) {
        return auth.getAuthMenuGroups().contains(this);
    }

    /**
     * AuthMenuGroup:MenuGroup = N:1
     * 양방향 연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_group_code")
    private MenuGroup menuGroup;

    public MenuGroup getMenuGroup() {
        return this.menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        if(this.menuGroup != null) {
            this.menuGroup.removeAuthMenuGroup(this);
        }
        this.menuGroup = menuGroup;
        if(!this.containsMenuGroup(menuGroup)) {
            menuGroup.addAuthMenuGroup(this);
        }
    }

    public boolean containsMenuGroup(MenuGroup menuGroup) {
        return menuGroup.getAuthMenuGroups().contains(this);
    }

    /**
     * 기본생성자
     */
    @Builder
    public AuthMenuGroup(AuthMenuGroup authMenuGroup) {
        this.auth = authMenuGroup.getAuth();
        this.menuGroup = authMenuGroup.getMenuGroup();
    }
}