package com.securec.main.service;


import com.securec.main.domain.Menu;
import com.securec.main.domain.User;
import com.securec.main.repository.MenuRepository;
import com.securec.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    public Menu useMenu(String menuCode);
    public Menu unUseMenu(String menuCode);

}