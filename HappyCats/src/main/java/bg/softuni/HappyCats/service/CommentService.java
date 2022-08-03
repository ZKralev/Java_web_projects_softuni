package bg.softuni.HappyCats.service;

import bg.softuni.HappyCats.model.DTOS.AddCommentDTO;
import bg.softuni.HappyCats.model.entity.Booking;
import bg.softuni.HappyCats.model.entity.Comment;
import bg.softuni.HappyCats.model.mapper.CommentMapper;
import bg.softuni.HappyCats.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    public void addComment(AddCommentDTO addCommentDTO, HappyPetsUserDetailsService userDetails) {
        Comment newComment = commentMapper.commentMapperDTO(addCommentDTO);
        commentRepository.save(newComment);

    }
}
