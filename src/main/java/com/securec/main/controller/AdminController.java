package com.securec.main.controller;

import com.securec.main.domain.*;
import com.securec.main.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restful/api")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthCrudService authCrudService;

    @Autowired
    private MenuGroupCrudService menuGroupCrudService;

    @Autowired
    private MenuCrudService menuCrudService;

    @Autowired
    private AuthMenuGroupCrudService authMenuGroupCrudService;



    /**
     * 1. 회원 메소드
     */
    // 1.회원 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        Optional<User> user = userCrudService.findUserByUserId(userId);
        if(user.isPresent()){
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    // 1-2. 회원 리스트 조회
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList(@PathVariable String userId) {
        Optional<List<User>> userList = userCrudService.findUsersByUserId(userId);
        if(userList.isPresent()){
            return new ResponseEntity<List<User>>(userList.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
    }

    // 1-3. 회원 권한 수정
    @PatchMapping("/user/{id}/auth")
    public ResponseEntity<User> patchUserAuth(@PathVariable String userId, @RequestBody String authCode) {
        Auth auth = authCrudService.getAuthByAuthCode(authCode).get();
        User updateUser = userService.patchUserAuth(userId, auth);
        if(updateUser != null){
            return new ResponseEntity<User>(updateUser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 2. 권한 메소드
     */
    // 2-1. 권한 생성
    @PostMapping("/auth")
    public ResponseEntity<Void> createAuth(@RequestBody Auth auth) {
        boolean flag = authCrudService.createAuth(auth);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    // 2-2. 권한 조회
    @GetMapping("/auths/{authCode}")
    public ResponseEntity<Auth> getAuth(@PathVariable String authCode) {
        Optional<Auth> auth = authCrudService.findAuthByAuthCode(authCode);
        if(auth.isPresent()){
            return new ResponseEntity<Auth>(auth.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Auth>(HttpStatus.NOT_FOUND);
        }
    }

    // 2-3. 권한 수정
    @PatchMapping("/auths/{authCode}")
    public ResponseEntity<Auth> patchAuth(@PathVariable String authCode, @RequestBody Auth auth) {
        Auth updateAuth = authCrudService.patchAuth(authCode, auth);
        if(updateAuth != null){
            return new ResponseEntity<Auth>(updateAuth, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Auth>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 3. 메뉴그룹 메소드
     */
    // 3-1. 메뉴그룹 생성
    @PostMapping("/menuGroup")
    public ResponseEntity<Void> createMenuGroup(@RequestBody MenuGroup menuGroup) {
        boolean flag = menuGroupCrudService.createMenuGroup(menuGroup);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    // 3-2. 메뉴그룹 조회
    @GetMapping("/menuGroup/{menuGroupCode}")
    public ResponseEntity<MenuGroup> getMenuGroup(@PathVariable String menuGroupCode) {
        Optional<MenuGroup> menuGroup = menuGroupCrudService.findMenuGroupByMenuGroupCode(menuGroupCode);
        if(menuGroup.isPresent()){
            return new ResponseEntity<MenuGroup>(menuGroup.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MenuGroup>(HttpStatus.NOT_FOUND);
        }
    }

    // 3-3. 메뉴그룹 수정
    @PatchMapping("/menuGroup/{menuGroupCode}")
    public ResponseEntity<MenuGroup> patchMenuGroup(@PathVariable String menuGroupCode, @RequestBody MenuGroup menuGroup) {
        MenuGroup updateMenuGroup = menuGroupCrudService.patchMenuGroup(menuGroupCode, menuGroup);
        if(updateMenuGroup != null){
            return new ResponseEntity<MenuGroup>(updateMenuGroup, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MenuGroup>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 4. 메뉴 메소드
     */
    // 4-1. 메뉴 생성
    @PostMapping("/menu")
    public ResponseEntity<Void> createMenu(@RequestBody Menu menu) {
        boolean flag = menuCrudService.createMenu(menu);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    // 4-2. 메뉴 조회
    @GetMapping("/menu/{menuCode}")
    public ResponseEntity<Menu> getMenu(@PathVariable String menuCode) {
        Optional<Menu> menu = menuCrudService.findMenuByMenuCode(menuCode);
        if(menu.isPresent()){
            return new ResponseEntity<Menu>(menu.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
        }
    }

    // 4-3. 메뉴 수정
    @PatchMapping("/menuGroup/{menuGroupCode}")
    public ResponseEntity<Menu> patchMenuGroup(@PathVariable String menuCode, @RequestBody Menu menu) {
        Menu updateMenu = menuCrudService.patchMenu(menuCode, menu);
        if(updateMenu != null){
            return new ResponseEntity<Menu>(updateMenu, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 4. 권한-메뉴그룹 메소드
     */
    // 4-1. 권한-메뉴그룹 생성
    @PostMapping("/authMenuGroup")
    public ResponseEntity<AuthMenuGroup> createAuthMenuGroup(@RequestBody String authCode, @RequestBody String menuGroupCode) {
        Auth auth = authCrudService.getAuthByAuthCode(authCode).get();
        MenuGroup menuGroup = menuGroupCrudService.getMenuGroupByMenuGroupCode(menuGroupCode).get();
        boolean flag = authMenuGroupCrudService.createAuthMenuGroup(auth, menuGroup);
        if (flag == false) {
            return new ResponseEntity<AuthMenuGroup>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<AuthMenuGroup>(HttpStatus.CREATED);
    }

    // 4-2. 권한-메뉴그룹 삭제
    @DeleteMapping("/authMenuGroup/{authCode}")
    public ResponseEntity<AuthMenuGroup> deleteAuthMenuGroup(@PathVariable String authCode, @PathVariable String menuGroupCode) {
        Auth auth = authCrudService.getAuthByAuthCode(authCode).get();
        MenuGroup menuGroup = menuGroupCrudService.getMenuGroupByMenuGroupCode(menuGroupCode).get();
        boolean flag = authMenuGroupCrudService.deleteAuthMenuGroupByAuthAndMenuGroup(auth, menuGroup);
        if (flag == false) {
            return new ResponseEntity<AuthMenuGroup>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AuthMenuGroup>(HttpStatus.OK);
    }




}