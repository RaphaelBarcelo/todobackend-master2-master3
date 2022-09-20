package com.example.todobackend.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface TodoRepository extends CrudRepository<TodoEntity, Integer> {

    @Query(value = "select * from TodoEntity t where t.id = :id", nativeQuery = true)
    Optional<TodoEntity> findById(UUID uuid);

    @Modifying
    @Query("delete from TodoEntity t where t.completed = :completed")
    void deleteByCompleted(Boolean completed);

    @Modifying
    @Query("delete from TodoEntity t where t.id = :id")
    void deleteById(UUID id);

    @Query("select MAX(order) from TodoEntity")
    int getMaxOrder();
}

