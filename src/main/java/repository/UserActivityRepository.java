package repository;

import model.UserActivity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    List<UserActivity> findByUserId(Integer userId);
}
