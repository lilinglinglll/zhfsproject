package lll.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import lll.adapter.ListViewAdapter;
import lll.fregments.LocationsFregment;
import lll.fregments.PersonFregment;
import lll.fregments.RoomFregment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private String authority;//用户权限
    // 底部菜单2个Linearlayout
    private LinearLayout ll_person;
    private LinearLayout ll_room;
    private LinearLayout ll_location;
    // 底部菜单2个ImageView
    private ImageView iv_person;
    private ImageView iv_room;
    private ImageView iv_location;
    // 底部菜单2个菜单标题
    private TextView tv_person;
    private TextView tv_room;
    private TextView tv_location;
    // 2个Fragment
    private Fragment personFragment;
    private Fragment roomFragment;
    private Fragment locationFragment;

    private Context mContext = this;

    //保存被选中item的标签号
    private String tagNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headactivity_main);
        //获取上个界面传递的参数
        final Intent data = getIntent();
        authority = data.getStringExtra("authority");
        System.out.println("==============================" + authority);
        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment
        initFragment(0,authority);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragment(int index,String params) {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);

        Bundle args =new Bundle();
        args.putString("authority", params);
        switch (index){
            case 0:
                if (personFragment == null) {
                    personFragment = new PersonFregment();
                    personFragment.setArguments(args);
                    transaction.add(R.id.fl_content, personFragment);
                } else {
                    transaction.show(personFragment);
                }
                break;
            case 1:
                if (locationFragment == null) {
                    locationFragment = new LocationsFregment();
                    locationFragment.setArguments(args);
                    transaction.add(R.id.fl_content, locationFragment);
                } else {
                    transaction.show(locationFragment);
                    //transaction.replace(R.id.fl_content, locationFragment);
                }
                break;
            case 2:
                if (roomFragment == null) {
                    roomFragment = new RoomFregment();
                    roomFragment.setArguments(args);
                    transaction.add(R.id.fl_content, roomFragment);
                } else {
                    transaction.show(roomFragment);
                }
                break;
        }
        // 提交事务
        transaction.commit();
    }
    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (personFragment != null) {
            transaction.hide(personFragment);
        }
        if (roomFragment != null) {
            transaction.hide(roomFragment);
        }
        if (locationFragment != null) {
            transaction.hide(locationFragment);
        }
    }
    private void initView() {
        // 底部菜单2个Linearlayout
        this.ll_person = (LinearLayout) findViewById(R.id.ll_person);
        this.ll_room = (LinearLayout) findViewById(R.id.ll_room);
        this.ll_location = (LinearLayout) findViewById(R.id.ll_lacation);
        // 底部菜单2个ImageView
        this.iv_person = (ImageView) findViewById(R.id.iv_person);
        this.iv_room = (ImageView) findViewById(R.id.iv_room);
        this.iv_location = (ImageView) findViewById(R.id.iv_location);
        // 底部菜单2个菜单标题
        this.tv_person = (TextView) findViewById(R.id.tv_person);
        this.tv_room = (TextView) findViewById(R.id.tv_room);
        this.tv_location = (TextView) findViewById(R.id.tv_locatioin);
    }
    private void initEvent() {
        // 设置按钮监听
        ll_person.setOnClickListener(this);
        ll_room.setOnClickListener(this);
        ll_location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.ll_person:
                iv_person.setImageResource(R.drawable.person_pressed);
                tv_person.setTextColor(this.getResources().getColor(R.color.tv_pressed));
                initFragment(0,authority);
                break;
            case R.id.ll_lacation:
                iv_location.setImageResource(R.drawable.location_pressed);
                tv_location.setTextColor(this.getResources().getColor(R.color.tv_pressed));
                initFragment(1,authority);
                break;
            case R.id.ll_room:
                iv_room.setImageResource(R.drawable.room_pressed);
                tv_room.setTextColor(this.getResources().getColor(R.color.tv_pressed));
                initFragment(2,authority);
                break;
            default:
                break;
        }
    }
    private void restartBotton() {
        // ImageView置为灰色
        iv_person.setImageResource(R.drawable.person_normal);
        iv_location.setImageResource(R.drawable.location_normal);
        iv_room.setImageResource(R.drawable.room_normal);
        // TextView置为白色
        tv_person.setTextColor(this.getResources().getColor(R.color.tv_normal));
        tv_location.setTextColor(this.getResources().getColor(R.color.tv_normal));
        tv_room.setTextColor(this.getResources().getColor(R.color.tv_normal));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //“再按一次返回键退出程序”实现
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(),R.string.doubleclick_exit, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
