package com.securec.main.service;


import com.securec.main.domain.MenuGroup;
import com.securec.main.repository.MenuGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MenuGroupCrudService {
    public boolean createMenuGroup(MenuGroup MenuGroup);
    public Optional<MenuGroup> getMenuGroupByMenuGroupCode(String MenuGroupCode);
    public Optional<List<MenuGroup>> getMenuGroupsByMenuGroupCode(String MenuGroupCode);
    public MenuGroup updateMenuGroup(String MenuGroupCode, MenuGroup MenuGroup);
    public MenuGroup patchMenuGroup(String MenuGroupCode, MenuGroup MenuGroup);
}