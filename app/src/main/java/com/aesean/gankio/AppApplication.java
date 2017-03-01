package com.aesean.gankio;

import android.app.Application;

import com.aesean.gankio.debug.BlockMonitor;
import com.aesean.gankio.debug.LifecyclePrinter;
import com.aesean.gankio.debug.shake.ShakeManager;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * AppApplication
 *
 * @author xl
 * @version V1.0
 * @since 24/02/2017
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        registerDebugService();
    }

    private void registerDebugService() {
        if (!BuildConfig.DEBUG) {
            return;
        }
        // Activity与Fragment生命周期打印
        new LifecyclePrinter.Builder()
                .setApplication(this)
                .build();
        BlockMonitor.getInstance().install();
        new ShakeManager(this).registerShakeDetector();
    }
}
