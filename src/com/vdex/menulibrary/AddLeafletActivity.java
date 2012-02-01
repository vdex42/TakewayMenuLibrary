package com.vdex.menulibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AddLeafletActivity extends Activity {

	protected static final int MEDIA_IMAGE_REQUEST_CODE = 0;
	Button btnTakePhoto;
	ImageView imgNewLeaflet;
	private Uri fileUri;
	public static final int MEDIA_TYPE_IMAGE = 1;
	protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 2;

	private AddLeafletActivity Context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Context = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_leaflet);

		btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
		imgNewLeaflet = (ImageView) findViewById(R.id.imgNewLeaflet);

		btnTakePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Context, CameraCaptureActivity.class);
				// File photo = new
				// File(Environment.getExternalStorageDirectory(), "Pic.jpg");
				startActivityForResult(intent,
						CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

			}
		});
	}

	protected File getOutputMediaFileUri(int mediaTypeImage) {

		// TODO Auto-generated method stub

		String baseDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();

		// Not sure if the / is on the path or not
		// ///Android/data/com.vdex.menulibrary/files/
		File mediaStorageDir = new File(baseDir
				+ "/Android/data/com.vdex.menulibrary/files/", "leaflets");

		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("leaflets", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (mediaTypeImage == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else {
			return null;
		}

		try {
			mediaFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("leaflets", "failed to create file");
			e.printStackTrace();
		}

		return mediaFile;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
			if (resultCode == Activity.RESULT_OK) {				
				Uri photo = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "Pic.jpg"));												

				ImageView imageView = (ImageView) findViewById(R.id.imgNewLeaflet);
				imageView.setImageURI(photo);

			}
		}

	}

}
