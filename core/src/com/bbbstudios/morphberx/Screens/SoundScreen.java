package com.bbbstudios.morphberx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbbstudios.morphberx.MorphBerx;
import com.bbbstudios.morphberx.Scenes.Gui;

public class SoundScreen implements Screen
{
    private Gui gui;
    private SpriteBatch batch;

    public SoundScreen (MorphBerx main)
    {
        super();
        batch = main.batch;
        gui = new Gui(main.assetManager, main.batch);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        update(delta);

        Gdx.gl.glClearColor(.996f, .341f, 0.133f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gui.getStage().getCamera().combined);
        gui.getStage().draw();
    }

    private void update(float delta)
    {

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
        gui.dispose();
    }
}
