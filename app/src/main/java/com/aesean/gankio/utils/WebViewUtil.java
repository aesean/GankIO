package com.aesean.gankio.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import com.aesean.gankio.R;

/**
 * WebViewUtil
 *
 * @author xl
 * @version V1.0
 * @since 27/02/2017
 */
public class WebViewUtil {
    public static final int REQUEST_CODE = 10010;

    private static void openAction(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void open(Context context, String url) {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        String shareLabel = context.getString(R.string.label_action_share);
        Drawable shareDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_share_white, context.getTheme());
        Bitmap icon = BitmapUtil.convertToBitmap(shareDrawable);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, url);
        sendIntent.setType("text/plain");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, sendIntent, PendingIntent.FLAG_ONE_SHOT);
        intentBuilder.setActionButton(icon, shareLabel, pendingIntent);
        intentBuilder.setShowTitle(true);
        intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_close_white));
        intentBuilder.setStartAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out);
        intentBuilder.setExitAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out);
        CustomTabsIntent build = intentBuilder.build();
        build.launchUrl(context, Uri.parse(url));
    }
}
