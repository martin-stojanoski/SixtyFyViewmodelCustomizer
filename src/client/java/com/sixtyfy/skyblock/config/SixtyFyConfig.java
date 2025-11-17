package com.sixtyfy.skyblock.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.isxander.yacl3.platform.YACLConfig.HANDLER;

public class SixtyFyConfig {
    // HELD ITEM
    @SerialEntry
    public boolean enableViewmodelTransformation = false;

    @SerialEntry
    public float translateX = 0.0f;
    @SerialEntry
    public float translateY = 0.0f;
    @SerialEntry
    public float translateZ = 0.0f;

    @SerialEntry
    public float scale = 1.0f;

    @SerialEntry
    public float rotateX = 0.0f;
    @SerialEntry
    public float rotateY = 0.0f;
    @SerialEntry
    public float rotateZ = 0.0f;

    // ARM
    @SerialEntry
    public boolean enableArmTransformation = false;

    @SerialEntry
    public float armTranslateX = 0.0f;
    @SerialEntry
    public float armTranslateY = 0.0f;
    @SerialEntry
    public float armTranslateZ = 0.0f;

    @SerialEntry
    public float armRotateX = 0.0f;
    @SerialEntry
    public float armRotateY = 0.0f;
    @SerialEntry
    public float armRotateZ = 0.0f;

    // Define presets as a map
    private static final Map<String, Preset> PRESETS = Map.of(
            "Default", new Preset(
                    false, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
                    false, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f
            ),
            "Small", new Preset(
                    true, 0.5f, 0.4f, -0.5f, 0.4f, 5.0f, -10.0f, 2.0f,
                    true, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f
            ),
            "Large", new Preset(
                    true, 0.3f, -0.2f, -0.4f, 2.6f, 0f, 0f, 0f,
                    true, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f
            )
    );

    public String getCurrentPreset() {
        return PRESETS.entrySet().stream()
                .filter(entry -> matchesPreset(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Custom");
    }

    private boolean matchesPreset(Preset preset) {
        return floatsEqual(enableViewmodelTransformation, preset.enableViewmodelTransformation) &&
                floatsEqual(translateX, preset.translateX) &&
                floatsEqual(translateY, preset.translateY) &&
                floatsEqual(translateZ, preset.translateZ) &&
                floatsEqual(scale, preset.scale) &&
                floatsEqual(rotateX, preset.rotateX) &&
                floatsEqual(rotateY, preset.rotateY) &&
                floatsEqual(rotateZ, preset.rotateZ) &&
                floatsEqual(enableArmTransformation, preset.enableArmTransformation) &&
                floatsEqual(armTranslateX, preset.armTranslateX) &&
                floatsEqual(armTranslateY, preset.armTranslateY) &&
                floatsEqual(armTranslateZ, preset.armTranslateZ) &&
                floatsEqual(armRotateX, preset.armRotateX) &&
                floatsEqual(armRotateY, preset.armRotateY) &&
                floatsEqual(armRotateZ, preset.armRotateZ);
    }

    private boolean floatsEqual(float a, float b) {
        return Math.abs(a - b) < 0.001f;
    }

    private boolean floatsEqual(boolean a, boolean b) {
        return a == b;
    }

    public void applyPresetByName(String presetName) {
        Preset preset = PRESETS.get(presetName);
        if (preset != null) {
            applyPreset(preset);
            HANDLER.save();
        }
        // "Custom" doesn't change anything
    }

    private void applyPreset(Preset preset) {
        enableViewmodelTransformation = preset.enableViewmodelTransformation;
        translateX = preset.translateX;
        translateY = preset.translateY;
        translateZ = preset.translateZ;
        scale = preset.scale;
        rotateX = preset.rotateX;
        rotateY = preset.rotateY;
        rotateZ = preset.rotateZ;

        enableArmTransformation = preset.enableArmTransformation;
        armTranslateX = preset.armTranslateX;
        armTranslateY = preset.armTranslateY;
        armTranslateZ = preset.armTranslateZ;
        armRotateX = preset.armRotateX;
        armRotateY = preset.armRotateY;
        armRotateZ = preset.armRotateZ;
    }

    // Preset record to hold all configuration values
    private record Preset(
            boolean enableViewmodelTransformation,
            float translateX, float translateY, float translateZ,
            float scale,
            float rotateX, float rotateY, float rotateZ,
            boolean enableArmTransformation,
            float armTranslateX, float armTranslateY, float armTranslateZ,
            float armRotateX, float armRotateY, float armRotateZ
    ) {}
}