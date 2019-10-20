package Practicas.Quiz.Room;

import androidx.room.RoomDatabase;


@androidx.room.Database(entities = {Player.class}, version = 1)
public abstract class DatabaseRepository extends RoomDatabase {
    public abstract PlayerDao getPlayerDao();
}
