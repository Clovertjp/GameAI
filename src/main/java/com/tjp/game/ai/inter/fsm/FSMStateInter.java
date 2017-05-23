package com.tjp.game.ai.inter.fsm;

import com.tjp.game.ai.inter.GameObjectInter;
import com.tjp.game.ai.profile.fsm.UserProfile;

public interface FSMStateInter extends GameObjectInter {
	
	public void init();
	public FSMStateInter getFSMState();
	
	public UserProfile getUserProfile();

	public void setUserProfile(UserProfile userProfile);

}
