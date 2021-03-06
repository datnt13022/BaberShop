package com.DoAn.HairStyle.respositiry;

import com.DoAn.HairStyle.entity.TopHairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TopHairRespository extends JpaRepository<TopHairEntity, Long> {

}