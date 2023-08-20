package com.bengohub.springcrud.repository;

import com.bengohub.springcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByAgeGreaterThanAndAddressContaining(Integer age, String address);
    @Query("SELECT u FROM User u WHERE u.age >= :minAge AND u.address LIKE %:search%")
    List<User> findUsersByAgeAndAddress(@Param("minAge") Integer minAge, @Param("search") String search);
    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR u.name = :name) AND " +
            "(:age IS NULL OR u.age = :age) AND " +
            "(:address IS NULL OR u.address = :address) AND " +
            "(:deleted IS NULL OR u.deleted = :deleted)")
    List<User> findByFilters(
            String name,
            Integer age,
            String address,
            Boolean deleted
    );
}
