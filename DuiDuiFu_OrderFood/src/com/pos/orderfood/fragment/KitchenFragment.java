package com.pos.orderfood.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pos.orderfood.R;
import com.pos.orderfood.dialog.GoodsCloseDialog;

//厨房端
public class KitchenFragment extends NoBugFragment implements OnClickListener {
	private Context context;
	private int[] mImgIds, mImgIds2;
	private LinearLayout order, wait;
	private LayoutInflater mInflater;
	private Button btn_close;
	private GoodsCloseDialog dialog;
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_kitchen, null);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		mInflater = LayoutInflater.from(context);
		initData();
		initView();
	}

	private void initData() {
		mImgIds = new int[] { R.drawable.k1, R.drawable.k2, R.drawable.k3,
				R.drawable.k4, R.drawable.k1, R.drawable.k1 };
		mImgIds2 = new int[] { R.drawable.k21, R.drawable.k22, R.drawable.k23,
				R.drawable.k24, R.drawable.k21, R.drawable.k21 };
		
	}

	private void initView() {
		btn_close = (Button) view.findViewById(R.id.btn_close);
		btn_close.setOnClickListener(this);
		order = (LinearLayout) view.findViewById(R.id.id_order);
		wait = (LinearLayout) view.findViewById(R.id.id_wait);

		for (int i = 0; i < mImgIds.length; i++) {

			final View view = mInflater.inflate(R.layout.item_temporary, order,
					false);
			ImageView img = (ImageView) view
					.findViewById(R.id.id_index_gallery_item_image);
			img.setImageResource(mImgIds[i]);
			Button ok = (Button) view.findViewById(R.id.btn_ok);
			ok.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					order.removeView(view);
				}
			});
			order.addView(view);
		}
		for (int i = 0; i < mImgIds2.length; i++) {

			final View view = mInflater.inflate(R.layout.item_temporary, wait,
					false);
			ImageView img = (ImageView) view
					.findViewById(R.id.id_index_gallery_item_image);
			img.setImageResource(mImgIds2[i]);
			Button ok = (Button) view.findViewById(R.id.btn_ok);
			ok.setText("制作");
			ok.setBackgroundColor(Color.parseColor("#196062"));
			ok.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					wait.removeView(view);
				}
			});
			wait.addView(view);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_close) {
			dialog=new GoodsCloseDialog(context);
			dialog.show();
		}
	}

	
}
