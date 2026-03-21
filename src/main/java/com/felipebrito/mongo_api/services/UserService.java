package com.felipebrito.mongo_api.services;

import com.felipebrito.mongo_api.domain.User;
import com.felipebrito.mongo_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

}
