package com.sixtyfy.skyblock.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

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
}
