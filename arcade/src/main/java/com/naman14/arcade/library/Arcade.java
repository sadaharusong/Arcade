package com.naman14.arcade.library;

import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.content.res.AssetManager;
import android.content.pm.ApplicationInfo;
import android.content.Context;

public class Arcade {

    AssetManager assetManager;
    ApplicationInfo info;
    ArcadeBuilder builder;

    static {
        System.loadLibrary("png16");
        System.loadLibrary("arcade");
    }

    public Arcade(Context context, ArcadeBuilder builder) {
        this.builder = builder;
        assetManager = context.getAssets();
        info = context.getApplicationInfo();
    }

    public void initialize() {
        initialize(assetManager, info.nativeLibraryDir, "neural_style.lua");
    }

    public void stylize() {
        stylize(builder.styleimage, builder.contentImage, builder.outputImage, builder.gpu, builder.iterations, builder.imageSize,
                builder.optimizer, builder.modelFile, builder.protoFIle, builder.backend, builder.styleScale, builder.styleBlendWeights,
                builder.styleLayers, builder.contentLayers, builder.pooling, builder.tvWeight, builder.styleWeight, builder.contentWeight,
                builder.seed, builder.learningRate, builder.init, builder.normalizeGradients, builder.printIterations,
                builder.saveIterations);
    }

    public void destroyArcade() {
        destroy();
    }

    // native method
    private native String initialize(AssetManager manager, String path, String luafile);

    private native String stylize(String styleImage, String contentImage, String outputImage, int gpu, int iterations, int imageSize,
                                  String optimizer, String modelFile, String protoFile, String backend, long styleScale,
                                  String blendWeights, String styleLayers, String contentLayers,
                                  String pooling, long tvWeight, long styleWeight, long contentWeight,
                                  int seed, int learningRate, String init, boolean normalizeGradients,
                                  int printIterations, int saveIterations);

    private native String destroy();

}