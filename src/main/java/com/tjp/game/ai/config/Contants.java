package com.tjp.game.ai.config;

import java.util.HashMap;
import java.util.Map;

import com.tjp.game.ai.inter.fsm.FSMStateInter;

public class Contants {
	
	public static float USER_VALUE_MAX=100.0f;
	
	public static Map<String, Class> actionClassControl=new HashMap<String, Class>();
	
	public static void registerAction(String key,Class actionClass)
	{
		actionClassControl.put(key, actionClass);
	}

}
