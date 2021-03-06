package com.DoAn.HairStyle.respositiry;


import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.entity.OrderEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface  UserRespository extends JpaRepository<UserEntity, String> {
    UserEntity findByToken(String token);
    UserEntity findByNumberPhone(String numberPhone);
    @Transactional
    void  deleteByToken(String token);
}
