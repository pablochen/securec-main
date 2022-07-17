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
@Table(name="menu_group")
public class MenuGroup extends BaseEntity{

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID menuGroupSeq;

    @Column(nullable = false)
    private String menuGroupCode;

    @Column
    private String menuGroupName;

    @Column
    private int menuGroupOrd;

    @Column(nullable = false)
    @ColumnDefault("'Y'")
    @Convert(converter= BooleanToYNConverter.class)
    private boolean useYn;


    /**
     * MenuGroup:Menu = 1:다
     * 양방향 연관관계
     */
    @OneToMany(mappedBy = "menuGroup")
    private List<Menu> menus = new ArrayList<Menu>();

    public List<Menu> getMenus() {
        return this.menus;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        if (menu.getMenuGroup() != this) {
            menu.setMenuGroup(this);
        }
    }

    public void removeMenu(Menu menu) {
        this.getMenus().remove(this);
    }

    /**
     * Auth:AuthMenuGroup = 1:N
     * 양방향 연관관계 (Auth:MenuGroup = N:M)
     */
    @OneToMany(mappedBy = "menuGroup")
    private List<AuthMenuGroup> authMenuGroups = new ArrayList<>();

    public List<AuthMenuGroup> getAuthMenuGroups() {
        return this.authMenuGroups;
    }

    public void addAuthMenuGroup(AuthMenuGroup authMenuGroup) {
        this.authMenuGroups.add(authMenuGroup);
        if (authMenuGroup.getMenuGroup() != this) {
            authMenuGroup.setMenuGroup(this);
        }
    }

    public void removeAuthMenuGroup(AuthMenuGroup authMenuGroup) {
        this.getAuthMenuGroups().remove(this);
    }


    /**
     * 기본생성자
     */
    @Builder
    public MenuGroup(MenuGroup menuGroup) {
        this.menuGroupCode = menuGroup.getMenuGroupCode();
        this.menuGroupName = menuGroup.getMenuGroupName();
        this.menuGroupOrd = menuGroup.getMenuGroupOrd();
    }
}