package com.bbbstudios.morphberx.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bbbstudios.morphberx.MorphBerx;


public class EasterEgg implements Screen
{
    private static final int FRAME_COUNT = 9;
    private MorphBerx main;
    private SpriteBatch batch;
    private Animation<Sprite> animation;
    private AssetManager assetManager;
    private Music music;
    private Sprite currentFrame;
    private float scale, stateTimer;

    public EasterEgg(MorphBerx main)
    {
        super();
        this.main = main;
        this.assetManager = main.assetManager;
    }

    private void arrangeFrames()
    {
        Array<Sprite> frames = new Array<Sprite>();
        Texture texture;

        for (int i = 0; i < FRAME_COUNT; i++) {
            texture = assetManager
                    .get("animation/" + i + ".png", Texture.class);
            frames.add(new Sprite(texture));
        }

        scale = Gdx.graphics.getWidth() / frames.get(0).getWidth();

        Gdx.app.log("SCALE", "" + scale);
        animation = new Animation<Sprite>
                (0.05f, frames, Animation.PlayMode.LOOP);

        frames.clear();
    }

    private void loadMusic()
    {
        assetManager.load("sounds/opening.ogg", Music.class);
        assetManager.finishLoading();
        music = assetManager.get("sounds/opening.ogg", Music.class);

        //sound = Gdx.audio.newSound(Gdx.files.internal("sounds/opening.ogg"));
    }

    private void loadFrames()
    {
        for (int i = 0; i < FRAME_COUNT; i++) {
            assetManager
                    .load("animation/" + i + ".png", Texture.class);
        }
        assetManager.finishLoading();
    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();

        loadFrames();
        arrangeFrames();
        loadMusic();
        stateTimer = 0;
        music.play();
        main.loadAssets();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(.996f, .341f, 0.133f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stateTimer += delta;

        batch.begin();
        batch.draw(
                animation.getKeyFrame(stateTimer, true),
                0, (Gdx.graphics.getHeight() -
                currentFrame.getHeight() * scale) / 2,
                currentFrame.getWidth() * scale,
                currentFrame.getHeight() * scale
        );
        batch.end();
    }

    private void update(float delta)
    {
        currentFrame = animation.getKeyFrame(delta);

        assetManager.update();

        if (stateTimer >= 5f
                && assetManager.getQueuedAssets() == 0) {
            main.setScreen(new SoundScreen(main));
        }
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
    }
}
