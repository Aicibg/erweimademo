package com.app.erweima;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity {
  private Button btScan,btMake;
  private CheckBox cbLogo;
  private EditText etInput;
  private ImageView ivResult;
  private TextView tvResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
		btMake.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String input=etInput.getText().toString();
				if(input.equals("")){
					Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					Bitmap bitmap=EncodingUtils.createQRCode(input, 500,500, cbLogo.isChecked()?
							BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher):null);
					ivResult.setImageBitmap(bitmap);
				}
			}
		});
		
		btScan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,CaptureActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	private void initView() {
		btMake=(Button) findViewById(R.id.bt_make);
		btScan=(Button) findViewById(R.id.bt_scan);
		etInput=(EditText) findViewById(R.id.et_text);
		tvResult=(TextView) findViewById(R.id.tv_result);
		ivResult=(ImageView) findViewById(R.id.iv_image);
		cbLogo=(CheckBox) findViewById(R.id.cb_check);
	}
	
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			if(resultCode==RESULT_OK){
				Bundle bundle=data.getExtras();
				String result=bundle.getString("result");
				tvResult.setText(result);
			}
		}

}
