package com.sixtyfy.skyblock.config;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import net.minecraft.text.Text;

import java.text.DecimalFormat;

public class SixtyFyConfigUtils {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static BooleanControllerBuilder createBooleanController(Option<Boolean> opt) {
        return BooleanControllerBuilder.create(opt).coloured(true);
    }

    public static FloatSliderControllerBuilder createFloatSliderController(Option<Float> opt, float min, float max, String unit, float step) {
        return FloatSliderControllerBuilder.create(opt).range(min, max).formatValue(f -> Text.of(DECIMAL_FORMAT.format(f) + unit)).step(step);
    }
}
