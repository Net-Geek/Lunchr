package edu.uc.ui.utils;

import android.content.Context;
import android.content.res.Resources;

import java.util.Random;

import edu.uc.R;

public class RandomColorPicker {

    public int generateRandomMaterialColor(Context context) {
        int[] randomAndroidColors = context.getResources().getIntArray(R.array.randomColorArray);
        int randomMaterialColor = randomAndroidColors[new Random().nextInt(randomAndroidColors.length)];
        return randomMaterialColor;
    }

    public int[] pickRandomMaterialColorArray(Context context, int numberOfColors) {
        int[] randomAndroidColors = context.getResources().getIntArray(R.array.randomColorArray);
        int[] randomMaterialColorArray = new int[numberOfColors];
        for (int i = 0; i < numberOfColors ; i++) {
            randomMaterialColorArray[i] = randomAndroidColors[new Random().nextInt(randomAndroidColors.length)];
        }
        return randomMaterialColorArray;
    }
}
