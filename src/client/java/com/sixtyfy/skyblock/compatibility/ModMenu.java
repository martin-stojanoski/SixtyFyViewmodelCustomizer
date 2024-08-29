package com.sixtyfy.skyblock.compatibility;

import com.sixtyfy.skyblock.config.SixtyFyConfigManager;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return SixtyFyConfigManager::createGui;
    }
}
