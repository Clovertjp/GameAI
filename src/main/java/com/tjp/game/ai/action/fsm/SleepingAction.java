package com.tjp.game.ai.action.fsm;

import java.util.Random;

import com.tjp.game.ai.config.Contants;
import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.inter.FSMStateInter;
import com.tjp.game.ai.profile.fsm.UserProfile;

public class SleepingAction implements FSMStateInter {
	
	public static final String ACTION_STR=" sleeping action ";
	
	private UserProfile userProfile;
	private Random random;
	
	public SleepingAction(UserProfile userProfile)
	{
		this.userProfile=userProfile;
		init();
	}

	public void onEnter() {
		// TODO Auto-generated method stub
		System.out.println(ACTION_STR+"  onEnter");
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
		if(userProfile.isDie())
		{
			System.out.println(userProfile+"   is die");
			GameEngine.getInstance().gameEnd();
			return ;
		}
		
		userProfile.incBlood(20*random.nextFloat());
		userProfile.decHungry(20*random.nextFloat());
		System.out.println(userProfile);
		
		FSMStateInter action=getFSMState();
		if(action!=null)
		{
			this.onExit();
			userProfile.setAction(action);
			action.onEnter();
		}
	}

	public void onExit() {
		// TODO Auto-generated method stub
		System.out.println(ACTION_STR+"  onExit");
	}

	public void init() {
		// TODO Auto-generated method stub
		random=new Random();
	}

	public FSMStateInter getFSMState() {
		// TODO Auto-generated method stub
		
		if(userProfile.getBlood()>=Contants.USER_VALUE_MAX)
		{
			return userProfile.getHungry()>userProfile.getEnergy() ? new FightingAction(userProfile) : 
																	new EatingAction(userProfile) ;
		}else
		{
			if(userProfile.getHungry()<=10.0f)
			{
				return new EatingAction(userProfile);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return ACTION_STR;
	}

}
