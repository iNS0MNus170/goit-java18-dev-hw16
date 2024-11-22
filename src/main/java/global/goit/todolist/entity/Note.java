package global.goit.todolist.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Note {
    private long id;
    private String title;
    private String content;
}
