package com.tjp.game.ai.profile.fsm;

import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.tjp.game.ai.action.fsm.FightingAction;
import com.tjp.game.ai.config.Contants;
import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.inter.fsm.FSMStateInter;
import com.tjp.game.ai.inter.GameObjectInter;

public class UserProfile implements GameObjectInter {
	
	private String name;
	
	private float blood;
	private float energy;
	private float hungry;
	
	private FSMStateInter action;
	
	private Map<String,FSMStateInter> actionMap=new HashMap<String, FSMStateInter>();
	
	public UserProfile(String name)
	{
		this.name=name;
		blood=Contants.USER_VALUE_MAX;
		energy=Contants.USER_VALUE_MAX;
		hungry=Contants.USER_VALUE_MAX;
	}
	

	public void onEnter() {
		// TODO Auto-generated method stub
		action=new FightingAction(this);
		action.onEnter();
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		if(action!=null)
		{
			action.onUpdate();
		}
		
	}

	public void onExit() {
		// TODO Auto-generated method stub

	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getBlood() {
		return blood;
	}


	public void setBlood(float blood) {
		this.blood = blood;
	}
	
	public boolean incBlood(float num) {
		if(num<=0 || num >Contants.USER_VALUE_MAX)
		{
			return false;
		}
		blood+=num;
		if(blood>Contants.USER_VALUE_MAX)
		{
			blood=Contants.USER_VALUE_MAX;
		}
		return true;
	}
	
	public boolean decBlood(float num) {
		if(num<=0 || num >Contants.USER_VALUE_MAX)
		{
			return false;
		}
		blood-=num;
		if(blood<0)
		{
			blood=0;
		}
		return true;
	}


	public float getEnergy() {
		return energy;
	}


	public void setEnergy(float energy) {
		this.energy = energy;
	}
	
	public boolean incEnergy(float num) {
		if(num<=0 || num >Contants.USER_VALUE_MAX)
		{
			return false;
		}
		energy+=num;
		if(energy>Contants.USER_VALUE_MAX)
		{
			energy=Contants.USER_VALUE_MAX;
		}
		return true;
	}
	
	public boolean decEnergy(float num) {
		if(num<=0 || num >Contants.USER_VALUE_MAX)
		{
			return false;
		}
		energy-=num;
		if(energy<0)
		{
			energy=0;
		}
		return true;
	}


	public float getHungry() {
		return hungry;
	}


	public void setHungry(float hungry) {
		this.hungry = hungry;
	}
	
	public boolean incHungry(float num) {
		if(num<=0 || num >Contants.USER_VALUE_MAX)
		{
			return false;
		}
		hungry+=num;
		if(hungry>Contants.USER_VALUE_MAX)
		{
			hungry=Contants.USER_VALUE_MAX;
		}
		return true;
	}
	
	public boolean decHungry(float num) {
		if(num<=0 || num >Contants.USER_VALUE_MAX)
		{
			return false;
		}
		hungry-=num;
		if(hungry<0)
		{
			hungry=0;
		}
		return true;
	}


	public FSMStateInter getAction() {
		return action;
	}


	public void setAction(FSMStateInter action) {
		this.action = action;
	}


	@Override
	public String toString() {
		return "UserProfile [name=" + name + ", blood=" + blood + ", energy=" + energy + ", hungry=" + hungry
				+ ", action=" + action + "]";
	}
	
	
	public boolean isDie()
	{
		if(blood<=0 || hungry<=0 || energy<=0)
		{
			return true;
		}
		return false;
	}


	public FSMStateInter getActionWithAdd(String actionKey) {
		if(actionMap.containsKey(actionKey))
		{
			return actionMap.get(actionKey);
		}
		if(!Contants.actionClassControl.containsKey(actionKey))
		{
			System.out.println("未注册 "+actionKey);
			return null;
		}
		Class actionClass=Contants.actionClassControl.get(actionKey);
		FSMStateInter action=null;
		try {
			action = (FSMStateInter) actionClass.newInstance();
			action.setUserProfile(this);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(action!=null)
		{
			setAction(actionKey,action);
		}
		return action;
	}
	
	public void setAction(String actionKey,FSMStateInter action)
	{
		if(Strings.isNullOrEmpty(actionKey))
		{
			return ;
		}
		if(action == null)
		{
			return ;
		}
		actionMap.put(actionKey, action);
	}

}
