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
    private int points;

    public Player(String nick){
        this.nick = nick;
        points = 0;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
