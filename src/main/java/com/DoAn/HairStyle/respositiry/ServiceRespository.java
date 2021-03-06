package com.DoAn.HairStyle.respositiry;

import com.DoAn.HairStyle.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ServiceRespository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findAllByType(String type);
}
