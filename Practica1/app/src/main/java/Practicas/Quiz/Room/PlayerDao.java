package Practicas.Quiz.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM jugadores")
    List<Player> getPlayers();

    @Query("SELECT * FROM jugadores WHERE id LIKE :uuid")
    Player getPlayer(String uuid);

    @Insert
    void addPlayer(Player player);

    @Delete
    void deletePlayer(Player player);

    @Query("DELETE FROM jugadores")
    void nukeTable();

    @Update
    void updatePlayer(Player player);
}
