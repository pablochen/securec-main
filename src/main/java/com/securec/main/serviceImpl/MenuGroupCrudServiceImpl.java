package com.securec.main.serviceImpl;


import com.securec.main.domain.MenuGroup;
import com.securec.main.repository.MenuGroupRepository;
import com.securec.main.service.MenuGroupCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuGroupCrudServiceImpl implements MenuGroupCrudService {

    @Autowired
    private MenuGroupRepository menuGroupRepository;

    @Override
    public boolean createMenuGroup(MenuGroup MenuGroup){
        try{
            menuGroupRepository.save(MenuGroup);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public Optional<MenuGroup> findMenuGroupByMenuGroupCode(String MenuGroupCode){
        return menuGroupRepository.findByMenuGroupCode(MenuGroupCode);
    }

    @Override
    public Optional<List<MenuGroup>> findMenuGroupsByMenuGroupCode(String MenuGroupCode){
        return menuGroupRepository.findAllByMenuGroupCodeContains(MenuGroupCode);
    }

    @Override
    public MenuGroup updateMenuGroup(String MenuGroupCode, MenuGroup MenuGroup){
        final Optional<MenuGroup> fetchedMenuGroup = menuGroupRepository.findByMenuGroupCode(MenuGroupCode);
        if(fetchedMenuGroup.isPresent()){
            MenuGroup.setMenuGroupCode(MenuGroupCode);
            return menuGroupRepository.save(MenuGroup);
        }
        else return null;
    }

    @Override
    public MenuGroup patchMenuGroup(String MenuGroupCode, MenuGroup MenuGroup){
        final Optional<MenuGroup> fetchedMenuGroup = menuGroupRepository.findByMenuGroupCode(MenuGroupCode);
        if(fetchedMenuGroup.isPresent()){
            if(!MenuGroup.menuGroupOrdIsNull()){
                fetchedMenuGroup.get().setMenuGroupName(MenuGroup.getMenuGroupName());
            }
            if(!MenuGroup.menuGroupNameIsNull()){
                fetchedMenuGroup.get().setMenuGroupName(MenuGroup.getMenuGroupName());
            }
            if(!MenuGroup.menuIsNull()){
                fetchedMenuGroup.get().setMenus(MenuGroup.getMenus());
            }
            return menuGroupRepository.save(fetchedMenuGroup.get());
        }
        else return null;
    }

}