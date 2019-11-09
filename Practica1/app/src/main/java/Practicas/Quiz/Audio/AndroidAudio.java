package Practicas.Quiz.Audio;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import Practicas.Quiz.Audio.Interfaces.Audio;
import Practicas.Quiz.Audio.Interfaces.Musica;
import Practicas.Quiz.Audio.Interfaces.Sonido;

public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    @Override
    public Musica nuevaMusica(String nombreArchivo) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(nombreArchivo);
            return new AndroidMusica(assetDescriptor);
        } catch (IOException e){
            throw new RuntimeException("no se ha podido cargar");
        }
    }

    @Override
    public Sonido nuevoSonido(String nombreArchivo) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(nombreArchivo);
            int soundId= soundPool.load(assetDescriptor, 0);
            return  new AndroidSonido(soundPool,soundId);
        } catch (IOException e){
            throw new RuntimeException("no se ha podido cargar");
        }
    }

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);

    }
}
