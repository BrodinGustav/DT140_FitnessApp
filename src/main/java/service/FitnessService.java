package service;

import model.Activity;
import model.User;
import model.UserActivity;
import repository.ActivityRepository;
import repository.UserActivityRepository;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    public void addActivityForUser(Integer userId, Integer activityId, int points) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Användare hittades ej"));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Aktivitet hittades ej"));

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(activity);
        userActivity.setPoints(points);

        userActivityRepository.save(userActivity);
    }
}
