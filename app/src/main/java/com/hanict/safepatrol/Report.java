package com.hanict.safepatrol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

/**
 * Created by suh15 on 2017-07-03.
 */

public class Report extends Activity {
    private boolean SetDateButton = false;
    private boolean SetTimeButton = false;
    private String[] ReportList = {"차량과속", "신호위반", "보행자사고", "기타", "기타", "기타"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // 뒤로 가는 이미지 버튼 클릭시 해당 레이아웃을 종료 이벤트 설정
        ImageButton ReportReverseButton = (ImageButton)findViewById(R.id.report_reverse_button);
        ReportReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 사고 유형 Spinner 설정
        Spinner ReportSpinner = (Spinner) findViewById (R.id.report_spinner);   //xml에 선언한 스피너를 id값으로 불러옴
        ArrayAdapter<String> list;  //문자열 어댑터 선언
        list = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ReportList); //어댑터 객체를 생성하고 보여질 아이템 리소스와 문자열 지정
        ReportSpinner.setAdapter(list); //스피너에 adapter 연결

        //발생 날짜 설정 버튼 이벤트 설정
        Button ReportDateButton = (Button)findViewById(R.id.report_Date_button);
        ReportDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button ReportDateButton = (Button)findViewById(R.id.report_Date_button);
                Button ReportTimeButton = (Button)findViewById(R.id.report_time_button);
                DatePicker ReportDatepicker = (DatePicker)findViewById(R.id.report_datePicker);
                TimePicker ReportTimepicker = (TimePicker)findViewById(R.id.report_timePicker);
                if(SetDateButton==false) {
                    ReportDateButton.setText("날짜 설정 △");
                    ReportTimeButton.setText("시간 설정 ▽");
                    ReportDatepicker.setVisibility(View.VISIBLE);
                    ReportTimepicker.setVisibility(View.GONE);
                    SetDateButton = true;
                    SetTimeButton = false;
                } else {
                    ReportDateButton.setText("날짜 설정 ▽");
                    ReportDatepicker.setVisibility(View.GONE);
                    SetDateButton = false;
                }
            }
        });

        // 발생 시간 설정 버튼 이벤트 설정
        Button ReportTimeButton = (Button)findViewById(R.id.report_time_button);
        ReportTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button ReportDateButton = (Button)findViewById(R.id.report_Date_button);
                Button ReportTimeButton = (Button)findViewById(R.id.report_time_button);
                DatePicker ReportDatepicker = (DatePicker)findViewById(R.id.report_datePicker);
                TimePicker ReportTimepicker = (TimePicker)findViewById(R.id.report_timePicker);
                if(SetTimeButton==false) {
                    ReportTimeButton.setText("시간 설정 △");
                    ReportDateButton.setText("날짜 설정 ▽");
                    ReportTimepicker.setVisibility(View.VISIBLE);
                    ReportDatepicker.setVisibility(View.GONE);
                    SetTimeButton = true;
                    SetDateButton = false;
                } else {
                    ReportTimeButton.setText("시간 설정 ▽");
                    ReportTimepicker.setVisibility(View.GONE);
                    SetTimeButton = false;
                }
            }
        });

        // 지도에서 찾기 버튼 이벤트(추가예정)

        // 갤러리에서 이미지 불러오기(추가예정)


    }
}
