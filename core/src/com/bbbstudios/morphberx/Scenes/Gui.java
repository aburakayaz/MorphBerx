package com.bbbstudios.morphberx.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bbbstudios.morphberx.MorphBerx;

public class Gui implements Disposable
{
    private static final int SOUND_COUNT = MorphBerx.SOUND_COUNT;
    private static final int MUSIC_COUNT = MorphBerx.MUSIC_COUNT;
    private Viewport viewport;
    private Stage stage;
    private Array<Button> buttons;
    private AssetManager assetManager;
    private Skin skin;
    private Table table;
    private Sound currentSound;
    private Music currentMusic;
    private boolean isPlayingSound,
            isPlayingMusic;

    public Gui(AssetManager assetManager, SpriteBatch batch)
    {
        this.assetManager = assetManager;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        buttons = new Array<Button>();
        setViewport();
        stage = new Stage(viewport, batch);
        isPlayingSound = false;
        isPlayingMusic = false;
        setButtons();
        setTable();

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    private void buttonPressed(int i)
    {
        if (isPlayingSound) {
            currentSound.stop();
        }

        if (isPlayingMusic) {
            currentMusic.stop();
        }

        if (i < SOUND_COUNT) {
            currentSound = assetManager.get("sounds/" + i + ".ogg", Sound.class);
            currentSound.play();
            isPlayingSound = true;
            isPlayingMusic = false;
            return;
        }

        currentMusic = assetManager.get("sounds/m" + i + ".ogg", Music.class);
        currentMusic.play();
        isPlayingMusic = true;
        isPlayingSound = false;
    }

    private void setViewport()
    {
        viewport = new FitViewport(
                MorphBerx.V_WIDTH,
                MorphBerx.V_HEIGHT,
                new OrthographicCamera()
        );
    }

    private void setTable()
    {
        table = new Table();
        table.top();
        table.setFillParent(true);

        for (int i = 0; i < SOUND_COUNT + MUSIC_COUNT; i++) {
            table.add(buttons.get(i)).width(viewport.getWorldWidth() / 4).expandX().padTop(10);
            if ((i + 1) % 3 == 0) {
                table.row();
            }
        }
    }

    private void setButtons()
    {
        for (int i = 0; i < SOUND_COUNT + MUSIC_COUNT; i++) {
            buttons.add(createButton("" + (i + 1)));
            addListenerToButton(i);
        }
    }

    private Button createButton(String s)
    {

        TextButton.TextButtonStyle style =
                skin.get("default", TextButton.TextButtonStyle.class);
        TextButton button = new TextButton(s, style);
        button.padTop(viewport.getWorldHeight() / 15);
        button.padBottom(viewport.getWorldHeight() / 15);
        return button;
    }

    private void addListenerToButton(int i)
    {
        final int index = i;

        buttons.get(i).addListener(
                new ClickListener()
                {
                    @Override
                    public void clicked(InputEvent event, float x, float y)
                    {
                        buttonPressed(index);
                    }
                }
        );
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
    }

    public Stage getStage()
    {
        return stage;
    }
}
