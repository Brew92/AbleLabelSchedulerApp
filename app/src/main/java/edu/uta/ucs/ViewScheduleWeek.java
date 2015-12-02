package edu.uta.ucs;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import android.content.Context;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * This Activity is designed to show a schedule. It expects the intent with which it is started to have the propper string extras.
 */
public class ViewScheduleWeek extends Activity {




    private ImageView calendarImg;

    private LinearLayout calendarContainer;
    private LinearLayout.LayoutParams calendarParams;
    private Schedule schedule;

    private ProgressDialog progressDialog;

    private int width;
    private int height;


    Button sundayButton;
    Button mondayButton;
    Button tuesdayButton;
    Button wednesdayButton;
    Button thursdayButton;
    Button fridayButton;
    Button saturdayButton;

    RelativeLayout sundayButtonContainer;
    RelativeLayout mondayButtonContainer;
    RelativeLayout tuesdayButtonContainer;
    RelativeLayout wednesdayButtonContainer;
    RelativeLayout thursdayButtonContainer;
    RelativeLayout fridayButtonContainer;
    RelativeLayout saturdayButtonContainer;

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

        LinearLayout mainWindow = new LinearLayout(this);
        LinearLayout buttonWindow = new LinearLayout(this);
        ScrollView scrollWindow = new ScrollView(this);
        RelativeLayout mainContainer = new RelativeLayout(this);


        calendarImg  = new ImageView(this);  //Build calendar image
        calendarImg.setImageResource(R.drawable.calendar_week);

        calendarContainer = new LinearLayout(this); //Build calendar container so you can determine position
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();

        width = Math.round(metrics.widthPixels); //width of calendar space
        float scaleFactor = width/(float)544;

        height = Math.round(scaleFactor*1602); //height of calendar space
        float blockWidth = (width-(96*(scaleFactor)))/(float)7;
        calendarParams = new LinearLayout.LayoutParams(width,height); //size width,height

        //calendarParams.leftMargin = 0; //x position
        //calendarParams.topMargin = 0;//Math.round((height/(float)49)/(float)30); //y position;


        //calendarImg.onMeasure(Math.round(h),Math.round(w));
        //calendarParams.height  = calendarImg.getNewHeight();
        calendarContainer.addView(calendarImg,calendarParams);

        mainContainer.addView(calendarContainer);

        int buttonWidth = Math.round(blockWidth);
        int buttonHeight = Math.round(height / (float) 24);
        sundayButton = new Button(this);
        mondayButton = new Button(this);
        tuesdayButton = new Button(this);
        wednesdayButton = new Button(this);
        thursdayButton = new Button(this);
        fridayButton = new Button(this);
        saturdayButton = new Button(this);
        sundayButton.setText("Sunday");
        mondayButton.setText("Monday");
        tuesdayButton.setText("Tuesday");
        wednesdayButton.setText("Wednesday");
        thursdayButton.setText("Thursday");
        fridayButton.setText("Friday");
        saturdayButton.setText("Saturday");
        sundayButtonContainer = new RelativeLayout(this);
        mondayButtonContainer = new RelativeLayout(this);
        tuesdayButtonContainer = new RelativeLayout(this);
        wednesdayButtonContainer = new RelativeLayout(this);
        thursdayButtonContainer = new RelativeLayout(this);
        fridayButtonContainer = new RelativeLayout(this);
        saturdayButtonContainer = new RelativeLayout(this);
        RelativeLayout.LayoutParams sundayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);
        RelativeLayout.LayoutParams mondayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);
        RelativeLayout.LayoutParams tuesdayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);
        RelativeLayout.LayoutParams wednesdayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);
        RelativeLayout.LayoutParams thursdayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);
        RelativeLayout.LayoutParams fridayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);
        RelativeLayout.LayoutParams saturdayButtonParams =new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);

        sundayButtonContainer.addView(sundayButton,sundayButtonParams);
        mondayButtonContainer.addView(mondayButton,mondayButtonParams);
        tuesdayButtonContainer.addView(tuesdayButton,tuesdayButtonParams);
        wednesdayButtonContainer.addView(wednesdayButton,wednesdayButtonParams);
        thursdayButtonContainer.addView(thursdayButton,thursdayButtonParams);
        fridayButtonContainer.addView(fridayButton,fridayButtonParams);
        saturdayButtonContainer.addView(saturdayButton,saturdayButtonParams);


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
            if (section.getDaysString().contains("SU"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544)) + blockWidth*0); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("M"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96 * (width /(float)544)) + blockWidth*1); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("TU"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544)) + blockWidth*2); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("W"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544)) + blockWidth*3); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("TH"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544)) + blockWidth*4); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("F"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544)) + blockWidth*5); //x position
                blockParams.topMargin = blockY; //y position
                blockContainer.addView(blockImg,blockParams);
                blockContainer.addView(text,blockParams);
                mainContainer.addView(blockContainer);
            }
            if (section.getDaysString().contains("SA"))
            {
                ImageView blockImg = new ImageView(this); //Build block image
                blockImg.setImageResource(R.drawable.round_rect_shape);

                TextView text = new TextView(this);
                text.setTextAppearance(this, android.R.style.TextAppearance_Small);
                text.setText(section.getDescription());
                RelativeLayout blockContainer = new RelativeLayout(this); //Build block container so you can determine position
                RelativeLayout.LayoutParams blockParams = new RelativeLayout.LayoutParams(Math.round(blockWidth),Math.round(blockHeight)); //size width,height
                blockParams.leftMargin = Math.round((96*(width/(float)544)) + blockWidth*6); //x position
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


        buttonWindow.addView(sundayButtonContainer);
        buttonWindow.addView(mondayButtonContainer);
        buttonWindow.addView(tuesdayButtonContainer);
        buttonWindow.addView(wednesdayButtonContainer);
        buttonWindow.addView(thursdayButtonContainer);
        buttonWindow.addView(fridayButtonContainer);
        buttonWindow.addView(saturdayButtonContainer);
        mainWindow.addView(buttonWindow);
        scrollWindow.addView(mainContainer);//Scroll windows can only have one thing so I have to have a layout/container
        mainWindow.addView(scrollWindow);

        //LinearLayout.LayoutParams mainWindowParams = new LinearLayout.LayoutParams(0,0);
        //mainWindowParams.se(LinearLayout.VERTICAL);;
           //.setLayoutParams(mainWindowParams);
        setContentView(mainWindow);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                SettingsActivity.startActivity(ViewScheduleWeek.this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set buttons to toggle colors. These can all be discarded if a proper style is setup and used for buttons.
        sundayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Sunday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
        mondayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Monday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
        tuesdayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Tuesday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
        wednesdayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Wednesday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
        thursdayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Thursday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
        fridayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Friday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
        saturdayButton.setOnClickListener(new View.OnClickListener() {   //setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailedSchedule", "Opening View Schedule");
                Intent startViewScheduleActivity = new Intent(ViewScheduleWeek.this, ViewScheduleDay.class);
                try {
                    startViewScheduleActivity.putExtra("Schedule",schedule.toJSON().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("DetailedSchedule", "Could not parse schedule to JSON");
                }
                //startViewScheduleActivity.putExtra("BlockList",blocks);
                startViewScheduleActivity.putExtra("Day","Saturday");
                ViewScheduleWeek.this.startActivity(startViewScheduleActivity);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // progressDialog Safety
        if(progressDialog != null)
            progressDialog.dismiss();
    }


}

