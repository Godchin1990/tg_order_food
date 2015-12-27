package com.pos.orderfood.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.pos.orderfood.R;
import com.pos.orderfood.adapter.CashAdapter2;
import com.pos.orderfood.bean.Good;
import com.pos.orderfood.dialog.IntroduceDialog;

public class CashFragment2 extends NoBugFragment implements OnClickListener {
	private Context context;
	private ListView lv_goods;
	private List<Good> list = new ArrayList<Good>();
	private CashAdapter2 adapter2;
	private Button introduce;
	private IntroduceDialog dialog;
	private TextView tv_introduce;

	// 支付方式选择
	private Button cash, mem, card, zfb, wx, baidu, other;
	// 数字键
	private Button btn10, btn20, btn50, btn100, btn7, btn4, btn1, btn8, btn5,
			btn2, btn0, btn9, btn6, btn3, btnpoint, btnEnter;
	private ImageButton btndelete;
	// 输入框
	private EditText et_memberid, et_useaintegral, et_tableNo, et_factmoney,
			et_change, et_vouchers;

	// 判断选择哪个输入框
	private int index = 0;
	// 输入框数据
	private StringBuffer num = new StringBuffer("");

	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_cash2, null);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		InitData();
		InitUI();
	}

	private void InitData() {
		// TODO Auto-generated method stub
		Good g = new Good();
		g.setName("菲林咖啡");
		g.setPrice("20.00");
		g.setSize("中");
		g.setNum(1);
		list.add(g);
		g = new Good();
		g.setName("曼特宁");
		g.setPrice("75.00");
		g.setSize("加奶");
		g.setNum(1);
		list.add(g);
		g = new Good();
		g.setName("红豆可可");
		g.setPrice("15.00");
		g.setSize("大杯");
		g.setNum(1);
		list.add(g);
	}

	private void InitUI() {
		// TODO Auto-generated method stub
		tv_introduce = (TextView) view.findViewById(R.id.tv_introduce);
		lv_goods = (ListView) view.findViewById(R.id.lv_goods);
		adapter2 = new CashAdapter2(context, list);
		lv_goods.setAdapter(adapter2);
		adapter2.setCallback(new CashAdapter2.CallBack() {

			@Override
			public void setnum(int position, int count) {
				// TODO Auto-generated method stub
				list.get(position).setNum(count);
				adapter2.update(list);
			}

			@Override
			public void delete(int position) {
				// TODO Auto-generated method stub
				list.remove(position);
				adapter2.update(list);
			}
		});
		introduce = (Button) view.findViewById(R.id.btn_introduce);
		introduce.setOnClickListener(this);

		cash = (Button) view.findViewById(R.id.btn_cash);
		mem = (Button) view.findViewById(R.id.btn_mem);
		card = (Button) view.findViewById(R.id.btn_card);
		zfb = (Button) view.findViewById(R.id.btn_zfb);
		wx = (Button) view.findViewById(R.id.btn_wx);
		baidu = (Button) view.findViewById(R.id.btn_baidu);
		other = (Button) view.findViewById(R.id.btn_other);
		cash.setOnClickListener(this);
		mem.setOnClickListener(this);
		card.setOnClickListener(this);
		zfb.setOnClickListener(this);
		wx.setOnClickListener(this);
		baidu.setOnClickListener(this);
		other.setOnClickListener(this);

		btn10 = (Button) view.findViewById(R.id.btn10);
		btn20 = (Button) view.findViewById(R.id.btn20);
		btn50 = (Button) view.findViewById(R.id.btn50);
		btn100 = (Button) view.findViewById(R.id.btn100);
		btn7 = (Button) view.findViewById(R.id.btn7);
		btn4 = (Button) view.findViewById(R.id.btn4);
		btn1 = (Button) view.findViewById(R.id.btn1);
		btn8 = (Button) view.findViewById(R.id.btn8);
		btn5 = (Button) view.findViewById(R.id.btn5);
		btn2 = (Button) view.findViewById(R.id.btn2);
		btn0 = (Button) view.findViewById(R.id.btn0);
		btn9 = (Button) view.findViewById(R.id.btn9);
		btn6 = (Button) view.findViewById(R.id.btn6);
		btn3 = (Button) view.findViewById(R.id.btn3);
		btnpoint = (Button) view.findViewById(R.id.btnpoint);
		btnEnter = (Button) view.findViewById(R.id.btnEnter);
		btndelete = (ImageButton) view.findViewById(R.id.btndelete);
		btn10.setOnClickListener(this);
		btn20.setOnClickListener(this);
		btn50.setOnClickListener(this);
		btn100.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn0.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btnpoint.setOnClickListener(this);
		btnEnter.setOnClickListener(this);
		btndelete.setOnClickListener(this);

		et_memberid = (EditText) view.findViewById(R.id.et_memberid);
		et_useaintegral = (EditText) view.findViewById(R.id.et_useaintegral);
		et_tableNo = (EditText) view.findViewById(R.id.et_tableNo);
		et_factmoney = (EditText) view.findViewById(R.id.et_factmoney);
		et_change = (EditText) view.findViewById(R.id.et_change);
		et_vouchers = (EditText) view.findViewById(R.id.et_vouchers);
		et_memberid.setOnClickListener(this);
		et_useaintegral.setOnClickListener(this);
		et_tableNo.setOnClickListener(this);
		et_factmoney.setOnClickListener(this);
 
		et_vouchers.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_introduce: {
			dialog = new IntroduceDialog(context, tv_introduce.getText()
					.toString());

			dialog.setCallback(new IntroduceDialog.CallBack() {

				@Override
				public void callback(String str) {
					// TODO Auto-generated method stub
					tv_introduce.setText(str);
				}
			});
			dialog.show();
		}
			break;

		case R.id.btn_cash: {
			setpaybtn();
			cash.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
		case R.id.btn_mem: {
			setpaybtn();
			mem.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
		case R.id.btn_card: {
			setpaybtn();
			card.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
		case R.id.btn_zfb: {
			setpaybtn();
			zfb.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
		case R.id.btn_wx: {
			setpaybtn();
			wx.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
		case R.id.btn_baidu: {
			setpaybtn();
			baidu.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
		case R.id.btn_other: {
			setpaybtn();
			other.setBackgroundResource(R.drawable.pay_btn_press);
		}
			break;
	   
			
			
		case R.id.et_memberid: {// 1
			if(index!=1){
				num.delete(0, num.length());
			}
			index=1;
			//TipsToast.makeText(context, "show", 1).show();
		}
			break;
		case R.id.et_useaintegral: {// 2
			if(index!=2){
				num.delete(0, num.length());
			}
			index=2;
			//TipsToast.makeText(context, "show", 1).show();
		}
			break;
		case R.id.et_tableNo: {// 3
			if(index!=3){
				num.delete(0, num.length());
			}
			index=3;
			//TipsToast.makeText(context, "show", 1).show();
		}
			break;
		case R.id.et_factmoney: {// 4
			if(index!=4){
				num.delete(0, num.length());
			}
			index=4;
			//TipsToast.makeText(context, "show", 1).show();
		}
			break;

		case R.id.et_vouchers: {// 5
			if(index!=5){
				num.delete(0, num.length());
			}
			index=5;
			//TipsToast.makeText(context, "show", 1).show();
		}
			break;
	 
		case R.id.btnEnter: {
			
			if(index==4){
				//et_change
				DecimalFormat df = new DecimalFormat("#.##");
				double d=Double.parseDouble(num.toString())-88.00;
				et_change.setText(df.format(d));
			}
			num.delete(0, num.length());
			index = 0;
		}
			break;
		case R.id.btndelete: {
			if (num.length() > 0) {
				num.delete(num.length() - 1, num.length());
				findfouse();
			}
		}
			break;
		case R.id.btn10: {
			num.append("10");
			findfouse();
		}
			break;
		case R.id.btn20: {
			num.append("20");
			findfouse();
		}
			break;
		case R.id.btn50: {
			num.append("50");
			findfouse();
		}
			break;
		case R.id.btn100: {
			num.append("100");
			findfouse();
		}
			break;
		case R.id.btn0: {
			num.append("0");
			findfouse();
		}
			break;
		case R.id.btn1: {
			num.append("1");
			findfouse();
		}
			break;
		case R.id.btn2: {
			num.append("2");
			findfouse();
		}
			break;
		case R.id.btn3: {
			num.append("3");
			findfouse();
		}
			break;
		case R.id.btn4: {
			num.append("4");
			findfouse();
		}
			break;
		case R.id.btn5: {
			num.append("5");
			findfouse();
		}
			break;
		case R.id.btn6: {
			num.append("6");
			findfouse();
		}
			break;
		case R.id.btn7: {
			num.append("7");
			findfouse();
		}
			break;
		case R.id.btn8: {
			num.append("8");
			findfouse();
		}
			break;
		case R.id.btn9: {
			num.append("9");
			findfouse();
		}
			break;
		case R.id.btnpoint: {
			num.append(".");
			findfouse();
		}
			break;
		default:
			break;
		}
	}

	public void setpaybtn() {

		cash.setBackgroundResource(R.drawable.pay_btn_normal);
		mem.setBackgroundResource(R.drawable.pay_btn_normal);
		card.setBackgroundResource(R.drawable.pay_btn_normal);
		zfb.setBackgroundResource(R.drawable.pay_btn_normal);
		wx.setBackgroundResource(R.drawable.pay_btn_normal);
		baidu.setBackgroundResource(R.drawable.pay_btn_normal);
		other.setBackgroundResource(R.drawable.pay_btn_normal);

	}
	public void findfouse(){
		/* et_memberid, et_useaintegral, et_tableNo, et_factmoney,
			, et_vouchers;*/
		if(index==1){
			et_memberid.setText(num.toString());
		}else if(index==2){
			et_useaintegral.setText(num.toString());
		}else if(index==3){
			et_tableNo.setText(num.toString());
		}else if(index==4){
			et_factmoney.setText(num.toString());
		}else if(index==5){
			et_vouchers.setText(num.toString());
		}
	}
}
