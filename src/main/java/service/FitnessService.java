package service;

import model.Activity;
import model.User;
import model.UserActivity;
import repository.ActivityRepository;
import repository.UserActivityRepository;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserActivityDTO;

@Service
public class FitnessService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    public void addActivityForUser(UserActivityDTO userActivityDTO) {
        User user = userRepository.findById(userActivityDTO.getUserId()).orElseThrow(() -> new RuntimeException("Användare hittades ej"));
        Activity activity = activityRepository.findById(userActivityDTO.getActivityId()).orElseThrow(() -> new RuntimeException("Aktivitet hittades ej"));

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(activity);
        userActivity.setPoints(userActivityDTO.getPoints());

        userActivityRepository.save(userActivity);
    }

    public List<UserActivityDTO> getUserActivities(Integer userId) {
        List<UserActivity> userActivities = userActivityRepository.findByUserId(userId);
        return userActivities.stream().map(userActivity -> {
            UserActivityDTO dto = new UserActivityDTO();
            dto.setId(userActivity.getId());
            dto.setUserId(userActivity.getUser().getId());
            dto.setActivityId(userActivity.getActivity().getId());
            dto.setPoints(userActivity.getPoints());
            return dto;
        }).collect(Collectors.toList());
    }
}
