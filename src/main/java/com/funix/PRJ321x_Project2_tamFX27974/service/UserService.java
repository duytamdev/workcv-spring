package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User update(User user);

    User getCurrentUser();

    User updateUserAuthContext();

    void updateCompanyById(int id, int companyId);

    String updateAvatarById(int id, String avatar);

    List<User> searchByNameLike(String name);
}
