package com.vicsla.vicsla.repo;

import com.vicsla.vicsla.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepositiry extends CrudRepository<Post, Long> {
}
