package com.hanict.safepatrol;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;

public class commuting extends Activity {
    // DB관련 변수
    private insideDataBase m_ins = null;
    private HashMap<String,Integer> InsideDataBase = null;
    // DB에서 불러온 값 저장
    private int dbStartHour1;
    private int dbStartMinute1;
    private int dbendHour1;
    private int dbendMinute1;
    private int dbStartHour2;
    private int dbStartMinute2;
    private int dbendHour2;
    private int dbendMinute2;


    // 엑티비티 관련 변수
    private boolean SettingCommutingStartButton1 = false;
    private boolean SettingCommutingEndButton1 = false;
    private boolean SettingCommutingStartButton2 = false;
    private boolean SettingCommutingEndButton2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commuting);
        // 출근 출발 시간 설정 버튼 이벤트 설정
        Button CommutingStartButton1 = (Button)findViewById(R.id.commuting_start_button1);
        CommutingStartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button CommutingStartButton1 = (Button)findViewById(R.id.commuting_start_button1);
                Button CommutingEndButton1 = (Button)findViewById(R.id.commuting_end_button1);
                TimePicker CommutingTimePickerStart1 = (TimePicker)findViewById(R.id.commuting_start_1);
                TimePicker CommutingTimePickerEnd1 = (TimePicker)findViewById(R.id.commuting_end_1);
                if(SettingCommutingStartButton1==false) {
                    CommutingStartButton1.setText("시작시간 설정 △");
                    CommutingEndButton1.setText("종료시간 설정 ▽");
                    CommutingTimePickerStart1.setVisibility(View.VISIBLE);
                    CommutingTimePickerEnd1.setVisibility(View.GONE);
                    SettingCommutingStartButton1 = true;
                    SettingCommutingEndButton1 = false;
                } else {
                    CommutingStartButton1.setText("시작시간 설정 ▽");
                    CommutingTimePickerStart1.setVisibility(View.GONE);
                    SettingCommutingStartButton1 = false;
                }
            }
        });
        // 출근 종료 시간 설정 버튼 이벤트 설정
        Button CommutingEndButton1 = (Button)findViewById(R.id.commuting_end_button1);
        CommutingEndButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button CommutingEndButton1 = (Button)findViewById(R.id.commuting_end_button1);
                Button CommutingStartButton1 = (Button)findViewById(R.id.commuting_start_button1);
                TimePicker CommutingTimePickerEnd1 = (TimePicker)findViewById(R.id.commuting_end_1);
                TimePicker CommutingTimePickerStart1 = (TimePicker)findViewById(R.id.commuting_start_1);
                if(SettingCommutingEndButton1==false) {
                    CommutingEndButton1.setText("종료시간 설정 △");
                    CommutingStartButton1.setText("시작시간 설정 ▽");
                    CommutingTimePickerEnd1.setVisibility(View.VISIBLE);
                    CommutingTimePickerStart1.setVisibility(View.GONE);
                    SettingCommutingEndButton1 = true;
                    SettingCommutingStartButton1 = false;
                } else {
                    CommutingEndButton1.setText("종료시간 설정 ▽");
                    CommutingTimePickerEnd1.setVisibility(View.GONE);
                    SettingCommutingEndButton1 = false;
                }
            }
        });

        // 퇴근 출발 시간 설정 버튼 이벤트 설정
        Button CommutingStartButton2 = (Button)findViewById(R.id.commuting_start_button2);
        CommutingStartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button CommutingStartButton2 = (Button)findViewById(R.id.commuting_start_button2);
                Button CommutingEndButton2 = (Button)findViewById(R.id.commuting_end_button2);
                TimePicker CommutingTimePickerStart2 = (TimePicker)findViewById(R.id.commuting_start_2);
                TimePicker CommutingTimePickerEnd2 = (TimePicker)findViewById(R.id.commuting_end_2);
                if(SettingCommutingStartButton2==false) {
                    CommutingStartButton2.setText("시작시간 설정 △");
                    CommutingEndButton2.setText("종료시간 설정 ▽");
                    CommutingTimePickerStart2.setVisibility(View.VISIBLE);
                    CommutingTimePickerEnd2.setVisibility(View.GONE);
                    SettingCommutingStartButton2 = true;
                    SettingCommutingEndButton2 = false;
                } else {
                    CommutingStartButton2.setText("시작시간 설정 ▽");
                    CommutingTimePickerStart2.setVisibility(View.GONE);
                    SettingCommutingStartButton2 = false;
                }
            }
        });
        // 퇴근 종료 시간 설정 버튼 이벤트 설정
        Button CommutingEndButton2 = (Button)findViewById(R.id.commuting_end_button2);
        CommutingEndButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button CommutingEndButton2 = (Button)findViewById(R.id.commuting_end_button2);
                Button CommutingStartButton2 = (Button)findViewById(R.id.commuting_start_button2);
                TimePicker CommutingTimePickerEnd2 = (TimePicker)findViewById(R.id.commuting_end_2);
                TimePicker CommutingTimePickerStart2 = (TimePicker)findViewById(R.id.commuting_start_2);
                if(SettingCommutingEndButton2==false) {
                    CommutingEndButton2.setText("종료시간 설정 △");
                    CommutingStartButton2.setText("시작시간 설정 ▽");
                    CommutingTimePickerEnd2.setVisibility(View.VISIBLE);
                    CommutingTimePickerStart2.setVisibility(View.GONE);
                    SettingCommutingEndButton2 = true;
                    SettingCommutingStartButton2 = false;
                } else {
                    CommutingEndButton2.setText("종료시간 설정 ▽");
                    CommutingTimePickerEnd2.setVisibility(View.GONE);
                    SettingCommutingEndButton2 = false;
                }
            }
        });

        // 데이터 베이스 작업전 타임피커 모두 선언하기
        TimePicker CommutingTimePickerStart1 = (TimePicker)findViewById(R.id.commuting_start_1);
        TimePicker CommutingTimePickerEnd1 = (TimePicker)findViewById(R.id.commuting_end_1);
        TimePicker CommutingTimePickerEnd2 = (TimePicker)findViewById(R.id.commuting_end_2);
        TimePicker CommutingTimePickerStart2 = (TimePicker)findViewById(R.id.commuting_start_2);

        try{ // 데이터 베이스 정의하는 작업
                m_ins = insideDataBase.GetInstance(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        InsideDataBase = m_ins.TimeSelect();
        dbStartHour1 = InsideDataBase.get("start_time_hour_1");
        dbStartMinute1 = InsideDataBase.get("start_time_Minute_1");
        dbendHour1 = InsideDataBase.get("end_time_hour_1");
        dbendMinute1 = InsideDataBase.get("end_time_Minute_1");
        dbStartHour2 = InsideDataBase.get("start_time_hour_2");
        dbStartMinute2 = InsideDataBase.get("start_time_Minute_2");
        dbendHour2 = InsideDataBase.get("end_time_hour_2");
        dbendMinute2 = InsideDataBase.get("end_time_Minute_2");
        // DB에서 가져온 값을 적용하는 과정
        CommutingTimePickerStart1.setHour(dbStartHour1);
        CommutingTimePickerStart1.setMinute(dbStartMinute1);
        CommutingTimePickerEnd1.setHour(dbendHour1);
        CommutingTimePickerEnd1.setMinute(dbendMinute1);
        CommutingTimePickerStart2.setHour(dbStartHour2);
        CommutingTimePickerStart2.setMinute(dbStartMinute2);
        CommutingTimePickerEnd2.setHour(dbendHour2);
        CommutingTimePickerEnd2.setMinute(dbendMinute2);

        // 저장버튼 이벤트 설정
        Button CommutingReturnButton = (Button)findViewById(R.id.commuting_Return_button);
        CommutingReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터 베이스 작업전 타임피커 모두 선언하기
                TimePicker CommutingTimePickerStart1 = (TimePicker) findViewById(R.id.commuting_start_1);
                TimePicker CommutingTimePickerEnd1 = (TimePicker) findViewById(R.id.commuting_end_1);
                TimePicker CommutingTimePickerStart2 = (TimePicker) findViewById(R.id.commuting_start_2);
                TimePicker CommutingTimePickerEnd2 = (TimePicker) findViewById(R.id.commuting_end_2);


                // TimePick 값을 int형 변수에 저장
                dbStartHour1 = CommutingTimePickerStart1.getHour();
                dbStartMinute1 = CommutingTimePickerStart1.getMinute();
                dbendHour1 = CommutingTimePickerEnd1.getHour();
                dbendMinute1 = CommutingTimePickerEnd1.getMinute();
                dbStartHour2 = CommutingTimePickerStart2.getHour();
                dbStartMinute2 = CommutingTimePickerStart2.getMinute();
                dbendHour2 = CommutingTimePickerEnd2.getHour();
                dbendMinute2 = CommutingTimePickerEnd2.getMinute();

                try { // 데이터 베이스 저장하는 작업
                    m_ins = insideDataBase.GetInstance(commuting.this);
                    m_ins.TimeAppend(dbStartHour1,dbStartMinute1
                            ,dbendHour1,dbendMinute1,dbStartHour2,dbStartMinute2,dbendHour2,
                            dbendMinute2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(commuting.this, "저장되었습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // 뒤로 가는 이미지 버튼 클릭시 해당 레이아웃을 종료
        ImageButton ReverseButton = (ImageButton)findViewById(R.id.commuting_reverse_button);
        ReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
