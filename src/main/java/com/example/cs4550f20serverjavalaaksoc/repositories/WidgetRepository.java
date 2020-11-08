package com.example.cs4550f20serverjavalaaksoc.repositories;

import com.example.cs4550f20serverjavalaaksoc.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
    @Query(value = "SELECT * FROM widgets where topic_id=:tid", nativeQuery = true)
    List<Widget> findWidgetsByTopicId(@Param("tid") String topicId);
}
