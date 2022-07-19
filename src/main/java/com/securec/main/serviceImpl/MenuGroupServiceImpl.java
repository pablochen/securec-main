package com.securec.main.serviceImpl;


import com.securec.main.domain.MenuGroup;
import com.securec.main.repository.MenuGroupRepository;
import com.securec.main.service.MenuGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuGroupServiceImpl implements MenuGroupService {

    @Autowired
    private MenuGroupRepository menuGroupRepository;

    /**
     * 메뉴그룹 상태전환 서비스
     */
    public MenuGroup useMenuGroup(String MenuGroupCode){
        final Optional<MenuGroup> fetchedMenuGroup = menuGroupRepository.findByMenuGroupCode(MenuGroupCode);
        if(fetchedMenuGroup.isPresent()){
            fetchedMenuGroup.get().use();
            return menuGroupRepository.save(fetchedMenuGroup.get());
        }
        else return null;
    }

    public MenuGroup unUseMenuGroup(String MenuGroupCode){
        final Optional<MenuGroup> fetchedMenuGroup = menuGroupRepository.findByMenuGroupCode(MenuGroupCode);
        if(fetchedMenuGroup.isPresent()){
            fetchedMenuGroup.get().unUse();
            return menuGroupRepository.save(fetchedMenuGroup.get());
        }
        else return null;
    }
}