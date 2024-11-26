package global.goit.todolist.repository;

import global.goit.todolist.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {
}
