package com.pos.orderfood.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.duiduifu.util.AppConstantByUtil;
import com.duiduifu.view.TipsToast;
import com.pos.orderfood.R;
import com.pos.orderfood.push.MyApplication;
import com.pos.orderfood.widget.Loading;

public class HomeActivity extends BaseActivity implements OnClickListener{
	private HomeActivity homeActivity;
	private Context context;
	private ImageView cash,order,goods,member,account,kitchen;
	private Handler mMainHadler;

	Loading loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		loading = new Loading(context);

		((MyApplication) getApplication()).addActivity(this);

		setContentView(R.layout.activity_home);
		init();
	}

	public void init() {
		 //private ImageView cash,order,goods,member,account,kitchen;
		cash=(ImageView) findViewById(R.id.iv_cash);
		order=(ImageView) findViewById(R.id.iv_order);
		goods=(ImageView) findViewById(R.id.iv_goods);
		member=(ImageView) findViewById(R.id.iv_member);
		account=(ImageView) findViewById(R.id.iv_account);
		kitchen=(ImageView) findViewById(R.id.iv_kitchen);
		order.setOnClickListener(this);
		goods.setOnClickListener(this);
		member.setOnClickListener(this);
		account.setOnClickListener(this);
		kitchen.setOnClickListener(this);
		cash.setOnClickListener(this);
		mMainHadler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				switch (msg.what) {

				case 0:
					TipsToast.makeText(homeActivity, msg.obj.toString(), 1000)
							.show();
					loading.cancel();
					break;

				case 1:
					// mCommonUtil.PreferenceSaveS("pwd",
					// MD5Util.createSign(mPasswordEditText.getText().toString()));
					// mCommonUtil.PreferenceSaveS("loginUser",
					// mUserNameEditText.getText().toString());

					Intent intent = new Intent(context, LoginActivity.class);
					overridePendingTransition(R.anim.push_right_in,
							R.anim.push_right_out);
					startActivityForResult(intent, 0);
					// mPasswordEditText.setText("");
					try {
						Intent i = new Intent();
						intent.setAction("com.duidufu.login.success");// 发出自定义广播
						// LoginActivity.this.sendBroadcast(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					loading.cancel();
					break;
				case 2:
					((MyApplication) getApplication()).AppExit(context);
					TipsToast.makeText(homeActivity, "退出", 1000).show();
					loading.cancel();
					break;

				case 13:
					loading.cancel();
					TipsToast.makeText(homeActivity, msg.obj.toString(), 1000)
							.show();
					break;

				default:
					break;
				}
			}
		};

	}

	private void LogOut() {
		sendMessageToHandler(AppConstantByUtil.SUCCESS, "");
		/*
		 * HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		 * String userId = Constants.shop.getUserId(); String loginSign =
		 * Constants.shop.getLoginSign(); String sign =
		 * MD5Util.createSign(loginSign + Constants.terminalId + userId);
		 * 
		 * mHashMap.put("terminalId", Constants.terminalId);
		 * mHashMap.put("userId", userId); mHashMap.put("loginSign", loginSign);
		 * mHashMap.put("deviceId", "123456"); if
		 * (!StringUtil.isEmpty(Constants.channelId)) mHashMap.put("channelId",
		 * Constants.channelId); mHashMap.put("sign", sign); Log.i("pos",
		 * Constants.terminalId); loading.show();
		 * AsyncRunner.getInstance().request( AppConstantByUtil.HOST +
		 * "ShopLogin/logout.do", "GET", mHashMap.entrySet(), new
		 * AsyncListener() {
		 * 
		 * @Override public void onComplete(String values) { // TODO
		 * Auto-generated method stub
		 * 
		 * try {
		 * 
		 * JSONObject jsonObject = new JSONObject(values);
		 * 
		 * String msg = jsonObject.getString("message");
		 * 
		 * String code = jsonObject.getString("code"); Log.i("pos", values); if
		 * (code.equals(String .valueOf(AppConstantByUtil.SUCCESS))) {
		 * //Constants.shop = null;
		 * sendMessageToHandler(AppConstantByUtil.SUCCESS, msg); } else if
		 * (code.equals("-1")) { sendMessageToHandler(AppConstantByUtil.ERROR,
		 * msg);
		 * 
		 * } } catch (Exception e) { e.printStackTrace(); } }
		 * 
		 * @Override public void onException(Object o) { // TODO Auto-generated
		 * method stub }
		 * 
		 * });
		 */
	}

	public void sendMessageToHandler(int what, String msg) {

		Message message = mMainHadler.obtainMessage();
		message.what = what;
		message.obj = msg;
		mMainHadler.sendMessage(message);
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_cash:
			Intent intent1=new Intent(context,MainActivity.class);
			intent1.putExtra("index", 1);
			startActivity(intent1);
			 
			break;
		case R.id.iv_order:
			Intent intent2=new Intent(context,MainActivity.class);
			intent2.putExtra("index", 2);
			startActivity(intent2);
			break;
		case R.id.iv_goods:
			Intent intent3=new Intent(context,MainActivity.class);
			intent3.putExtra("index", 3);
			startActivity(intent3);
			break;
		case R.id.iv_member:
			Intent intent4=new Intent(context,MainActivity.class);
			intent4.putExtra("index", 4);
			startActivity(intent4);
			break;
		case R.id.iv_account:
			Intent intent5=new Intent(context,MainActivity.class);
			intent5.putExtra("index", 6);
			startActivity(intent5);
			break;
		case R.id.iv_kitchen:
			Intent intent6=new Intent(context,MainActivity.class);
			intent6.putExtra("index", 7);
			startActivity(intent6);
			break;
		default:
			break;
		}
	}
}
