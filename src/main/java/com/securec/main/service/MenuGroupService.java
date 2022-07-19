package com.securec.main.service;


import com.securec.main.domain.MenuGroup;
import com.securec.main.repository.MenuGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MenuGroupService {
    public MenuGroup useMenuGroup(String MenuGroupCode);
    public MenuGroup unUseMenuGroup(String MenuGroupCode);
}