package jafar.trello.controller.column;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jafar.trello.dto.column.ColumnCreateDto;
import jafar.trello.dto.column.ColumnUpdateDto;
import jafar.trello.services.column.ColumnService;
import jafar.trello.services.project.ProjectService;
import jafar.trello.services.task.TaskService;

@Controller

@RequestMapping("/project/column/")
public class ColumnController {
    private final ColumnService service;
    private final ProjectService projectService;


    public ColumnController(
            ColumnService service,
            TaskService taskService, ProjectService projectService) {
        this.service = service;

        this.projectService = projectService;
    }

    @RequestMapping("list/{id}")
    public String list(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("project", projectService.get(id));
        model.addAttribute("columns", service.getAll(id));
        return "project/project";
    }

    @RequestMapping(value = "create/{id}", method = RequestMethod.POST)
    public String create(@PathVariable(name = "id") Long id, @ModelAttribute ColumnCreateDto dto) {
        dto.setProjectId(id);
        service.create(dto);
        return "redirect:/project/"+id;
    }

   @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public String createPAage(@PathVariable(name = "id") Long id, Model model) {
        ColumnCreateDto columnCreateDto = new ColumnCreateDto();
        columnCreateDto.setProjectId(id);
        model.addAttribute("dto", columnCreateDto);
        return "column/create";
    }

    @RequestMapping(value = "update/", method = RequestMethod.POST)
    public String create(@ModelAttribute ColumnUpdateDto dto) {
        service.update(dto);
        return "redirect:/project/project";
    }

    @RequestMapping(value = "delete/", method = RequestMethod.DELETE)
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/project/project";
    }
}
