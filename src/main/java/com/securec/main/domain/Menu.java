package com.securec.main.domain;


import com.securec.main.util.BooleanToYNConverter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="menu")
public class Menu extends BaseEntity{

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID menuSeq;

    @Column(nullable = false)
    private String menuCode;

    @Column(nullable = false)
    private String menuName;

    @Column
    private int menuOrd;

    @Column(nullable = false)
    @ColumnDefault("'Y'")
    @Convert(converter= BooleanToYNConverter.class)
    private boolean useYn;

    /**
     * Menu:MenuGroup = 다:1
     * 양방향 연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuGroupCode")
    private MenuGroup menuGroup;

    public MenuGroup getMenuGroup() {
        return this.menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        if(this.menuGroup != null) {
            this.menuGroup.removeMenu(this);
        }
        this.menuGroup = menuGroup;
        if(!containsMenuGroup(menuGroup)) {
            menuGroup.addMenu(this);
        }
    }

    public boolean containsMenuGroup(MenuGroup menuGroup) {
        return menuGroup.getMenus().contains(this);
    }

    /**
     * 기본생성자
     */
    @Builder
    public Menu(Menu menu) {
        this.menuCode = menu.getMenuCode();
        this.menuName = menu.getMenuName();
        this.menuOrd = menu.getMenuOrd();
    }

}