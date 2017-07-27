package com.hanict.safepatrol;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by suh15 on 2017-07-18.
 * 백그라운드 관련 소스
 * 추가할 항목
 * 1. 사용자 위치와 사고다발구역 위치가 같으면 사용자가 설정한 사운드를 내도록 설정 (안드로이드 내부 DB사용)
 * 2. 사용자의 설정한 시간 외에 사운드가 울리지 않도록 설정 (안드로이드 내부 DB사용)
 * 3. 백그라운드 관련 사이트 http://blog.naver.com/PostView.nhn?blogId=wind5395&logNo=150081290293
 */

public class BackGroundService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
