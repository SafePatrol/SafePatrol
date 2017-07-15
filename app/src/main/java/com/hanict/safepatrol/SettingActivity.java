package com.hanict.safepatrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Shin on 2017-06-23.
 */

public class SettingActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        // 사고 다발 구역 신고 버튼 클릭시 해당 레이아웃으로 이동 코드
        ImageButton Report = (ImageButton)findViewById(R.id.report_button);
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, Report.class);
                startActivity(intent);
            }
        });
        // 뒤로 가는 이미지 버튼 클릭시 해당 레이아웃을 종료
        ImageButton ReverseButton = (ImageButton)findViewById(R.id.reverse_button);
        ReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
