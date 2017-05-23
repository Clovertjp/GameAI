package com.tjp.game.ai.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.tjp.game.ai.action.fsm.EatingAction;
import com.tjp.game.ai.action.fsm.FightingAction;
import com.tjp.game.ai.action.fsm.SleepingAction;
import com.tjp.game.ai.config.Contants;
import com.tjp.game.ai.inter.GameObjectInter;

public class GameEngine {

	private static GameEngine gameEngine=new GameEngine();
	private volatile boolean isRuing;
	private List<GameObjectInter> childrenList=new ArrayList<GameObjectInter>();
	private AtomicLong atomicLong=new AtomicLong(0);
	
	private GameEngine()
	{
		init();
	}
	
	public void init()
	{
		Contants.registerAction(EatingAction.ACTION_STR, EatingAction.class);
		Contants.registerAction(FightingAction.ACTION_STR, FightingAction.class);
		Contants.registerAction(SleepingAction.ACTION_STR, SleepingAction.class);
	}
	
	public static GameEngine getInstance()
	{
		return gameEngine;
	}
	
	public void gameStart()
	{
		isRuing=true;
	}
	
	public void onEnter()
	{
		for(GameObjectInter child : childrenList)
		{
			child.onEnter();
		}
	}
	
	public void onUpdate()
	{
		onEnter();
		while(isRuing)
		{
			atomicLong.incrementAndGet();
			for(GameObjectInter child : childrenList)
			{
				child.onUpdate();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		onExit();
	}
	
	public void onExit()
	{
		for(GameObjectInter child : childrenList)
		{
			child.onExit();
		}
	}
	
	public void gameEnd()
	{
		isRuing=false;
	}
	
	public void addChild(GameObjectInter obj)
	{
		childrenList.add(obj);
	}
	
	public long getFrame()
	{
		return atomicLong.get();
	}

}
