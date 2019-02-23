package com.nl.manage.finasius;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.CustomVisionPredictionManager;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.PredictionEndpoint;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ImagePrediction;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.Prediction;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class DetectImageActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    int harga = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_image);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button scanButton = (Button)findViewById(R.id.scanButton);
        Button saveButton = (Button)findViewById(R.id.SaveButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = (ImageView)findViewById(R.id.scannedImageView);
            imageView.setImageBitmap(imageBitmap);
            TextView produkText = (TextView)findViewById(R.id.produk);
            TextView hargaText = (TextView)findViewById(R.id.hargaText);
            String text = new DetectImage().doInBackground(imageBitmap);
            produkText.setText(text);
            harga = getHargaFromTextDummy(text);
            hargaText.setText("Rp "+String.valueOf(harga));
        }
    }

    private int getHargaFromTextDummy(String produk){
        if(produk.equals("YouC 1000 Orange Water")){
            return 7600;
        }else if(produk.equals("Good Day Caramel Macchiato")){
            return 4500;
        }else if(produk.equals("Air Mineral Aqua")){
            return 3000;
        }else{
            return 0;
        }
    }

    class DetectImage extends AsyncTask<Bitmap, Void, String> {

        @Override
        protected String doInBackground(Bitmap... bitmaps) {
            String result = "";
            final String predictionApiKey = "3dc9393ac7d644f781966cc2c1b5681b";
            Bitmap bmp = bitmaps[0];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            //bmp.recycle();
            PredictionEndpoint predictClient = CustomVisionPredictionManager.authenticate("https://southeastasia.api.cognitive.microsoft.com/customvision/v2.0/Prediction/",predictionApiKey);
            UUID projectID = UUID.fromString("c179606e-7aba-4eab-b25c-ebe979e5fa13");
            ImagePrediction prediction = predictClient.predictions().predictImage().withProjectId(projectID)
                    .withImageData(byteArray).execute();

            for(Prediction pred : prediction.predictions()){
                if(pred.probability()>0.4){
                    result = pred.tagName();
                }
                System.out.print(result);
            }
            return  result;
        }
    }
}
