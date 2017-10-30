package com.itcast.yb.packelves.utils;

import android.content.Context;
import android.widget.PopupWindow;

import com.itcast.yb.packelves.R;

/**
 * Created by yb on 2017/10/28.
 */

public class PopupUtils {
    public static void initPopup(Context ctx,PopupWindow pw) {
        pw.setBackgroundDrawable(ctx.getResources().getDrawable(R.color.transparent));
        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
    }

    public static void dismissPopup(PopupWindow pw) {
        if(pw != null || pw.isShowing()) {
            pw.dismiss();
        }
    }
}
