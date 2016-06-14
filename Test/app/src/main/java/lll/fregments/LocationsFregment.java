package lll.fregments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.List;

import lll.adapter.ListViewAdapter;
import lll.domain.Rooms;
import lll.test.R;
import lll.test.map_414Activity;
import lll.test.map_516Activity;

public class LocationsFregment extends Fragment{
    private int authority;
    private ListView mListView;
    private ListViewAdapter mAdapter;
    private Context mContext;
    //保存被选中item的标签号
    private String roomNum;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext=getActivity();
        Bundle auth=getArguments();
        authority=auth.getInt("authority");

        List<Rooms> result=new ArrayList<Rooms>();
        Rooms r516=new Rooms("Lab516");
        result.add(r516);
        Rooms r414=new Rooms("414");
        result.add(r414);
        System.out.println("====================================================location" + result);

        //显示
        inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.room_listview,null);
        mListView = (ListView)layout.findViewById(R.id.roomlistview);
        final List<Rooms> finalResult = result;
        mAdapter = new ListViewAdapter(mContext) {
            @Override
            public int getCount() {
                return finalResult.size();
            }

            @Override
            public Object getItem(int position) {
                return super.getItem(position);
            }
            @Override
            public void fillValues(int position, View convertView) {
                //显示房间号
                TextView t = (TextView)convertView.findViewById(R.id.who);
                t.setText(finalResult.get(position).getRoomNum() + ".");
//                //显示房间信息
//                TextView text_data= (TextView)convertView.findViewById(R.id.text_data);
//                StringBuilder tag =new StringBuilder();
            }
            @Override
            public View generateView(final int position, ViewGroup parent) {
                View v = LayoutInflater.from(mContext).inflate(R.layout.room_listview_item, null);
                SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
                swipeLayout.addSwipeListener(new SimpleSwipeListener() {
                    @Override
                    public void onOpen(SwipeLayout layout) {
                        YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.magnifier));
                        YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.star));
                        YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                    }
                });
                swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
                    @Override
                    public void onDoubleClick(SwipeLayout layout, boolean surface) {
                        Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
                    }
                });
                v.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,R.string.delete_tag, Toast.LENGTH_SHORT).show();
                    }
                });
                v.findViewById(R.id.magnifier).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("=========================================选中的room" + roomNum);
                        if(roomNum.equals("Lab516")){
                            Intent intent = new Intent(mContext, map_516Activity.class);
                            //采用Intent普通传值的方式
                            intent.putExtra("authority", authority);
                            startActivity(intent);
                        }else if(roomNum.equals("414")){
                            Intent intent = new Intent(mContext, map_414Activity.class);
                            //采用Intent普通传值的方式
                            intent.putExtra("authority", authority);
                            startActivity(intent);
                        }
                    }
                });
                v.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,R.string.attention_tag, Toast.LENGTH_SHORT).show();
                    }
                });
                return v;
            }
        };

        mListView.setAdapter(mAdapter);
        mAdapter.setMode(Attributes.Mode.Single);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((SwipeLayout) (mListView.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
                TextView c = (TextView) mListView.getChildAt(position - mListView.getFirstVisiblePosition()).findViewById(R.id.who);//该句可以得到LISTVIEW中的相应数据
                roomNum = c.getText().toString();
                //去掉roomNum后的点
                roomNum = roomNum.substring(0, roomNum.length() - 1);
            }
        });
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("ListView", "onNothingSelected:");
            }
        });
        return layout;
    }
}

