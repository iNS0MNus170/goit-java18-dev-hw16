package global.goit.todolist.controller;

import global.goit.todolist.entity.Note;
import global.goit.todolist.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView listNotes() {
        List<Note> notes = noteService.listAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView editNoteForm(@RequestParam("id") long id) {
        Note note = noteService.getById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editNote(@ModelAttribute Note note) {
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }
}
