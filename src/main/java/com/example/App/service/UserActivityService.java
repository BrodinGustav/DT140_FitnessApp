package com.example.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.UserActivity;
import com.example.App.repository.UserActivityRepository;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;




    public UserActivity saveUserActivity(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);   //Fungerar även som update och create
    }

    public List<UserActivity> getAllUserActivities() {
        return userActivityRepository.findAll();
    }
    
    public UserActivity getUserActivityById(Integer id) {
        return userActivityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
    }

     public UserActivity updateUserActivity(Integer id, UserActivity userActivityDetails) {
        UserActivity userActivity = userActivityRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("UserActivity med id " + id + " hittades inte."));
        
        userActivity.setPoints(userActivityDetails.getPoints());
        
        return userActivityRepository.save(userActivity);
    }

    public void deleteUserActivity(Integer id) {
        userActivityRepository.deleteById(id);
    }
























     
/* 
    
    //Hämta UserActivity via ID
   public List<UserActivityDTO> getUserActivities(Integer userId) {
        List<UserActivity> userActivities = userActivityRepository.findByUserId(userId);

        // debugg
        System.out.println("Antal aktiviteter hittade för userId " + userId + ": " + userActivities.size());

        return userActivities.stream().map(userActivity -> {
            UserActivityDTO dto = new UserActivityDTO();
            dto.setId(userActivity.getId());
            dto.setUserId(userActivity.getUser().getId());
            dto.setActivityId(userActivity.getActivity().getId());
            dto.setPoints(userActivity.getPoints());

            // Hämta användarens namn
            String userName = userActivity.getUser().getName();
            dto.setUserName(userName); 

            // Hämta aktivitetens namn
            String activityName = userActivity.getActivity().getName(); 
            dto.setActivityName(activityName); 

            // Hämta category
            String category = userActivity.getActivity().getCategory().getName(); 
            dto.setCategory(category);

            return dto;
        }).collect(Collectors.toList());
    }


    

     //Skapa UserActivity
     public void addActivityForUser(UserActivityDTO userActivityDTO) {
        User user = userRepository.findById(userActivityDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Användare hittades ej"));
        Activity activity = activityRepository.findById(userActivityDTO.getActivityId())
                .orElseThrow(() -> new RuntimeException("Aktivitet hittades ej"));

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(activity);
        userActivity.setPoints(userActivityDTO.getPoints());

        userActivityRepository.save(userActivity);
    }


//CRUD utanför DTO som test ifall 401 försvinner

//GET
 /*   public List<UserActivity> getAllUserActivities() {
        return userActivityRepository.findAll();
    }

//GET {id}
public UserActivity getUserActivityById(Integer id) {
    return userActivityRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("UserActivity med id: " + id + "hittades ej."));
}

//DELETE
public void deleteUserActivity(Integer id) {
    userActivityRepository.deleteById(id);
}

//CREATE (Via DTO)
public UserActivity save(UserActivityDTO userActivityDTO) {
        
    //Skapar en UserActivity från UserActivityRequest
    UserActivity userActivity = new UserActivity();
    
    userActivity.setId(userActivityDTO.getId());

    return userActivityRepository.save(userActivity); //Sparar entiteten i databasen
}
*/


}
