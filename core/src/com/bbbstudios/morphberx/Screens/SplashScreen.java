package com.bbbstudios.morphberx.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.bbbstudios.morphberx.MorphBerx;
import com.bbbstudios.morphberx.Tween.SpriteAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;


public class SplashScreen implements Screen
{
    private SpriteBatch batch;
    private Texture texture;
    private Sprite splash;
    private TweenManager tweenManager;
    private AssetManager assetManager;
    private MorphBerx main;

    public SplashScreen(MorphBerx main)
    {
        super();
        this.main = main;
        this.assetManager = main.assetManager;
    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        texture = new Texture("splashscreen.png");
        splash = new Sprite(texture);
        tweenManager = new TweenManager();

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).start(tweenManager);
        loadAssets();
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(0)/*.delay(2)*/.start(tweenManager); // TODO: Change
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(.996f, .341f, 0.133f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        batch.begin();
        splash.draw(batch);
        batch.end();
    }

    private void update(float delta)
    {
        tweenManager.update(delta);

        if (tweenManager.getRunningTweensCount() == 0) {
            main.setScreen(new SoundScreen(main));
        }
    }

    private void loadAssets()
    {
        for (int i = 0; i < MorphBerx.SOUND_COUNT; i++) {
            assetManager.load("sounds/" + i + ".ogg", Sound.class);
        }

        assetManager.finishLoading();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        texture.dispose();
    }
}