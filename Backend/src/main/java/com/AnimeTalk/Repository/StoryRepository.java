package com.AnimeTalk.Repository;

import com.AnimeTalk.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story,Integer> {
    public List<Story> findByUserId(Integer userId);

}
