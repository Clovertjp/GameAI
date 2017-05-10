package com.tjp.game.ai.action.fsm;

import java.util.Random;

import com.tjp.game.ai.config.Contants;
import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.inter.FSMStateInter;
import com.tjp.game.ai.profile.fsm.UserProfile;

public class FightingAction implements FSMStateInter {
	
	public static final String ACTION_STR=" fighting action ";
	
	private UserProfile userProfile;
	private Random random;
	
	public FightingAction(UserProfile userProfile)
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
			return;
		}
		
		userProfile.incEnergy(10*random.nextFloat());
		userProfile.decBlood(20*random.nextFloat());
		userProfile.decHungry(15*random.nextFloat());
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
		
		if(userProfile.getEnergy()>=Contants.USER_VALUE_MAX)
		{
			return userProfile.getHungry()>userProfile.getBlood() ? new SleepingAction(userProfile) : 
																	new EatingAction(userProfile) ;
		}else
		{
			if(userProfile.getBlood()<=10.0f)
			{
				return new SleepingAction(userProfile);
			}
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
