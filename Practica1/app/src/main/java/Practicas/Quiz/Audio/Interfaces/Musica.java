package Practicas.Quiz.Audio.Interfaces;

public interface Musica {
    public void play();

    public void stop();

    public boolean isPlaying();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();
}
