package com.sixtyfy.skyblock.config;

import com.google.gson.FieldNamingPolicy;
import com.sixtyfy.skyblock.SixtyFyClient;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

public class SixtyFyConfigManager {
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("sixtyfy.json");
    private static final ConfigClassHandler<SixtyFyConfig> HANDLER = ConfigClassHandler.createBuilder(SixtyFyConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(PATH)
                    .setJson5(false)
                    .appendGsonBuilder(builder -> builder
                            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                            .registerTypeHierarchyAdapter(Identifier.class, new Identifier.Serializer())
                    ).build()
            ).build();

    public static void init() {
        if (StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass() != SixtyFyClient.class) {
            throw new IllegalStateException("Cannot initialize config from a class other than SixtyFyClient");
        }

        HANDLER.load();
    }

    public static SixtyFyConfig getConfig() {
        return HANDLER.instance();
    }

//    public static void save() {
//        HANDLER.save();
//    }

    public static Screen createGui(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, ((defaults, config, builder) -> builder
                .title(Text.literal("SixtyFy Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("UI"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Held Item View Model Transformation"))
                                .description(OptionDescription.of(Text.literal("Options for transforming the view model of the held item")))
                                .collapsed(false)
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Held Item View Model Transformation"))
                                        .description(OptionDescription.of(Text.literal("Enables held item view mod transformation")))
                                        .binding(
                                                defaults.enableViewmodelTransformation,
                                                () -> config.enableViewmodelTransformation,
                                                (v) -> config.enableViewmodelTransformation = v
                                        )
                                        .controller(SixtyFyConfigUtils::createBooleanController)
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Translate X"))
                                        .description(OptionDescription.of(Text.literal("X translation of the held item view model")))
                                        .binding(
                                                defaults.translateX,
                                                () -> config.translateX,
                                                (v) -> config.translateX = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -2.0f, 2.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Translate Y"))
                                        .description(OptionDescription.of(Text.literal("Y translation of the held item view model")))
                                        .binding(
                                                defaults.translateY,
                                                () -> config.translateY,
                                                (v) -> config.translateY = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -2.0f, 2.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Translate Z"))
                                        .description(OptionDescription.of(Text.literal("Z translation of the held item view model")))
                                        .binding(
                                                defaults.translateZ,
                                                () -> config.translateZ,
                                                (v) -> config.translateZ = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -2.0f, 2.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Scale"))
                                        .description(OptionDescription.of(Text.literal("Scale of the held item view model")))
                                        .binding(
                                                defaults.scale,
                                                () -> config.scale,
                                                (v) -> config.scale = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, 0.1f, 3.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Rotate X"))
                                        .description(OptionDescription.of(Text.literal("X rotation of the held item view model")))
                                        .binding(
                                                defaults.rotateX,
                                                () -> config.rotateX,
                                                (v) -> config.rotateX = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -180.0f, 180.0f, "°", 1.0f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Rotate Y"))
                                        .description(OptionDescription.of(Text.literal("Y rotation of the held item view model")))
                                        .binding(
                                                defaults.rotateY,
                                                () -> config.rotateY,
                                                (v) -> config.rotateY = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -180.0f, 180.0f, "°", 1.0f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Rotate Z"))
                                        .description(OptionDescription.of(Text.literal("Z rotation of the held item view model")))
                                        .binding(
                                                defaults.rotateZ,
                                                () -> config.rotateZ,
                                                (v) -> config.rotateZ = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -180.0f, 180.0f, "°", 1.0f))
                                        .build()
                                )
                                .build()
                        )
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Arm View Model Transformation"))
                                .description(OptionDescription.of(Text.literal("Options for transforming the view model of the players arm")))
                                .collapsed(false)
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Arm View Model Transformation"))
                                        .description(OptionDescription.of(Text.literal("Enables view model transformation of the players arm")))
                                        .binding(
                                                defaults.enableArmTransformation,
                                                () -> config.enableArmTransformation,
                                                (v) -> config.enableArmTransformation = v
                                        )
                                        .controller(SixtyFyConfigUtils::createBooleanController)
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Translate X"))
                                        .description(OptionDescription.of(Text.literal("X translation of the view model of the players arm")))
                                        .binding(
                                                defaults.armTranslateX,
                                                () -> config.armTranslateX,
                                                (v) -> config.armTranslateX = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -2.0f, 2.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Translate Y"))
                                        .description(OptionDescription.of(Text.literal("Y translation of the view model of the players arm")))
                                        .binding(
                                                defaults.armTranslateY,
                                                () -> config.armTranslateY,
                                                (v) -> config.armTranslateY = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -2.0f, 2.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Translate Z"))
                                        .description(OptionDescription.of(Text.literal("Z translation of the view model of the players arm")))
                                        .binding(
                                                defaults.armTranslateZ,
                                                () -> config.armTranslateZ,
                                                (v) -> config.armTranslateZ = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -2.0f, 2.0f, "", 0.1f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Rotate X"))
                                        .description(OptionDescription.of(Text.literal("X rotation of the view model of the players arm")))
                                        .binding(
                                                defaults.armRotateX,
                                                () -> config.armRotateX,
                                                (v) -> config.armRotateX = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -180.0f, 180.0f, "°", 1.0f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Rotate Y"))
                                        .description(OptionDescription.of(Text.literal("Y rotation of the view model of the players arm")))
                                        .binding(
                                                defaults.armRotateY,
                                                () -> config.armRotateY,
                                                (v) -> config.armRotateY = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -180.0f, 180.0f, "°", 1.0f))
                                        .build()
                                )
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Rotate Z"))
                                        .description(OptionDescription.of(Text.literal("Z rotation of the view model of the players arm")))
                                        .binding(
                                                defaults.armRotateZ,
                                                () -> config.armRotateZ,
                                                (v) -> config.armRotateZ = v
                                        )
                                        .controller(opt -> SixtyFyConfigUtils.createFloatSliderController(opt, -180.0f, 180.0f, "°", 1.0f))
                                        .build()
                                )
                                .build()
                        )
                        .build()
                        
                )
        )).generateScreen(parent);
    }
}
