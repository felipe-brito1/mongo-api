package com.felipebrito.mongo_api.services;

import com.felipebrito.mongo_api.domain.Post;
import com.felipebrito.mongo_api.domain.User;
import com.felipebrito.mongo_api.repository.PostRepository;
import com.felipebrito.mongo_api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        Optional<Post> obj = repo.findById(id);
    return obj.orElseThrow(()-> new ObjectNotFoundException("Object not found!"));
    }

    public List<Post> findByTitle(String text){
        return repo.findByTitleContainingIgnoreCase(text);
    }
}
