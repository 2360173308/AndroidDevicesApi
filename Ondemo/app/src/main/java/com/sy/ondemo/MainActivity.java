package com.sy.ondemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.sy.ondemo.utils.SuUtils;
import com.sy.ondemo.utils.SystemUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_app_install;
    private Button button_app_uninstall;
    private Button button_appsc;
    private Button button_app_root;
    private Button button_app_screen;
    private Button button_port_reset;
    private Button button_app_time;
    private Button button_sethwrotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        button_app_install = (Button) findViewById(R.id.button_app_install);
        button_app_install.setOnClickListener(this);
        button_app_uninstall = (Button) findViewById(R.id.button_app_uninstall);
        button_app_uninstall.setOnClickListener(this);
        button_appsc = (Button) findViewById(R.id.button_appsc);
        button_appsc.setOnClickListener(this);
        button_app_root = (Button) findViewById(R.id.button_app_root);
        button_app_root.setOnClickListener(this);

        button_app_screen = (Button) findViewById(R.id.button_app_screen);
        button_app_screen.setOnClickListener(this);
        button_port_reset = (Button) findViewById(R.id.button_port_reset);
        button_port_reset.setOnClickListener(this);
        button_app_time = (Button) findViewById(R.id.button_app_time);
        button_app_time.setOnClickListener(this);


        button_sethwrotation = (Button) findViewById(R.id.button_sethwrotation);
        button_sethwrotation.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_app_install://静默安装
                SuUtils.StartCommand("install","");
                break;
            case R.id.button_app_uninstall://静默卸载
                SuUtils.StartCommand("uninstall","");
            case R.id.button_appsc:
                SuUtils.killProcess("com.demo");
                break;
            case R.id.button_app_root:
                SuUtils.roobt();
                break;
            case R.id.button_app_screen:
                SuUtils.screenShot("");
                break;
            case R.id.button_port_reset:
                SuUtils.portReset();
                break;
            case R.id.button_app_time:
                SuUtils.testDate();
                break;
            case R.id.button_sethwrotation://旋转屏幕
                SystemUtils.sethwrotation("90");
                break;
        }
    }
}
