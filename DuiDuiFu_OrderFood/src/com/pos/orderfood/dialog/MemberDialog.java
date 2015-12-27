package com.pos.orderfood.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.zxing.WriterException;
import com.pos.orderfood.R;
import com.pos.orderfood.printcode.EncodingHandler;

public class MemberDialog extends BaseDialog{
	Context mContext;
	private ImageView iv_QRcode;
	public MemberDialog(Context mContext) {
		super(mContext);
		this.mContext = mContext;
		//HideKeyboard();
		initUI();
	}
	private void initUI() {
		View v = getLayoutInflater().inflate(R.layout.dialog_member ,null);
		iv_QRcode=(ImageView) v.findViewById(R.id.iv_QRcode);
		Bitmap map;
		try {
			map = EncodingHandler.createQRCode("fafafaf", 300);
			iv_QRcode.setImageBitmap(map);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(750,
				500);
		this.addContentView(v, lp);
	}
	/*private void HideKeyboard() {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(mContext.INPUT_METHOD_SERVICE);

		if(((Activity) mContext).getCurrentFocus()!=null)
		imm.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus()
				.getWindowToken(), 0);

		if (imm.isActive()) { // 一直是true
			
			 * imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
			 * InputMethodManager.HIDE_IMPLICIT_ONLY);
			 
			getActivity().getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		}
	}*/

}
