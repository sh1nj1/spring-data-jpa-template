package com.chk0ndanger.springframework.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chk0ndanger.springframework.entity.mysql.ExampleEntity;

/**
 * Example repository of Spring data repository.
 * @author soonoh
 */
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

    /**
     * example method that uses method name as query expression in spring data.
     * @param name
     * @return
     */
    List<ExampleEntity> findByNameLikeOrderByUpdatedAtDesc(String name);

    /**
     * example method that selects rows by name with named parametres.
     * @param name
     * @return
     */
    @Query("select e from ExampleEntity e where e.name = :name")
    List<ExampleEntity> findByName(@Param("name") String name);

    /**
     * example method that selects rows by name with like expression, see ordinal parametres starts with 1.
     * @param name
     * @return
     */
    @Query("select e from ExampleEntity e where e.name like %?1%")
    List<ExampleEntity> findByNameLike(String name);

    /**
     * example method that selects rows by age range with native query by turning nativeQuery property on.
     * @param low
     * @param high
     * @return
     */
    @Query(value = "select * from ExampleEntity "
            + "where age >= :low and age <= :high "
            + "order by updatedAt", nativeQuery = true)
    List<ExampleEntity> findByAgeRange(@Param("low") int low, @Param("high") int high);

    /**
     * example method that returns paged list filtered by name.
     * @param name
     * @param pageable use {@link org.springframework.data.domain.PageRequest}.
     * @return
     */
    @Query("select e from ExampleEntity e where e.name like %?1%")
    Page<ExampleEntity> listByName(String name, Pageable pageable);
}
