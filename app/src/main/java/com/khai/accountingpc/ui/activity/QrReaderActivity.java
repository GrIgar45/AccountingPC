package com.khai.accountingpc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.khai.accountingpc.R;
import com.khai.accountingpc.ui.camera.CameraSource;

import java.io.IOException;

/** Camera for QR recognition Activity
 @author Safaryan Vyacheslav
*/
public class QrReaderActivity extends Activity {

    private SurfaceView cameraView;
    private boolean isCaptured = false;

    /** Start initialization
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.barcode_capture);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);

        // Default QR recognizer
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        // Create camera and set properties
        final CameraSource cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                // Set frame pes seconds for camera
                .setRequestedFps(30.0f)
                // Set focus mode
                .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
                // Complete
                .build();


        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) throws SecurityException {
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            /** throw
            */
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            /** Recognition QR
            */
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    if (!isCaptured) {
                        isCaptured = true;
                        Intent intent = new Intent(QrReaderActivity.this, MainActivity.class);
                        intent.putExtra("qrData", barcodes.valueAt(0).displayValue);
                        startActivity(intent);
                        // Close activity
                        finish();
                    }
                }
            }
        });
    }
}
