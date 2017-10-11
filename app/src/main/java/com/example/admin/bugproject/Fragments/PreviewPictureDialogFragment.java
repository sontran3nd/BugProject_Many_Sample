package com.example.admin.bugproject.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Utils.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

/**
 * Created by Admin on 9/7/2017.
 */

public class PreviewPictureDialogFragment extends BaseDialogFragment{

    private SurfaceView cameraPreview;
    private CameraSource mCameraSource;
    private ImageView btnLight;
    boolean lightOn = false;

    public static PreviewPictureDialogFragment newInstance(){
        PreviewPictureDialogFragment previewPictureDialogFragment = new PreviewPictureDialogFragment();
        return previewPictureDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_preview, container, false);

        this.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics metrics = new DisplayMetrics();

        WindowManager manager = getActivity().getWindowManager();

        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            rootView.setMinimumWidth(width);
            rootView.setMinimumHeight(height);
        }

        setupViews(rootView);
        return rootView;
    }

    private void setupViews(View view)
    {

        cameraPreview = (SurfaceView) view.findViewById(R.id.surface_view);
        btnLight = (ImageView) view.findViewById(R.id.btnLight);
        btnLight.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        btnLightClicked();
                    }
                }
        );

        createCameraSource(true, false);
    }

    private void btnLightClicked() {
        if (!lightOn) {
            mCameraSource.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            lightOn = true;
            btnLight.setImageResource(R.drawable.right);
        } else {
            mCameraSource.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            lightOn = false;
            btnLight.setImageResource(R.drawable.white_arrow_down);
        }
    }


    @SuppressLint("InlinedApi")
    private void createCameraSource(boolean autoFocus, boolean useFlash) {
        Context context = getContext();
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).build();
        CameraSource.Builder builder = new CameraSource.Builder(getContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1920, 1080)
                .setRequestedFps(30.0f);
        // make sure that auto focus is an available option
        builder = builder.setFocusMode(
                autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE : null);

        mCameraSource = builder
                .setFlashMode(useFlash ? Camera.Parameters.FLASH_MODE_TORCH : null)
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 112);
                    }
                    mCameraSource.start(cameraPreview.getHolder());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mCameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() > 0) {
                    cameraPreview.getHolder().removeCallback(null);
                    Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(100);
                }
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 112:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        mCameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 112);
                    }
                    Toast.makeText(getContext(), "Vui lòng cấp quyền để thực hiện chức năng này!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
