package global.goit.todolist.service;

import global.goit.todolist.entity.Note;
import global.goit.todolist.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {

        return noteRepository.findAll();
    }

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoSuchElementException("Note with ID " + id + " not found.");
        }
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        if (!noteRepository.existsById(note.getId())) {
            throw new NoSuchElementException("Note with ID " + note.getId() + " not found.");
        }
        noteRepository.save(note);
    }

    public Note getById(long id) {
        Optional<Note> currentNote = noteRepository.findById(id);
        if (currentNote.isEmpty()) {
            throw new NoSuchElementException("Note with ID " + id + " not found.");
        }
        return currentNote.get();
    }
}