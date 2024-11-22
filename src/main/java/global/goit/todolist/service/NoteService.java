package global.goit.todolist.service;

import global.goit.todolist.entity.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NoteService {

    private final Map<Long, Note> notes = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        add(Note.builder()
                .title("Elden Ring: Game Review")
                .content("Elden Ring is an exciting action RPG developed by FromSoftware. " +
                         "It offers a vast open world and a complex combat system, making every battle unique.")
                .build());
        add(Note.builder()
                .title("Cyberpunk 2077: Patches and Updates")
                .content("Cyberpunk 2077, after numerous updates, has become much more stable and impressive. " +
                         "The game mechanics, graphics, and story are now on a new level.")
                .build());
        add(Note.builder()
                .title("The Witcher 3: Wild Hunt")
                .content("The Witcher 3 is an epic story about Geralt of Rivia, with many quests, an open world, " +
                         "and intense combat. One of the best role-playing games of all time.")
                .build());
        add(Note.builder()
                .title("Minecraft: How to Create Your World")
                .content("Minecraft is a game where you can build your own worlds from blocks. " +
                         "From simple buildings to large castles and cities. " +
                         "The game stimulates creativity and offers endless possibilities.")
                .build());
        add(Note.builder()
                .title("Horizon Zero Dawn: Post-Apocalyptic Journey")
                .content("Horizon Zero Dawn is an adventure game where you play as Aloy, " +
                         "a young woman exploring a post-apocalyptic world inhabited by robots and strange creatures.")
                .build());
    }

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        long id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with ID " + id + " not found.");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NoSuchElementException("Note with ID " + note.getId() + " not found.");
        }
        Note currentNote = notes.get(note.getId());
        currentNote.setTitle(note.getTitle());
        currentNote.setContent(note.getContent());
    }

    public Note getById(long id) {
        Note currentNote = notes.get(id);
        if (currentNote == null) {
            throw new NoSuchElementException("Note with ID " + id + " not found.");
        }
        return currentNote;
    }
}