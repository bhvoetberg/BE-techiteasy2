package com.example.demo.repositories;

import com.example.demo.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Integer> {

//    Iterable<Television> findAllBy(String name);
    Iterable<Television> findAllByName(String name);


//    @Query("SELECT b FROM Television b WHERE b.name LIKE %:s%")
//    Iterable<Television> searchByName(@Param("s") String s);

}