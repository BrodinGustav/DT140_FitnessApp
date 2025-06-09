package com.example.App.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

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

        var duration = Duration.ofSeconds(putUserActivity.getSeconds());                                    //Skapar ett Duration-objekt från antalet sekunder som angivits
        var userActivity = new UserActivity();                                                              //Skapar nytt userActivity-objekt med aktivitet, poäng och tid
        userActivity.setActivity(putUserActivity.getActivity());
        userActivity.setPoints((int) (putUserActivity.getActivity().getValue() * duration.toMinutes()));    //Räknar ut totala poängen för aktiviteten
        userActivity.setDuration(duration);                                                                 //Sätter tid för aktivitet
        userActivity.setDate(putUserActivity.getDate());

        System.out.println(putUserActivity);
        System.out.println(userActivity);

        user.addUserActivity(userActivity);                                                                 //Lägger till aktivitet i användares lista av aktiviteter

        userRepository.save(user); 
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
                            .filter(activity -> activity.getTimestamp().isAfter(now.toLocalDateTime())) //Filtrering av aktiviteter inom de senaste 7 dagarna
                            .mapToInt(UserActivity::getPoints)  //Hämtar redan uträknat värde från createUserActivity
                            .sum(); //Summerar samtliga poäng
                            System.out.println(String.format("%s, %s", user.getName(), totalPoints));
                    return new LeaderboardDTO(
                            user.getName(),
                            totalPoints, totalPoints);
                })
                
                .sorted(Comparator.comparingInt(LeaderboardDTO::getTotalPoints).reversed()) //Sorterar listan från högst till lägst
                .toList(); // Samla alla DTO:n i en lista och returnera den

    }

    public void deleteUserActivity(Integer id) {
        UserActivity activity = userActivityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aktivitet med id " + id + " finns inte."));
        userActivityRepository.delete(activity);
    }

}
