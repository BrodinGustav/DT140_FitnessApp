package com.example.App.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.App.dto.CreateUserActivityDTO;
import com.example.App.dto.LeaderboardDTO;
import com.example.App.dto.WeeklyActivityPointsDTO;
import com.example.App.model.UserActivity;

@Service
public interface UserActivityService {

    void createUserActivity(CreateUserActivityDTO userActivity);

    UserActivity getUserActivityById(Integer id);

    void deleteUserActivity(Integer id);

    List<LeaderboardDTO> totalResultat();

}
