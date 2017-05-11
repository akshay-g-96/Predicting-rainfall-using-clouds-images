package com.example.sairameshg.prci;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;

//import android.util.log;
//  import org.opencv.core.DMatch;
// import org.opencv.features2d.DMatch;
// import org.opencv.highgui.Highgui;


public class capture extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView buckysImageView;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        Button buckyButton = (Button) findViewById(R.id.buckysButton);
        buckysImageView = (ImageView) findViewById(R.id.buckysImageView);
        textView1=(TextView ) findViewById(R.id.textView1) ;
        textView2=(TextView ) findViewById(R.id.textView2) ;

        //Disable the button if the user has no camera
        if (!hasCamera())
            buckyButton.setEnabled(false);
    }

    //Check if the user has a camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            buckysImageView.setImageBitmap(photo);
            // Prci();

        }
    }




    public void Prci(View v){
       // Log.d("test");

        // Set image path
        String filename  = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/g.jpg";
        String filename1 = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/a.jpg";
        String filename2 = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/b.jpg";
        String filename3 = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/c.jpg";
        String filename4 = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/d.jpg";
        String filename5 = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/e.jpg";
        String filename6 = "/home/akshay/AndroidStudioProjects/TakePhoto/app/src/main/res/drawable/f.jpg";
        int count=0,count1=0;
        int ret[]=new int[6];
        ret[5] = compareFeature(filename, filename1);

        ret[0] = compareFeature(filename, filename1);
        ret[1] = compareFeature(filename, filename2);
        ret[2] = compareFeature(filename, filename3);
        ret[3] = compareFeature(filename, filename4);
        ret[4] = compareFeature(filename, filename5);
        ret[5] = compareFeature(filename, filename6);
        for(int i=0;i<=5;i++)
        {
            if(i<=2)
            {
                if(ret[i]>0)
                    count++;
            }
            if(i>2)
            {
                if(ret[i]>0)
                    count1++;
            }

        }


        if(count+count1>0)
        {
            //TextView textView1=(TextView ) findViewById(R.id.textView1) ;
            textView1.setText("it's going to rain");
            // System.out.println();
        }
        else
        {
            //TextView textView1=(TextView ) findViewById(R.id.textView1) ;
            textView1.setText("it's not going to rain");
            // System.out.println();
        }

        if(count>count1)
        {
            // TextView textView2=(TextView ) findViewById(R.id.textView2) ;
            textView2.setText("it's cumulonimbus");
            //System.out.println("it's cumulonimbus");
        }
        else if(count<count1)
        {  // TextView textView2=(TextView ) findViewById(R.id.textView2) ;
            textView2.setText("it's nimbostratus");
            // System.out.println("it's nimbostartus");
        }
        else
        {
            //TextView textView2=(TextView ) findViewById(R.id.textView2) ;
            textView2.setText("its either one of them");
            // System.out.println("as if understood you what nimbostratus is R??");
        }
        //  if (ret > 0) {
        //   System.out.println("Two images are same.");
        //  } else {
        //   System.out.println("Two images are different.");
        //  }
    }


    /**
     * Compare that two images is similar using feature mapping
     * @author minikim
     * @param filename1 - the first image
     * @param filename2 - the second image
     * @return integer - count that has the similarity within images
     */
    public static int compareFeature(String filename1, String filename2) {
        int retVal = 0;
        long startTime = System.currentTimeMillis();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load images to compare
        Mat img1 = Imgcodecs.imread(filename1, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Mat img2 = Imgcodecs.imread(filename2, Imgcodecs.CV_LOAD_IMAGE_COLOR);

        // Declare key point of images
        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();

        // Definition of ORB key point detector and descriptor extractors
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

        // Detect key points
        detector.detect(img1, keypoints1);
        detector.detect(img2, keypoints2);

        // Extract descriptors
        extractor.compute(img1, keypoints1, descriptors1);
        extractor.compute(img2, keypoints2, descriptors2);

        // Definition of descriptor matcher
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        // Match points of two images
        MatOfDMatch matches = new MatOfDMatch();
        //  System.out.println("Type of Image1= " + descriptors1.type() + ", Type of Image2= " + descriptors2.type());
        //  System.out.println("Cols of Image1= " + descriptors1.cols() + ", Cols of Image2= " + descriptors2.cols());

        // Avoid to assertion failed
        // Assertion failed (type == src2.type() && src1.cols == src2.cols && (type == CV_32F || type == CV_8U)
        if (descriptors2.cols() == descriptors1.cols()) {
            matcher.match(descriptors1, descriptors2, matches);

            // Check matches of key points
            DMatch[] match = matches.toArray();
            double max_dist = 0;
            double min_dist = 100;

            for (int i = 0; i < descriptors1.rows(); i++) {
                double dist = match[i].distance;
                if (dist < min_dist) min_dist = dist;
                if (dist > max_dist) max_dist = dist;
            }
            //System.out.println("max_dist=" + max_dist + ", min_dist=" + min_dist);

            // Extract good images (distances are under 10)
            for (int i = 0; i < descriptors1.rows(); i++) {
                if (match[i].distance <= 37) {
                    retVal++;
                }
            }
            //  System.out.println("matching count=" + retVal);
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        //System.out.println("estimatedTime=" + estimatedTime + "ms");

        return retVal;


    }


}
