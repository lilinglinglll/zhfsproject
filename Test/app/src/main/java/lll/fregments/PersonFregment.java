package lll.fregments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
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

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lll.adapter.ListViewAdapter;
import lll.domain.Tags;
import lll.service.CreatTagsResult;
import lll.test.R;
import lll.test.showtimeActivity;
import lll.utils.ServiceIp;

public class PersonFregment extends Fragment{
    private String authority;
    private ListView mListView;
    private ListViewAdapter mAdapter;
    private Context mContext;
    //保存被选中item的标签号
    private String tagNum;

    static final String nameSpaceAddress = "http://service.zhfs.lab516/";
    static final String methodfindTagUserByAuthority = "findTagUserByAuthority";
    static final String soapAction = "http://service.zhfs.lab516/findTagUserByAuthority";

    public PersonFregment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext=getActivity();
        Bundle auth=getArguments();
        authority=auth.getString("authority");
        System.out.println("personfregment  "+authority);
        //查询标签信息
        List<Tags> result=new ArrayList<Tags>();
        final StringBuilder endPoint=new StringBuilder("http://");
        endPoint.append(ServiceIp.serviceIp()).append(":8080/WebService-ZHFS/TagUserServiceWS");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();//非主线程中默认没有创建Looper对象，需要先调用Looper.prepare()启用Looper
                SoapObject soapObject = new SoapObject(nameSpaceAddress, methodfindTagUserByAuthority);
                soapObject.addProperty("authority", authority);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                envelope.bodyOut = soapObject;
                envelope.dotNet = false;
                envelope.setOutputSoapObject(soapObject);
                HttpTransportSE httpTransportSE = new HttpTransportSE(endPoint.toString());
                try {
                    httpTransportSE.call(soapAction, envelope);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SoapObject object = (SoapObject) envelope.bodyIn;

                System.out.println(object.getProperty(0).toString());
//                result = object.getProperty(0).toString();
//                System.out.println("解析服务数据成功");
//                System.out.println(result);
                Looper.loop();
            }
        }).start();
        System.out.println("====================================================tag" + result);
//        pool.shutdown();
        //显示

        inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.tag_listview,null);
        mListView = (ListView)layout.findViewById(R.id.listview);
        final List<Tags> finalResult = result;
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
                //显示标号
                TextView t = (TextView)convertView.findViewById(R.id.position);
                t.setText(finalResult.get(position).getTagNum() + ".");
                //显示tagnum
                TextView text_data= (TextView)convertView.findViewById(R.id.text_data);
                StringBuilder tag =new StringBuilder();
                tag.append(finalResult.get(position).getName()).append(",").append(finalResult.get(position).getSex());
                text_data.setText(tag);
            }
            @Override
            public View generateView(final int position, ViewGroup parent) {
                View v = LayoutInflater.from(mContext).inflate(R.layout.tag_listview_item, null);
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
                        Toast.makeText(mContext,R.string.check_time_info, Toast.LENGTH_SHORT).show();
                        System.out.println("=========================================选中的tag" + tagNum);
                        Intent intent = new Intent(mContext, showtimeActivity.class);
                        //采用Intent普通传值的方式
                        intent.putExtra("tagNum", tagNum);
                        startActivity(intent);
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
                TextView c = (TextView) mListView.getChildAt(position - mListView.getFirstVisiblePosition()).findViewById(R.id.position);//该句可以得到LISTVIEW中的相应数据
                tagNum = c.getText().toString();
                //去掉tagNum后的点
                tagNum=tagNum.substring(0,tagNum.length()-1);
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

