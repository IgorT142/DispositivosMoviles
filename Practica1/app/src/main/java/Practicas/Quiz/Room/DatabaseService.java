package Practicas.Quiz.Room;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class DatabaseService {
    @SuppressLint("StaticFieldLeak")
    private static DatabaseService databaseService;

    private PlayerDao playerDao;

    private DatabaseService(Context context){
        Context appContext = context.getApplicationContext();
        DatabaseRepository databaseRepository = Room.databaseBuilder(appContext,DatabaseRepository.class,"player").
                allowMainThreadQueries().build();
        playerDao = databaseRepository.getPlayerDao();
    }

    public static DatabaseService get(Context context) {
        if (databaseService == null) {
            databaseService = new DatabaseService(context);
        }
        return databaseService;
    }

    public List<Player> getPlayers() {
        return playerDao.getPlayers();
    }

    public Player getPlayer(String id) {
        return playerDao.getPlayer(id);
    }

    public void addPlayer(Player player) {
        playerDao.addPlayer(player);
    }

    public void updatePlayer(Player player){
        playerDao.updatePlayer(player);
    }

    public void deletePlayer(Player player){
        playerDao.deletePlayer(player);
    }
}
