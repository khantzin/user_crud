package com.kz.bookingsystem.repository;

import com.kz.bookingsystem.dto.projection.UserView;
import com.kz.bookingsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUseridAndPassword(String userid, String password);
    Optional<User> findUserByUserid(String userid);

    @Query(
            value = "select " +
                    "bu.name, " +
                    "bu.email, " +
                    "bc.name as 'countryName'" +
                    "from bs_user bu " +
                    "inner join bs_country bc on bc.id = bu.country_id " +
                    "where (LOWER(bu.name) LIKE CASE WHEN LENGTH(:name) > 0 THEN '%' || LOWER(:name) || '&' ELSE LOWER(bu.name) END) " +
                    "AND (LOWER(bu.email) LIKE CASE WHEN LENGTH(:email) > 0 THEN '%' || LOWER(:email) || '&' ELSE LOWER(bu.email) END) " +
                    "AND (LOWER(bc.name) LIKE CASE WHEN LENGTH(:countryName) > 0 THEN '%' || LOWER(:countryName) || '&' ELSE LOWER(bc.name) END)",
          nativeQuery = true
    )
    Page<UserView> findUser(String name, String email, String countryName, Pageable pageable);
}
