package com.pos.orderfood.fragment;

import com.pos.orderfood.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

//功能管理
public class FunctionFragment extends NoBugFragment{
	private View view;
	private Context context;
	private ToggleButton tb1;
	private CallBack callback;
	
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_function, null);
		context = get_Activity();
		return view;
	}
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		 
		initView();
	}
	private void initView() {
		 tb1=(ToggleButton) view.findViewById(R.id.tb1);
		 tb1.setChecked(true);
		 tb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				 callback.callback();
			}
		});
	}
	public interface CallBack{
		public void callback();
	}
	public void setCallback(CallBack callback) {
		this.callback = callback;
	}
}
