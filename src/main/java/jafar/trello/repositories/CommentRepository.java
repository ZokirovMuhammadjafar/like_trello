package jafar.trello.repositories;

import jafar.trello.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByDeletedFalse();

    Comment findByIdAndDeletedFalse(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update Comment t SET t.deleted = true WHERE t.id=:id")
    void delete(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "Update Comment t SET t.text =:text WHERE t.id=:id")
    void update(@Param("id") Long id, @Param("text") String text);


    @Query("SELECT c FROM Comment c WHERE  c.deleted =false AND c.taskId=:taskId ORDER BY c.id desc ")
    List<Comment> findAllByTaskIdd(@Param("taskId") Long taskId);

    @Query("from Comment c where c.authorUsername=:id")
    List<Comment> getCommentCount(String id);
}
