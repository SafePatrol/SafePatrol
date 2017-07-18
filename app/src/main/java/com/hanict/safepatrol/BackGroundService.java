package com.hanict.safepatrol;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by suh15 on 2017-07-18.
 * 백그라운드 관련 소스
 */

public class BackGroundService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
