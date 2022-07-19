package com.securec.main.serviceImpl;


import com.securec.main.domain.Menu;
import com.securec.main.repository.MenuRepository;
import com.securec.main.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * 메뉴 상태변환 서비스
     */
    @Override
    public Menu useMenu(String menuCode){
        final Optional<Menu> fetchedMenu = menuRepository.findByMenuCode(menuCode);
        if(fetchedMenu.isPresent()){
            fetchedMenu.get().use();
            return menuRepository.save(fetchedMenu.get());
        }
        else return null;
    }

    @Override
    public Menu unUseMenu(String menuCode){
        final Optional<Menu> fetchedMenu = menuRepository.findByMenuCode(menuCode);
        if(fetchedMenu.isPresent()){
            fetchedMenu.get().unUse();
            return menuRepository.save(fetchedMenu.get());
        }
        else return null;
    }

}