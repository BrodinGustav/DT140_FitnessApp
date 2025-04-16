package com.example.App.service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.App.dto.UpdateUserDTO;
import com.example.App.dto.WeeklyActivityPointsDTO;
import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.Activity;
import com.example.App.model.User;
import com.example.App.model.UserActivity;
import com.example.App.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

       @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Inga användare hittades.");
        }
        return users;
    }

    @Override
    public User updateUser(Integer id, UpdateUserDTO userDetails) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));

        userDetails.getEmail()
            .ifPresent(user::setEmail);
        userDetails.getName()
            .ifPresent(user::setName);;
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " finns inte."));
    userRepository.delete(user);
    }



    @Transactional(readOnly = true)
    public List<WeeklyActivityPointsDTO> getWeeklyPointsByActivityForUser(int id) {
    
        // Definiera den tidsperiod (de senaste 7 dagarna)
        OffsetDateTime now = OffsetDateTime.now().minusDays(7);
    
        // Hämta användaren från databasen
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
    
        // Gruppera aktiviteter efter aktivitetstyp och summera poäng baserat på varaktigheten
        Map<Activity, Integer> activityToPoints = user.getActivities().stream()
            .filter(activity -> activity.getTimestamp().compareTo(now.toLocalDateTime()) > 0) // Endast senaste 7 dagar
            .collect(Collectors.groupingBy(
                UserActivity::getActivity,
                Collectors.summingInt(a -> (int) (((long) a.getPoints()) * a.getDuration().toMinutes()))
            ));
    
        // Skapa en lista av WeeklyActivityPointsDTO
        return activityToPoints.entrySet().stream()
            .map(entry -> new WeeklyActivityPointsDTO(id, entry.getKey(), entry.getValue()))  // Lägg till id för användare här
            .sorted(Comparator.comparingInt(WeeklyActivityPointsDTO::getPoints).reversed())     //Sorterar från högst till lägst
            .toList(); 
    }
    
    
}
