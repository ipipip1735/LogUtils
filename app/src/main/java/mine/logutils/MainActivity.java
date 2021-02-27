package mine.logutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileFilter;
import com.apkfuns.logutils.file.LogFileParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

        //日志输出
//        LogUtils.v("vvv");
//        LogUtils.d("ddd");
//        LogUtils.i("iii");
//        LogUtils.w("www");
//        LogUtils.e("eee");


        //设置Tag
        LogUtils.i("iii");
        LogUtils.tag("ttt");//仅对后面一条日志有效果
        LogUtils.i("iii");
        LogUtils.i("iii");

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        //禁用日志
//        LogUtils.getLogConfig().configAllowLog(true);//禁用日志
//        LogUtils.i("iii");
//        LogUtils.getLogConfig().configAllowLog(false);//启用日志
//        LogUtils.i("iii");


        //配置Tag前缀
//        LogUtils.getLogConfig().configTagPrefix("xxx");
//        LogUtils.i("iii");
//        LogUtils.i("iii");

        //首行信息
//        LogUtils.getLogConfig().configFormatTag("%d{HH:mm:ss:SSS} %t %c");
//        LogUtils.i("iii");


        //配置打印边框
//        LogUtils.getLogConfig().configShowBorders(false);
//        LogUtils.i("iii");
//        LogUtils.getLogConfig().configShowBorders(true);
//        LogUtils.i("iii");


        //配置日志级别
//        LogUtils.getLogConfig().configLevel(LogLevel.TYPE_INFO);
//        LogUtils.v("vvv");
//        LogUtils.d("ddd");
//        LogUtils.i("iii");
//        LogUtils.w("www");
//        LogUtils.e("eee");


        //配置方法栈偏移量
//        LogUtils.getLogConfig().configMethodOffset(0);
//        LogUtils.i("iii");


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");


        //日志Java容器
        LogUtils.i(Arrays.asList(1, 2, 4, 5, 6));


        //自定义对象解析器
//        LogUtils.getLogConfig().addParserClass(PersonParser.class);
//        LogUtils.i(new Person());


    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        //方式一：文件日志，GitHub样例
//        String filePath = Environment.getExternalStorageDirectory() + "/LogUtils/logs/";
//        LogUtils.getLog2FileConfig()
//                .configLog2FileEnable(true)  // 是否输出日志到文件
//                .configLog2FilePath(filePath)  // 日志路径
//                .configLog2FileNameFormat("app-%d{yyyyMMdd}.txt") // 日志文件名称
//                .configLog2FileLevel(LogLevel.TYPE_VERBOSE) // 文件日志等级
////                .configLogFileEngine(new LogFileEngineFactory(this)) // 日志文件引擎实现
//                .configLogFileFilter(new LogFileFilter() {  // 文件日志过滤
//                    @Override
//                    public boolean accept(int level, String tag, String logContent) {
//                        return true;
//                    }
//                });


        //方式二：文件日志，自定义引擎
        File file = getExternalFilesDir("ss/tt/");
        System.out.println(file);
        System.out.println(file.getPath());
        LogUtils.getLog2FileConfig()
                .configLog2FileEnable(true)
                .configLog2FilePath(file.getPath())
                .configLogFileEngine(new LogFileEngine() {
                    @Override
                    public void writeToFile(File logFile, String logContent, LogFileParam params) {
                        System.out.println("~~" + getClass().getSimpleName() + ".writeToFile~~");
                        System.out.println("logFile = " + logFile + ", logContent = " + logContent + ", params = " + params);

                        //文件日志
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(logFile);
                            fileOutputStream.write(logContent.getBytes(UTF_8));
                            fileOutputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void flushAsync() {
                        System.out.println("~~" + getClass().getSimpleName() + ".flushAsync~~");

                    }

                    @Override
                    public void release() {
                        System.out.println("~~" + getClass().getSimpleName() + ".release~~");
                    }
                });
        LogUtils.i(Arrays.asList(1, 2, 4, 5, 6));


    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }
}