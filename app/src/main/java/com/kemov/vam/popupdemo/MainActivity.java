package com.kemov.vam.popupdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context mContext;
    Button  popupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }

    private void initView() {
        popupBtn = (Button) findViewById(R.id.poPupbtn);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });
    }

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.pop_window, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                100, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        ListView listview = contentView.findViewById(R.id.listview);
        String[] list = new String[]{"编辑","删除"};
        ArrayAdapter adpter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adpter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "i="+i+", l="+l, Toast.LENGTH_SHORT).show();
                if (popupWindow.isShowing())
                    popupWindow.dismiss();
            }
        });

        // 设置PopupWindow的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        popupWindow.showAsDropDown(view,10,0);

    }


}
