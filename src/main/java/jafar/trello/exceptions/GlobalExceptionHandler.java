package jafar.trello.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class })
    public String error404(NotFoundException e,
                           Model model,
                           WebRequest request){
        String path =((ServletWebRequest)(request)).getRequest().getRequestURI();
//        model.addAttribute("time", new Date());
//        model.addAttribute("message", e.getMessage());
//        model.addAttribute("path", path);
//        model.addAttribute("error", e.getStatus().getReasonPhrase());
        model.addAttribute("code", e.getStatus().value());
        return "error/error";

    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public String rror404(NoHandlerFoundException e, Model model, WebRequest request) {
//        String path = ((ServletWebRequest) (request)).getRequest().getRequestURI();
//        model.addAttribute("time", new Date());
//        model.addAttribute("message", e.getMessage());
//        model.addAttribute("path", path);
//        model.addAttribute("error", e.getStatus().getReasonPhrase());
        model.addAttribute("code", e.getMessage());
        return "error/error";
    }






}
