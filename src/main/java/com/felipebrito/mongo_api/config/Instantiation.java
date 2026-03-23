package com.felipebrito.mongo_api.config;

import com.felipebrito.mongo_api.domain.Post;
import com.felipebrito.mongo_api.domain.User;
import com.felipebrito.mongo_api.dto.AuthorDTO;
import com.felipebrito.mongo_api.dto.CommentDTO;
import com.felipebrito.mongo_api.repository.PostRepository;
import com.felipebrito.mongo_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {


        userRepository.deleteAll();
        postRepository.deleteAll();


        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));


        Post post1 = new Post(null, Instant.now(), "Partiu viagem!", "Vou viajar para o Rio de Janeiro, abraços", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.now(), "Boa noite!", "Desejo a todos uma boa noite", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem, tia!", Instant.now(), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Curta a praia", Instant.now(), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha atenção", Instant.now(), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll((Arrays.asList(post1, post2)));

        maria.getPosts().addAll((Arrays.asList(post1, post2)));
        userRepository.save(maria);
    }
}
