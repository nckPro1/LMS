package com.example.lms.repository;

import com.example.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Tìm kiếm một người dùng dựa trên địa chỉ email.
     * Được sử dụng trong quá trình đăng nhập để lấy thông tin người dùng.
     *
     * @param email Địa chỉ email cần tìm.
     * @return một Optional chứa User nếu tìm thấy, ngược lại trả về Optional rỗng.
     */
    Optional<User> findByEmail(String email);

    /**
     * Kiểm tra xem một email đã tồn tại trong hệ thống hay chưa.
     * Được sử dụng để đảm bảo tính duy nhất của email khi tạo tài khoản mới.
     *
     * @param email Email cần kiểm tra.
     * @return true nếu email đã tồn tại, false nếu ngược lại.
     */
    boolean existsByEmail(String email);

    /**
     * Kiểm tra xem một mã định danh đã tồn tại trong hệ thống hay chưa.
     * Được sử dụng để đảm bảo tính duy nhất của mã định danh khi tạo tài khoản mới.
     *
     * @param identifierCode Mã định danh cần kiểm tra.
     * @return true nếu mã đã tồn tại, false nếu ngược lại.
     */
    boolean existsByIdentifierCode(String identifierCode);

}