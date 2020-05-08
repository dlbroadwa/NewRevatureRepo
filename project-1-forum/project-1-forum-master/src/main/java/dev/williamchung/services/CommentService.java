package dev.williamchung.services;

import dev.williamchung.models.Comment;
import dev.williamchung.models.User;
import dev.williamchung.repositories.CommentRepository;

import java.util.List;

/**
 * This is the CommentService class which liaises between the servlet and repository dao.
 * Each method is explained below.
 */
public class CommentService {
    private CommentRepository commentRepository = new CommentRepository();

    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Grabs all the Comments mapped by foreign key to a Thread.
     * @param threadId
     * is passed by the Servlet.
     * @return
     * returns a List of all the comments for the thread id.
     */
    public List<Comment> getCommentsByThread(Integer threadId) {
        return commentRepository.findByComment(threadId);
    }

    /**
     * Creates a comment in the database.
     * @param commentContent
     * @param author
     * @param threadId
     * parameters are passed by the Servlet.
     * @return
     * returns a Comment object with the id generated by database.
     */
    public Comment postComment(String commentContent, User author, String threadId){
        Integer threadIdInteger = Integer.parseInt(threadId);
        Comment comment = new Comment(commentContent, author.getId(), threadIdInteger);
        return commentRepository.save(comment);
    }


}
