package com.example.App.service;

import java.time.Duration;
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

        userRepository.save(user);   //Fungerar även som update och create
    }
    
    @Override
    public UserActivity getUserActivityById(Integer id) {
        return userActivityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
    }

//Metod för att hämta användares poäng, namn och aktivitet samt tid för utskrift till Leaderboard på fronten
@Transactional(readOnly = true)
public List<LeaderboardDTO> totalResultat() {
    return userRepository.findAll().stream()  // Hämta alla användare
        .flatMap(user -> user.getActivities().stream()  // Platta ut aktiviteter för varje användare
            .map(userActivity -> new LeaderboardDTO(
                user.getName(),  // Hämta användarens namn
                userActivity.getActivity().toString(),  // Hämta aktivitetens namn
                userActivity.getPoints(),  // Hämta poäng
                (int) userActivity.getDuration().toMinutes()  // Hämta duration i minuter (om du vill ha det)
            ))
        )
        .collect(Collectors.toList());  // Samla alla DTO:n i en lista och returnera den
}




    /* 
    @Override
     public UserActivity updateUserActivity(Integer id, CreateUserActivityDTO userActivity) {
        UserActivity userActivity = userActivityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
        
        userActivity.setPoints(userActivityDetails.getPoints());
        
        return userActivityRepository.save(userActivity);
    }
*/


    @Override
    public void deleteUserActivity(Integer id) {
        userActivityRepository.deleteById(id);
    }


}
