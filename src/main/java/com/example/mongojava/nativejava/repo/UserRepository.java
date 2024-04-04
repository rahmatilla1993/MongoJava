package com.example.mongojava.nativejava.repo;

import com.example.mongojava.nativejava.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(String id);
    List<User> findAllByZipCodeEndWith(String a, String b);
    List<User> findAllByLatitudeWithNegative();
    List<User> findAllByWebSiteEndWithDomain(String domain);
    User save(User user);
    boolean edit(User user);
    boolean editOfCatchPhraseAndLongitude(String id, String phrase, String lng);
    boolean delete(String id);
}
