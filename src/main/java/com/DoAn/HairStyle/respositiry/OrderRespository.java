package com.DoAn.HairStyle.respositiry;

import com.DoAn.HairStyle.entity.OrderEntity;
import com.DoAn.HairStyle.entity.ServiceEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderRespository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUser(UserEntity user);
//    static void findByUser_Token(String token) {
//    }


    //   OrderEntity findByUser_Token(UserEntity user);


    //   OrderEntity findByUser_Token(UserEntity user);
List<OrderEntity> findAllByTimeBookedBetween(Date dateStart, Date dateEnd);

//    Optional<OrderEntity> findByTimeBookedAndAndUser(Date timeBooked,UserEntity user);

//    Optional<OrderEntity> findAllTimeBookedBetween(UserEntity userEntity,Date fromDate,Date toDate);


//List<Student> findByTeacher_TeacherId(String teacherId);
//List<OrderEntity> findAll();
}
