package com.re.project.service;


import com.re.project.entity.UserProfile;

public interface UserProfileService {

    UserProfile getCurrentProfile();

    void save(UserProfile profile);
}
