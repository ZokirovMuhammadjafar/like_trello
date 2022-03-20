package jafar.trello.controller.commentController;

import jafar.trello.dto.comment.CommentCreateDto;
import jafar.trello.services.comment.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/comment/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "create/{id}", method = RequestMethod.POST)
    public String comment(@ModelAttribute CommentCreateDto dto, @PathVariable(name = "id") Long id) {
        dto.setTaskId(id);
        commentService.create(dto);
        return "redirect:/task/" + id;
    }


}
