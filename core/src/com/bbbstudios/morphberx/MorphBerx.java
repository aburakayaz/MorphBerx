package com.bbbstudios.morphberx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbbstudios.morphberx.Screens.EasterEgg;
import com.bbbstudios.morphberx.Screens.SplashScreen;

import java.util.Calendar;

public class MorphBerx extends Game
{
    public static final int V_WIDTH = 380;
    public static final int V_HEIGHT = 640;
    public static final int SOUND_COUNT = 12;
    public static final int MUSIC_COUNT = 1;

    public SpriteBatch batch;
    public AssetManager assetManager;

    @Override
    public void create()
    {
        batch = new SpriteBatch();

        assetManager = new AssetManager();

        setScreen(determineScreen());
    }

    private Screen determineScreen()
    {
        Calendar c = Calendar.getInstance();

        if(c.get(Calendar.MONTH) == Calendar.AUGUST
                && c.get(Calendar.DAY_OF_MONTH) == 25) {
            return new EasterEgg(this);
        }

        return new SplashScreen(this);
    }

    public void loadAssets()
    {
        for (int i = 0; i < SOUND_COUNT; i++) {
            assetManager.load("sounds/" + i + ".ogg", Sound.class);
        }

        for (int i = SOUND_COUNT; i < SOUND_COUNT  + MUSIC_COUNT; i++) {
            assetManager.load("sounds/m" + i + ".ogg", Music.class);
        }
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        assetManager.dispose();
        batch.dispose();
    }
}
