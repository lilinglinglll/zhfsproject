package lll.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import lll.login.MaterialLoginView;
import lll.login.MaterialLoginViewListener;
import lll.service.WarnService;
import lll.utils.ServiceIp;

public class LoginActivity extends Activity {

    static final String nameSpaceAddress = "http://service.zhfs.lab516/";
    static final String methodCheckMnameAndPswd = "checkMnameAndPswd";
    static final String methodFindAuthorityByName = "findAuthorityByName";
    static final String soapAction = "http://service.zhfs.lab516/checkMnameAndPswd";
    static final String soapAction2 = "http://service.zhfs.lab516/findAuthorityByName";
    private String result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        MaterialLoginView materialLoginViewlogin = (MaterialLoginView) findViewById(R.id.login);
        materialLoginViewlogin.setListener(new MaterialLoginViewListener() {
//            @Override
//            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
//                //Handle register
//            }

            @Override
            public void onLogin(final TextInputLayout loginUser, final TextInputLayout loginPass) {
                final String name=loginUser.getEditText().getText().toString();
                final String password=loginPass.getEditText().getText().toString();
                final StringBuilder endPoint=new StringBuilder("http://");
                endPoint.append(ServiceIp.serviceIp()).append(":8080/WebService-ZHFS/ManageUserServiceWS");
                if (!name.equals("") && !password.equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Looper.prepare();//非主线程中默认没有创建Looper对象，需要先调用Looper.prepare()启用Looper
                            System.out.println("进入OnLogin方法");
                            SoapObject soapObject = new SoapObject(nameSpaceAddress, methodCheckMnameAndPswd);
                            soapObject.addProperty("name", name);
                            soapObject.addProperty("password", password);
                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                            envelope.bodyOut = soapObject;
                            envelope.dotNet = false;
                            envelope.setOutputSoapObject(soapObject);
                            HttpTransportSE httpTransportSE = new HttpTransportSE(endPoint.toString());
                            System.out.println("基本服务设置完毕，下面开始调用服务");
                            try {
                                httpTransportSE.call(soapAction, envelope);
                                System.out.println("调用webservice成功");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("服务调用失败");
                            }
                            SoapObject object = (SoapObject) envelope.bodyIn;
                            System.out.println("调用服务数据成功");
                            result = object.getProperty(0).toString();
                            System.out.println("解析服务数据成功");
                            System.out.println(result);
                            switch (result) {
                                case "404":
                                    Toast.makeText(LoginActivity.this, R.string.no_user, Toast.LENGTH_LONG).show();
                                    break;
                                case "100":
                                    Toast.makeText(LoginActivity.this, R.string.password_error, Toast.LENGTH_LONG).show();
                                    break;
                                case "200":{
                                    Toast.makeText(LoginActivity.this, R.string.login_succeed, Toast.LENGTH_LONG).show();
                                    SoapObject soapObject2 = new SoapObject(nameSpaceAddress, methodFindAuthorityByName);
                                    soapObject2.addProperty("name", name);
                                    SoapSerializationEnvelope envelope2 = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                                    envelope2.bodyOut = soapObject2;
                                    envelope2.dotNet = false;
                                    envelope2.setOutputSoapObject(soapObject2);
                                    HttpTransportSE httpTransportSE2 = new HttpTransportSE(endPoint.toString());
                                    try {
                                        httpTransportSE2.call(soapAction2, envelope2);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    SoapObject object2 = (SoapObject) envelope2.bodyIn;
                                    String authority = object2.getProperty(0).toString();
                                    System.out.println(authority);

//                                    //给WarnService传值
//                                    Intent startIntent = new Intent(LoginActivity.this, WarnService.class);
//                                    startIntent.putExtra("authority", authority);
//                                    startService(startIntent);

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    //采用Intent普通传值的方式
                                    intent.putExtra("authority", authority);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                    break;
                                }
                            }
                            Looper.loop();
                        }
                    }).start();
                }

//                            Toast.makeText(getApplicationContext(), R.string.login_succeed,
//                                    Toast.LENGTH_SHORT).show();
//                            //给WarnService传值
//                            Intent startIntent = new Intent(LoginActivity.this, WarnService.class);
//                            startIntent.putExtra("authority", result.get(0).getAuthority());
//                            startService(startIntent);
//
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            //采用Intent普通传值的方式
//                            intent.putExtra("authority", result.get(0).getAuthority());
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            LoginActivity.this.finish();
//                        }
//
//                    }
//                }else{
//                    Toast.makeText(getApplicationContext(),R.string.Internet_error,
//                            Toast.LENGTH_SHORT).show();
//                }

            }
        });
    }
}
