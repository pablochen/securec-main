package com.securec.main.service;


import com.securec.main.domain.Menu;
import com.securec.main.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MenuCrudService {
    public boolean createMenu(Menu menu);
    public Optional<Menu> getMenuByMenuCode(String menuCode);
    public Optional<List<Menu>> getAllMenusByMenuCode(String menuCode);
    public Optional<Menu> getMenuByMenuName(String menuName);
    public Optional<List<Menu>> getAllMenusByMenuName(String menuName);
    public Menu updateMenu(String menuCode, Menu menu);
    public Menu patchMenu(String menuCode, Menu menu);

}