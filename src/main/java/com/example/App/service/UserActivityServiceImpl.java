package com.example.App.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.App.dto.CreateUserActivityDTO;
import com.example.App.dto.LeaderboardDTO;
import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.UserActivity;
import com.example.App.repository.UserActivityRepository;
import com.example.App.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UserActivityServiceImpl implements UserActivityService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Override
    public void createUserActivity(CreateUserActivityDTO putUserActivity) {

        var user = userRepository.findById(putUserActivity.getUserId()).orElseThrow();

        var userActivity = new UserActivity();
        userActivity.setActivity(putUserActivity.getActivity());
        userActivity.setPoints(100);
        userActivity.setDuration(Duration.ofSeconds(putUserActivity.getSeconds()));

        System.out.println(putUserActivity);
        System.out.println(userActivity);

        user.addUserActivity(userActivity);

        userRepository.save(user); // Fungerar även som update och create
    }

    @Override
    public UserActivity getUserActivityById(Integer id) {
        return userActivityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
    }

    // Metod för att hämta användares poäng, namn och aktivitet samt tid för
    // utskrift till Leaderboard på fronten
    @Transactional(readOnly = true)
    public List<LeaderboardDTO> totalResultat() {
        var now = OffsetDateTime.now().minusDays(7);
        var userList = userRepository.findAll();
        
        return userList.stream()
                .map(user -> {
                    int totalPoints = user.getActivities().stream()
                            .filter(activity -> activity.getTimestamp().compareTo(now.toLocalDateTime()) > 1) //Filtrering av aktiviteter inom de senaste 7 dagarna
                            .mapToInt(a -> (int) (((long) a.getPoints()) * a.getDuration().toMinutes()))
                            .sum();
                            System.out.println(String.format("%s, %s", user.getName(), totalPoints));
                    return new LeaderboardDTO(
                            user.getName(),
                            totalPoints, totalPoints);
                })
                
                .sorted(Comparator.comparingInt(LeaderboardDTO::getTotalPoints).reversed())
                .toList(); // Samla alla DTO:n i en lista och returnera den

    }

    /*
     * @Transactional
     * public UserActivity updateUserActivity(Integer id, CreateUserActivityDTO
     * putUserActivity) {
     * UserActivity userActivity = userActivityRepository.findById(id)
     * .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id
     * + " hittades inte."));
     * 
     * userActivity.setActivity(putUserActivity.getActivity());
     * userActivity.setPoints(100);
     * userActivity.setDuration(Duration.ofSeconds(putUserActivity.getSeconds()));
     * 
     * return userActivityRepository.save(userActivity);
     * }
     */

    @Override
    public void deleteUserActivity(Integer id) {
        userActivityRepository.deleteById(id);
    }

}
