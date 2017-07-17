package young.tze.badgescaledanimation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap appIcon = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        final BadgeDrawable drawable = new BadgeDrawable(this,appIcon);
        TextView tv = (TextView)findViewById(R.id.app_icon);
        tv.setCompoundDrawables(null,drawable,null,null);
        final BadgeScaledAnimator ba = new BadgeScaledAnimator(drawable);
        Button startBtn = (Button)findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba.start();
            }
        });

        Button stopBtn = (Button)findViewById(R.id.stop);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba.stop();
            }
        });

    }
}
