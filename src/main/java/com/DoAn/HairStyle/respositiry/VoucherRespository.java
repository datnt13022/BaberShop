package com.DoAn.HairStyle.respositiry;

import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VoucherRespository extends JpaRepository<VoucherEntity, String> {
//    VoucherEntity findByVoucherCode(String voucherCode);
//    VoucherEntity findByToken(String token);
//    private String voucherCode;

}
