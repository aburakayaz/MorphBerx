package com.bbbstudios.morphberx.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bbbstudios.morphberx.MorphBerx;


public class EasterEgg implements Screen
{
    private static final int FRAME_COUNT = 9;
    private Viewport viewport;
    private MorphBerx main;
    private SpriteBatch batch;
    private Animation<Sprite> animation;
    private AssetManager assetManager;
    private Sound sound;
    private Sprite currentFrame;
    private float scale, stateTimer;

    public EasterEgg(MorphBerx main)
    {
        super();
        this.main = main;
        this.assetManager = main.assetManager;
        batch = new SpriteBatch();

        setViewport();
        loadFrames();
        arrangeFrames();
        loadSound();
        stateTimer = 0;
        loadAssets();
    }

    public void setViewport()
    {
        viewport = new FitViewport(
                MorphBerx.V_WIDTH,
                MorphBerx.V_HEIGHT,
                new OrthographicCamera()
        );
    }

    private void arrangeFrames()
    {
        Array<Sprite> frames = new Array<Sprite>();
        Texture texture;
        Sprite sprite;

        for (int i = 0; i < FRAME_COUNT; i++) {
            texture = assetManager.get("animation/" + i + ".png", Texture.class);
            sprite = new Sprite(texture);
            frames.add(sprite);
        }

        scale = viewport.getWorldWidth() / frames.get(0).getWidth();

        animation = new Animation<Sprite>(0.05f, frames, Animation.PlayMode.LOOP);

        frames.clear();
    }

    private void loadSound()
    {
        assetManager.load("sounds/opening.ogg", Sound.class);
        assetManager.finishLoading();
        sound = assetManager.get("sounds/opening.ogg");
    }

    private void loadFrames()
    {
        for (int i = 0; i < FRAME_COUNT; i++) {
            assetManager.load("animation/" + i + ".png", Texture.class);
        }
        assetManager.finishLoading();
    }

    @Override
    public void show()
    {
        sound.play();
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
                0, (viewport.getWorldHeight() -
                currentFrame.getHeight() * scale) / 2,
                currentFrame.getWidth() * scale,
                currentFrame.getHeight() * scale
        );
        batch.end();
    }

    private void update(float delta)
    {
        currentFrame = animation.getKeyFrame(delta);

        if (stateTimer >= 3.5f
                && assetManager.getQueuedAssets() == 0) {
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
    }
}
