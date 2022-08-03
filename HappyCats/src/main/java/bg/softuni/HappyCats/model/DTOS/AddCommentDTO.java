package bg.softuni.HappyCats.model.DTOS;

import bg.softuni.HappyCats.model.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class AddCommentDTO {


    private String message;

    private User author;

    public AddCommentDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
