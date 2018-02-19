package com.alex.materialanimations.models;

import android.content.res.ColorStateList;

/**
 * Created by Alex on 19/02/2018.
 */

public class Color {
    private String ColorName;
    private ColorStateList ColorValue;

    public Color()
    {

    }

    public Color(String ColorName, ColorStateList ColorValue)
    {
        this.ColorName = ColorName;
        this.ColorValue = ColorValue;
    }

    public String getColorName() {
        return ColorName;
    }

    public void setColorName(String colorName) {
        ColorName = colorName;
    }

    public ColorStateList getColorValue() {
        return ColorValue;
    }

    public void setColorValue(ColorStateList colorValue) {
        ColorValue = colorValue;
    }

}
