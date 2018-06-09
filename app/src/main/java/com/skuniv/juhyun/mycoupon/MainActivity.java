package com.skuniv.juhyun.mycoupon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ViewPager vp;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //네비게이션 드로우 관련 변수선언
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        // 툴바 생성 및 세팅하는 부분
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 네비게이션 뷰 아이템 클릭시 이뤄지는 이벤트
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                int id = item.getItemId();
                // 각 메뉴 클릭시 이뤄지는 이벤트
                switch (id){
                    case R.id.item_notice:
                        Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.item_time:
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.item_none1:
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.item_none2:
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // 햄버거 버튼 클릭 시 드로어 열리도록 하는 곳



        vp = (ViewPager)findViewById(R.id.vp);
//        Button btn_third = (Button)findViewById(R.id.btn_third);

//        Button btn_first = (Button)findViewById(R.id.btn_first);
//        Button btn_second = (Button)findViewById(R.id.btn_second);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        //뷰페이저 좌우 잔상
        vp.setClipToPadding(false);
        vp.setPadding(70, 0, 70, 0);
//        vp.setPageMargin(getResources().getDisplayMetrics().widthPixels / -50);

//        btn_first.setOnClickListener(movePageListener);
//        btn_first.setTag(0);
//        btn_second.setOnClickListener(movePageListener);
//        btn_second.setTag(1);
//        btn_third.setOnClickListener(movePageListener);
//        btn_third.setTag(2);
    }


    //메뉴 버튼 클릭시 메뉴바 나오는 부분
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    View.OnClickListener movePageListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            int tag = (int) v.getTag();
//            vp.setCurrentItem(tag);
//        }
//    };

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                case 2:
                    return new Fragment3();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }

        //뷰페이저 잔상
        @Override
        public float getPageWidth(int position) {
//            return (0.9f);
        return super.getPageWidth(position);
        }

    }
}
