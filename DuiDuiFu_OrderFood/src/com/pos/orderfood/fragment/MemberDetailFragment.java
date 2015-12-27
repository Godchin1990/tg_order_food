package com.pos.orderfood.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pos.orderfood.R;

public class MemberDetailFragment extends NoBugFragment implements
		OnClickListener {
	private Context context;
	private Button back;
	private Callback callback;
	private RadioGroup rg,rg1;
	private LinearLayout orders,profit;
	private LayoutInflater mInflater;
	// private RadioButton month,year,quarter;
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_memberdetail,
				null);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		InitUI();
		InitData();
	}
//添加初始化数据
	private void InitData() {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			View v=mInflater.inflate(R.layout.records, orders, false);
			orders.addView(v);
		}
		for(int i=0;i<3;i++){
			View v=mInflater.inflate(R.layout.profit, profit, false);
			profit.addView(v);
		}
	}

	private void InitUI() {
		// TODO Auto-generated method stub
		orders=(LinearLayout) view.findViewById(R.id.ll_orders);
		profit=(LinearLayout) view.findViewById(R.id.ll_profit);
		mInflater = LayoutInflater.from(context);
		back = (Button) view.findViewById(R.id.btn_back);
		back.setOnClickListener(this);
		rg = (RadioGroup) view.findViewById(R.id.rg_bydate);
		rg1 = (RadioGroup) view.findViewById(R.id.rg_bydate1);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_month: {
					orders.removeAllViews();
					for(int i=0;i<3;i++){
						View v=mInflater.inflate(R.layout.records, orders, false);
						orders.addView(v);
					}
				}
					break;
				case R.id.rb_year: {
					orders.removeAllViews();
					for(int i=0;i<3;i++){
						View v=mInflater.inflate(R.layout.records, orders, false);
						orders.addView(v);
					}
					View v=mInflater.inflate(R.layout.records, orders, false);
					TextView date=(TextView) v.findViewById(R.id.tv_date);
					date.setText("2015/09/15 14:00");
					orders.addView(v);
					View v1=mInflater.inflate(R.layout.records, orders, false);
					TextView date1=(TextView) v1.findViewById(R.id.tv_date);
					date1.setText("2015/06/15 14:00");
					orders.addView(v1);
				}
					break;
				case R.id.rb_quarter: {
					orders.removeAllViews();
					for(int i=0;i<3;i++){
						View v=mInflater.inflate(R.layout.records, orders, false);
						orders.addView(v);
					}
					View v=mInflater.inflate(R.layout.records, orders, false);
					TextView date=(TextView) v.findViewById(R.id.tv_date);
					date.setText("2015/09/15 14:00");
					orders.addView(v);
					
				}
					break;
				default:
					break;
				}
			}
		});
		rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_month1: {
					profit.removeAllViews();
					for(int i=0;i<3;i++){
						View v=mInflater.inflate(R.layout.profit, profit, false);
						profit.addView(v);
					}
				}
					break;
				case R.id.rb_year1: {
					profit.removeAllViews();
					for(int i=0;i<3;i++){
						View v=mInflater.inflate(R.layout.profit, profit, false);
						profit.addView(v);
					}
					View v=mInflater.inflate(R.layout.profit, profit, false);
					TextView date=(TextView) v.findViewById(R.id.tv_date);
					date.setText("2015/09/15 14:00");
					profit.addView(v);
					View v1=mInflater.inflate(R.layout.profit, profit, false);
					TextView date1=(TextView) v1.findViewById(R.id.tv_date);
					date1.setText("2015/06/15 14:00");
					orders.addView(v1);
				}
					break;
				case R.id.rb_quarter1: {
					profit.removeAllViews();
					for(int i=0;i<3;i++){
						View v=mInflater.inflate(R.layout.profit, profit, false);
						profit.addView(v);
					}
					View v=mInflater.inflate(R.layout.profit, profit, false);
					TextView date=(TextView) v.findViewById(R.id.tv_date);
					date.setText("2015/09/15 14:00");
					profit.addView(v);
					
				}
					break;
				default:
					break;
				}
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back: {
			callback.callback();
		}

			break;

		default:
			break;
		}
	}

	public interface Callback {
		public void callback();
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

}
