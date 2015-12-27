package com.pos.orderfood.push;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.androidlibrary.util.image.BitmapManager;
import com.baidu.frontia.FrontiaApplication;
import com.duiduifu.db.DbUtil;
import com.duiduifu.imageloader.ImageLoader;
import com.pos.orderfood.activity.HomeActivity;
 

/*
 * 如果您的工程中实现了Application的继承类，那么，您需要将父类改为com.baidu.frontia.FrontiaApplication。
 * 如果您没有实现Application的继承类，那么，请在AndroidManifest.xml的Application标签中增加属性： 
 * <application android:name="com.baidu.frontia.FrontiaApplication"
 * 。。。
 */
public class MyApplication extends FrontiaApplication implements UncaughtExceptionHandler{
	
	public static Stack<Activity> activityStack = new Stack<Activity>();
	public DbUtil dbUtil = new DbUtil(this);
	public ImageLoader imageLoader;
	public BitmapManager bitmapManager;
	
    @Override
    public void onCreate() {
        super.onCreate();

        // 以下是您原先的代码实现，保持不变
        mInstants = this;
		imageLoader = new ImageLoader(this, "icon");
	    Thread.setDefaultUncaughtExceptionHandler(this);
    }
    private static MyApplication mInstants = null;
	public static MyApplication getInstants(){
		return mInstants;
	}
	
	public DbUtil getDbUtil(){
		return dbUtil;
	}
	
	
	public ImageLoader getImageLoader(){
		return imageLoader;
	}
	public BitmapManager getBitmapManger(){
		return bitmapManager;
	}
	
	public void addActivity(Activity activity){
		try {
			if(activityStack==null){
				activityStack=new Stack<Activity>();
			}
			activityStack.add(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Activity currentActivity(){
		Activity activity=activityStack.lastElement();
		return activity;
	}
	
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	
	public void finishActivity(Activity activity){
		try {
			if(activity!=null){
				activityStack.remove(activity);
				activity.finish();
				activity=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finishActivity(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				finishActivity(activity);
			}
		}
	}
	
	public void removeOnly(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				if(activity!=null){
					activityStack.remove(activity);
				}
			}
		}
	}
	
	public void finishAllActivity(){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
            	activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	
	
	public void finishAllExitThisActivity(Class<?> cls){
		for (int i = 0; i <  activityStack.size(); i++){
            if (null != activityStack.get(i)){
        			if(activityStack.get(i).getClass().equals(cls) ){
        				continue;
        			}
        			finishActivity(activityStack.get(i));
            }
	    }
	}
	
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}

	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		try {
			Log.e("error", "ncaughtException: "+getErrorInfo(arg1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(isAppInstalled("com.duiduifu.upload")){
			Intent intent = new Intent();
			ComponentName component = new ComponentName("com.duiduifu.upload", "com.duiduifu.upload.app.MainActivity");
			intent.putExtra("error", getErrorInfo(arg1));
			intent.setComponent(component);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	       this.startActivity(intent);
		}
		
		try {
			finishAllExitThisActivity(HomeActivity.class);
			android.os.Process.killProcess(android.os.Process.myPid());
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	 private String getErrorInfo(Throwable arg1) {
		  Writer writer = new StringWriter();
		  PrintWriter pw = new PrintWriter(writer);
		  arg1.printStackTrace(pw);
		  pw.close();
		  String error= writer.toString();
		  return error;
	 }
	 
	 
	public boolean isAppInstalled(String uri) {
		
		PackageManager pm = this.getPackageManager();
		
		boolean installed = false;
		
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			installed = false;
		}
		
		return installed;
	}
	 
}

