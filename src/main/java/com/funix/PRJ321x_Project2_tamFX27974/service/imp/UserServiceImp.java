package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Role;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.repository.RoleRepository;
import com.funix.PRJ321x_Project2_tamFX27974.repository.UserRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import com.funix.PRJ321x_Project2_tamFX27974.utils.ErrorException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private final RoleRepository roleRepository;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        return null;
    }

    @Override
    public User updateUserAuthContext() {
        User user = userRepository.findById(getCurrentUser().getId()).orElseThrow(() -> new ErrorException("User not found"));
        // Cập nhật thông tin người dùng trong Security Context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken updatedAuthentication = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
        return user;
    }

    @Override
    @Transactional
    public void updateCompanyById(int id, int companyId) {
        userRepository.updateCompanyById(id, companyId);
    }

    @Override
    @Transactional
    public String updateAvatarById(int id, String avatar) {
        userRepository.updateAvatarById(id, avatar);
        updateUserAuthContext();
        // return path to avatar
        return "/assets/user-images/" + avatar;
    }

    @Override
    public List<User> searchByNameLike(String name) {
        return userRepository.searchByNameLike(name);
    }

    @Override
    public void register(User user) {
        // Kiểm tra xem role_id có tồn tại không
        Optional<Role> roleOptional = roleRepository.findById(user.getRole().getId());
        if (roleOptional.isPresent()) {
            // Set Role cho User và lưu vào cơ sở dữ liệu
            user.setRole(roleOptional.get());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            // Xử lý khi role không tồn tại
            throw new ErrorException("Role not found");
        }
    }

    @Override
    public User update(User userUpdate) {
        User user = userRepository.findById(userUpdate.getId()).orElseThrow(() -> new ErrorException("User not found"));
        user.setEmail(userUpdate.getEmail());
        user.setFullName(userUpdate.getFullName());
        user.setAddress(userUpdate.getAddress());
        user.setPhoneNumber(userUpdate.getPhoneNumber());
        user.setSelfDescription(userUpdate.getSelfDescription());
        User userAfterUpdate = userRepository.save(user);

        this.updateUserAuthContext();
        return userAfterUpdate;
    }


}
