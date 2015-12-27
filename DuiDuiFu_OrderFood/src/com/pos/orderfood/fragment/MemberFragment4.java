package com.pos.orderfood.fragment;

import com.pos.orderfood.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MemberFragment4 extends NoBugFragment{
private Context context;

@Override
public View oncreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	view = View.inflate(get_Activity(), R.layout.fragment_member1, null);
	context = get_Activity();
	return view;
}
public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	getActivity().getWindow().setSoftInputMode(
			WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	
}}
