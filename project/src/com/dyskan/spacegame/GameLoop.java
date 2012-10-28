package com.dyskan.spacegame;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.os.Bundle;

 /*
  * AndEngine --> SimpleBaseGameEngine
  * 	we don't need to handle callbacks.
  * 
  * --> onCreateEngineOptions
  * --> onCreateResources
  * --> onCreateScene
  * 
  * ==> Success!
  */
public class GameLoop extends SimpleBaseGameActivity {
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	
	private ITextureRegion mBackgroudTextureRegion;
	
	/**
	 * Sets up the engine for the came
	 * @see org.andengine.ui.IGameInterface#onCreateEngineOptions()
	 */
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
		/*
		 * Params:
		 * 
		 * FullScreen: yes/ no
		 * ScreenOrientation: Specifies the orientation used while the game is running.
         * ResolutionPolicy: Defines how the engine will scale the game assets on phones with different screen sizes.
         * Camera: Defines the width and height of the final game scene.
		 */
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED ,new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}
	
	/**
	 * Sets up the resources for the current view
	 * Should this be done asyncronized? Loaded on startup?
	 * 
	 * Our textures should be placed in assets
	 * 
	 * We must use spritesheets
	 */
	protected void onCreateResources() throws IOException {
		//In case shit fucks up
		try {
			
		    // 1 - Set up bitmap textures
		    ITexture AwesomeTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
		        @Override
		        public InputStream open() throws IOException {
		            return getAssets().open("gfx/Awesome!.png");
		        }
		    });
		    
		    // 2 - Load bitmap textures into VRAM
		    AwesomeTexture.load();
		    
		    // 3 - Set up texture regions
			this.mBackgroudTextureRegion = TextureRegionFactory.extractFromTexture(AwesomeTexture);
		    
		} catch (IOException e) {
		    Debug.e(e);
		}
		
		
		
	}
	
	/**
	 * Sets up the game scene
	 */
	@Override
	protected Scene onCreateScene() {
		
		// 1 - Create new scene
		final Scene scene = new Scene();
		Sprite backgroundSprite = new Sprite(0, 0, this.mBackgroudTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);
		
		/*
		 * Maube old example.. Do not return, but use in onPopulateScene
		 */
		return scene;
	}
	
	//@Override
	//public void OnLoadComplete(){}
	
	/*
	 * Engine is stopped and resumed automaticly, everything else mst be handled here
	 * @see org.andengine.ui.activity.BaseGameActivity#onResumeGame()
	 */
	@Override
	public void onResumeGame() {
		super.onResumeGame();

	}

	@Override
	public void onPauseGame() {
		super.onPauseGame();

	}

}