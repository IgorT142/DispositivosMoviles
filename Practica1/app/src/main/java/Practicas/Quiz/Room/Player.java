package Practicas.Quiz.Room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "jugadores")
public class Player {
    @PrimaryKey
    @NonNull
    private String id;
    private String nick;

    public Player(String nick){
        this.nick = nick;
        id= UUID.randomUUID().toString();
    }
    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
