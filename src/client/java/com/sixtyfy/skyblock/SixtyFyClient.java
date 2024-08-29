package com.sixtyfy.skyblock;

import com.sixtyfy.skyblock.config.SixtyFyConfigManager;
import net.fabricmc.api.ClientModInitializer;

public class SixtyFyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		SixtyFyConfigManager.init();
	}
}