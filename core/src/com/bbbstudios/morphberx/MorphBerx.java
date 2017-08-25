package com.bbbstudios.morphberx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbbstudios.morphberx.Screens.SplashScreen;

public class MorphBerx extends Game
{
    public static final int V_WIDTH = 380;
    public static final int V_HEIGHT = 640;
    public static final int SOUND_COUNT = 12;

    public SpriteBatch batch;
    public AssetManager assetManager;

    @Override
    public void create()
    {
        batch = new SpriteBatch();

        assetManager = new AssetManager();

        setScreen(new SplashScreen(this));
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
