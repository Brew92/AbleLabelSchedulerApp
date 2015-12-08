package edu.uta.ucs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Todd on 12/1/2015.
 */
public class ViewScheduleDay extends Activity {


    private Schedule schedule;
    private ProgressDialog progressDialog;

    private int width;
    private int height;
    private String day;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.calendar);
        Intent intent = getIntent();
        try {
            JSONObject scheduleJSON = new JSONObject(intent.getStringExtra("Schedule"));
            schedule = new Schedule(scheduleJSON);
            Log.i("Load Schedules", "Schedule JSON" + schedule.toJSON().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        day = intent.getStringExtra("Day");

        ImageView calendarImg = new ImageView(this);//Build calendar image
        LinearLayout calendarContainer = new LinearLayout(this);//Build calendar container so you can determine position
        LinearLayout mainWindow = new LinearLayout(this);
        LinearLayout buttonWindow = new LinearLayout(this);
        ScrollView scrollWindow = new ScrollView(this);
        RelativeLayout mainContainer = new RelativeLayout(this);

        calendarImg.setImageResource(R.drawable.calendar_day);


        DisplayMetrics metrics = this.getResources().getDisplayMetrics();

        width = Math.round(metrics.widthPixels); //width of calendar space
        float scaleFactor = width/(float)544;

        height = Math.round(scaleFactor*1602); //height of calendar space
        float blockWidth = (width-(96*(scaleFactor)));
        LinearLayout.LayoutParams calendarParams = new LinearLayout.LayoutParams(width,height); //size width,height

        //calendarParams.leftMargin = 0; //x position
        //calendarParams.topMargin = 0;//Math.round((height/(float)49)/(float)30); //y position;


        //calendarImg.onMeasure(Math.round(h),Math.round(w));
        //calendarParams.height  = calendarImg.getNewHeight();
        calendarContainer.addView(calendarImg,calendarParams);

        mainContainer.addView(calendarContainer);

        int buttonWidth = Math.round(blockWidth);
        int buttonHeight = Math.round(height / (float) 24);
        Button dayButton = new Button(this);

        dayButton.setText(day);

        RelativeLayout dayButtonContainer = new RelativeLayout(this);

        RelativeLayout.LayoutParams dayButtonParams = new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);

        dayButtonContainer.addView(dayButton,dayButtonParams);

        ImageView miniLogoImg = new ImageView(this);
        miniLogoImg.setImageResource(R.drawable.uta_mascot_2c);
        LinearLayout miniLogoContainer = new LinearLayout(this);
        LinearLayout.LayoutParams miniLogoContainerParams = new LinearLayout.LayoutParams(Math.round(96*(scaleFactor)),Math.round(height/(float)24));
        miniLogoContainer.addView(miniLogoImg, miniLogoContainerParams);




        //int pos = 0;

        //float blockHeight = height/(float)49;
        for (Section section : schedule.getSelectedSections())
        {

            int blockY = Math.round(section.getStartTime().getMinAfterMidnight()*(((height-32)/(float)49)/(float)30)+(32*scaleFactor));
            float blockHeight = (section.getEndTime().getMinAfterMidnight() - section.getStartTime().getMinAfterMidnight())*(((height-32)/(float)49)/(float)30);
            if (section.getDaysString().contains("SU") && day.contains("Sunday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("M") && day.contains("Monday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96 * (width /(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("TU") && day.contains("Tuesday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("W") && day.contains("Wednesday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("TH") && day.contains("Thursday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("F") && day.contains("Friday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("SA") && day.contains("Saturday"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                switch (section.getSectionID())
                {
                    case(-1): {blockImg.setImageResource(R.drawable.round_rect_work); break;}//work
                    case(-2): {blockImg.setImageResource(R.drawable.round_rect_com); break;}//commute
                    case(-3): {blockImg.setImageResource(R.drawable.round_rect_sleep); break;}//sleep
                    case(-4): {blockImg.setImageResource(R.drawable.round_rect_study); break;}//study
                    case(-5): {blockImg.setImageResource(R.drawable.round_rect_other); break;}//other
                    default: {blockImg.setImageResource(R.drawable.round_rect_class); break;}//class
                }

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544))); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }


        }

        mainWindow.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams buttonWindowParams = new LinearLayout.LayoutParams(width,Math.round(height/(float)24));
        buttonWindow.setLayoutParams(buttonWindowParams);
        buttonWindow.setOrientation(LinearLayout.HORIZONTAL);
        buttonWindow.addView(miniLogoContainer);

        buttonWindow.addView(dayButtonContainer);
        mainWindow.addView(buttonWindow);
        scrollWindow.addView(mainContainer);//Scroll windows can only have one thing so I have to have a layout/container
        mainWindow.addView(scrollWindow);

        //LinearLayout.LayoutParams mainWindowParams = new LinearLayout.LayoutParams(0,0);
        //mainWindowParams.se(LinearLayout.VERTICAL);;
        //.setLayoutParams(mainWindowParams);
        setContentView(mainWindow);


    }




    @Override
    protected void onPause() {
        super.onPause();

        // progressDialog Safety
        if(progressDialog != null)
            progressDialog.dismiss();
    }
}