package com.example.App.service;

import java.time.OffsetDateTime;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.App.dto.UpdateUserDTO;
import com.example.App.dto.WeeklyActivityPointsDTO;
import com.example.App.execption.ResourceNotFoundException;
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

        record Thingimajigg(List<UserActivity> activityId, int points) {}
    
        // Definiera den tidsperiod (de senaste 7 dagarna)
        OffsetDateTime now = OffsetDateTime.now().minusDays(7);
    
        // Hämta användaren från databasen
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
    
        // Hämtar användaraktiviteter (Gruppera aktiviteter efter aktivitetstyp och summera poäng baserat på varaktigheten)
        var activityToPoints = user.getActivities().stream()
            .filter(activity -> activity.getTimestamp().isAfter(now.toLocalDateTime())) // Endast senaste 7 dagar
           // Grupperar användaraktiviteter
            .collect(Collectors.groupingBy(
                UserActivity::getActivity,
                Collectors.toList()
            ))
            // Hämtar varje entry
            .entrySet()
            //Streamar data
            .stream()
            //Mappar varje aktivitet till gruppaktivitet. Lägger till poäng
            .map(entry -> {
                var totSum = entry.getValue().stream()
                .map(activity -> activity.getPoints())
                .reduce(Integer::sum)
                .orElse(0);

                return new AbstractMap.SimpleEntry<>(entry.getKey(), new Thingimajigg(entry.getValue(), totSum));
            })
            //Sorterar efter totSum (Sorteras i fallande ordning baserat på poäng)
            .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().points(), entry1.getValue().points()))
            .toList();
    
        // Skapa en lista av WeeklyActivityPointsDTO 
         return activityToPoints.stream()
            .map(entry -> new WeeklyActivityPointsDTO(
                id, entry.getKey(), 
                entry.getValue().points(),
                entry.getValue().activityId()
                ))  
            .sorted(Comparator.comparingInt(WeeklyActivityPointsDTO::getPoints).reversed())     //Sorterar från högst till lägst
            .toList(); 
    

}
    
    
}
