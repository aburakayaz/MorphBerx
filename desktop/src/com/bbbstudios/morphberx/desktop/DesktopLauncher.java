package com.bbbstudios.morphberx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bbbstudios.morphberx.MorphBerx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "MorphBerx";
		config.width = 360;
		config.height = 640;
		new LwjglApplication(new MorphBerx(), config);
	}
}
