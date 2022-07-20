package com.securec.main.serviceImpl;


import com.securec.main.domain.Menu;
import com.securec.main.repository.MenuRepository;
import com.securec.main.service.MenuCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuCrudServiceImpl implements MenuCrudService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public boolean createMenu(Menu menu){
        try{
            menuRepository.save(menu);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public Optional<Menu> findMenuByMenuCode(String menuCode){
        return menuRepository.findByMenuCode(menuCode);
    }

    @Override
    public Optional<List<Menu>> findAllMenusByMenuCode(String menuCode){
        return menuRepository.findAllByMenuCodeContains(menuCode);
    }

    @Override
    public Optional<Menu> findMenuByMenuName(String menuName){
        return menuRepository.findByMenuName(menuName);
    }

    @Override
    public Optional<List<Menu>> findAllMenusByMenuName(String menuName){
        return menuRepository.findAllByMenuNameContains(menuName);
    }

    @Override
    public Menu updateMenu(String menuCode, Menu menu){
        final Optional<Menu> fetchedMenu = menuRepository.findByMenuCode(menuCode);
        if(fetchedMenu.isPresent()){
            menu.setMenuCode(menuCode);
            return menuRepository.save(menu);
        }
        else return null;
    }

    @Override
    public Menu patchMenu(String menuCode, Menu menu){
        final Optional<Menu> fetchedMenu = menuRepository.findByMenuCode(menuCode);
        if(fetchedMenu.isPresent()){
            if(!menu.menuOrdIsNull()){
                fetchedMenu.get().setMenuOrd(menu.getMenuOrd());
            }
            if(!menu.menuNameIsNull()){
                fetchedMenu.get().setMenuName(menu.getMenuName());
            }
            return menuRepository.save(fetchedMenu.get());
        }
        else return null;
    }

}