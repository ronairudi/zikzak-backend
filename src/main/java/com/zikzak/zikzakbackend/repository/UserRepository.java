package com.zikzak.zikzakbackend.repository;

import com.zikzak.zikzakbackend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
