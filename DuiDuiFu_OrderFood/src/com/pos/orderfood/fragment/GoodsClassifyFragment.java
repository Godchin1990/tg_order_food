package com.pos.orderfood.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pos.orderfood.R;
import com.pos.orderfood.adapter.LableEditAdapter;
import com.pos.orderfood.widget.DragSortListView;
import com.pos.orderfood.widget.DragSortListView.DropListener;

public class GoodsClassifyFragment extends NoBugFragment implements
		OnClickListener {
	private Context context;
	private DragSortListView lv_drag;
	private LableEditAdapter adapter;
	private List<String> items = new ArrayList<String>();
	private Button btn_addattribute, btn_addcategory, btn_revoke, btn_ok;
	private LinearLayout ll_attribute, ll_category;
	private TextView tv_name;
	private LayoutInflater mInflater;
	private View arg;

	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_goodsclassify,
				null);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		InitData();
		InitUI();
		AddTestData();
	}

	private void AddTestData() {
		// TODO Auto-generated method stub
		// 添加属性
		for (int i = 0; i < 3; i++) {

			arg = mInflater.inflate(R.layout.goods_attribute, ll_attribute,
					false);
			LinearLayout ll_delete = (LinearLayout) arg
					.findViewById(R.id.ll_delete);
			arg.setClickable(true);
			ll_attribute.addView(arg);
			arg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (ll_category.getChildCount() <= 0) {
						View arg1 = mInflater.inflate(R.layout.goods_attribute,
								ll_category, false);
						TextView tv_attribute = (TextView) arg1
								.findViewById(R.id.tv_attribute);
						tv_attribute.setText("属性名称");
						LinearLayout delete = (LinearLayout) arg1
								.findViewById(R.id.ll_delete);
						delete.setVisibility(View.INVISIBLE);
						ll_category.addView(arg1);
						arg.setClickable(false);
					}
				}
			});
			ll_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ll_category.removeView(arg);
				}
			});

		}
		// 添加类别

		for (int i = 0; i < 3; i++) {

			arg = mInflater.inflate(R.layout.goods_attribute, ll_category,
					false);
			LinearLayout ll_delete = (LinearLayout) arg
					.findViewById(R.id.ll_delete);

			TextView tv_attribute = (TextView) arg
					.findViewById(R.id.tv_attribute);
			if (i == 0) {
				tv_attribute.setText("属性名称");

			} else
				tv_attribute.setText("类别");
			arg.setClickable(true);
			ll_category.addView(arg);

			ll_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ll_category.removeView(arg);
				}
			});

		}
	}

	private void InitData() {
		// TODO Auto-generated method stub
		items.add("花式咖啡");
		items.add("单品咖啡");
		items.add("果汁");
	}

	private void InitUI() {
		// TODO Auto-generated method stub
		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		btn_revoke = (Button) view.findViewById(R.id.btn_revoke);
		btn_revoke.setOnClickListener(this);
		mInflater = LayoutInflater.from(context);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		ll_attribute = (LinearLayout) view.findViewById(R.id.ll_attribute);
		ll_category = (LinearLayout) view.findViewById(R.id.ll_category);
		btn_addcategory = (Button) view.findViewById(R.id.btn_addcategory);
		btn_addcategory.setOnClickListener(this);
		btn_addattribute = (Button) view.findViewById(R.id.btn_addattribute);
		btn_addattribute.setOnClickListener(this);
		lv_drag = (DragSortListView) view.findViewById(R.id.lv_category);
		adapter = new LableEditAdapter(context, items);
		lv_drag.setAdapter(adapter);
		lv_drag.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				tv_name.setText(items.get(position));
			}
		});
		lv_drag.setDropListener(new DropListener() {

			@Override
			public void drop(int from, int to) {
				// TODO Auto-generated method stub
				String shop = items.remove(from);
				items.add(to, shop);
				adapter.refresh(items);
			}
		});
		lv_drag.setDragScrollProfile(ssProfile);
	}

	private DragSortListView.DragScrollProfile ssProfile = new DragSortListView.DragScrollProfile() {
		@Override
		public float getSpeed(float w, long t) {
			if (w > 0.8f) {
				// Traverse all views in a millisecond
				return (adapter.getCount()) / 0.001f;
			} else {
				return 10.0f * w;
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok: {
			ll_category.removeAllViews();
		}
			break;
		case R.id.btn_revoke: {
			if (ll_category.getChildCount() > 0) {
				ll_category.removeViewAt(ll_category.getChildCount() - 1);
			}
		}
			break;
		case R.id.btn_addattribute: {
			arg = mInflater.inflate(R.layout.goods_attribute, ll_attribute,
					false);
			LinearLayout ll_delete = (LinearLayout) arg
					.findViewById(R.id.ll_delete);
			arg.setClickable(true);
			ll_attribute.addView(arg);
			arg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (ll_category.getChildCount() <= 0) {
						View arg1 = mInflater.inflate(R.layout.goods_attribute,
								ll_category, false);
						TextView tv_attribute = (TextView) arg1
								.findViewById(R.id.tv_attribute);
						tv_attribute.setText("属性名称");
						LinearLayout delete = (LinearLayout) arg1
								.findViewById(R.id.ll_delete);
						delete.setVisibility(View.INVISIBLE);
						ll_category.addView(arg1);
						arg.setClickable(false);
					}
				}
			});
			ll_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ll_category.removeView(arg);
				}
			});
		}
			break;
		case R.id.btn_addcategory: {
			if (ll_category.getChildCount() > 0) {
				arg = mInflater.inflate(R.layout.goods_attribute, ll_category,
						false);
				arg.setClickable(true);
				LinearLayout ll_delete = (LinearLayout) arg
						.findViewById(R.id.ll_delete);

				TextView tv_attribute = (TextView) arg
						.findViewById(R.id.tv_attribute);
				tv_attribute.setText("类别");
				ll_category.addView(arg);
				ll_delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ll_category.removeView(arg);
					}
				});
			}
		}
			break;
		default:
			break;
		}
	}
}
