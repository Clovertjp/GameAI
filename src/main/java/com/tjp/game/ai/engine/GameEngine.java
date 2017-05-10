package com.tjp.game.ai.engine;

import java.util.ArrayList;
import java.util.List;

import com.tjp.game.ai.inter.GameObjectInter;

public class GameEngine {

	private static GameEngine gameEngine=new GameEngine();
	private volatile boolean isRuing;
	private List<GameObjectInter> childrenList=new ArrayList<GameObjectInter>();
	
	private GameEngine()
	{
		
	}
	
	public static GameEngine getInstance()
	{
		return gameEngine;
	}
	
	public void gameStart()
	{
		isRuing=true;
	}
	
	public void onUpdate()
	{
		while(isRuing)
		{
			
			for(GameObjectInter child : childrenList)
			{
				child.onUpdate();
			}
			
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void gameEnd()
	{
		isRuing=false;
	}

}
