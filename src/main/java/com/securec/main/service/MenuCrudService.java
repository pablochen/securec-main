package com.securec.main.service;


import com.securec.main.domain.Menu;
import com.securec.main.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MenuCrudService {
    public boolean createMenu(Menu menu);
    public Optional<Menu> findMenuByMenuCode(String menuCode);
    public Optional<List<Menu>> findAllMenusByMenuCode(String menuCode);
    public Optional<Menu> findMenuByMenuName(String menuName);
    public Optional<List<Menu>> findAllMenusByMenuName(String menuName);
    public Menu updateMenu(String menuCode, Menu menu);
    public Menu patchMenu(String menuCode, Menu menu);

}