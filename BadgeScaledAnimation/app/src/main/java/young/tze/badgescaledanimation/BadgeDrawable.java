package young.tze.badgescaledanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/7/13.
 */

public class BadgeDrawable extends Drawable {
    private Context mContext;
    private Bitmap mBitmap;
    private Paint mPaintCircle;
    private Paint mPaintText;
    private int mAppIconSize;
    private String mContent = "2";
    private Rect mRectText;
    private float mY;
    private float mScale;

    public BadgeDrawable(Context context, Bitmap bitmap) {
        this.mContext = context;
        this.mBitmap = bitmap;
        this.mPaintCircle = new Paint();
        this.mRectText = new Rect();
        mPaintCircle.setColor(Color.RED);
        mPaintText = new Paint();
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(48);
        Paint.FontMetrics fm = mPaintText.getFontMetrics();
        //先将文字上移fm.descent，然后再下移(fm.bottom - fm.top) / 2
        mY = 0 - fm.descent + (fm.bottom - fm.top) / 2;
        this.mAppIconSize = mContext.getResources().getDimensionPixelSize(R.dimen.app_icon_size);
        setBounds(0, 0, mAppIconSize, mAppIconSize);
    }

    public void update(float scale) {
        this.mScale = scale;
        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mPaintText.getTextBounds(mContent, 0, mContent.length(), mRectText);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, mAppIconSize, mAppIconSize, false);
        mPaintCircle.setAntiAlias(true);
        mPaintText.setAntiAlias(true);
        canvas.drawBitmap(mBitmap, 0, 0, mPaintCircle);
        canvas.save();
        canvas.translate(mAppIconSize, 0);
        canvas.scale(mScale, mScale, -3 * mRectText.width() / 4, 3 * mRectText.height() / 4);
        canvas.drawRect(new Rect(-3 * mRectText.width() / 4, -3 * mRectText.height() / 4,
                3 * mRectText.width() / 4, 3 * mRectText.height() / 4), mPaintCircle);
        canvas.drawCircle(-3 * mRectText.width() / 4, 0, 3 * mRectText.height() / 4, mPaintCircle);

        canvas.drawCircle(3 * mRectText.width() / 4, 0, 3 * mRectText.height() / 4, mPaintCircle);
        canvas.drawText(mContent, 0, mY, mPaintText);
        mPaintCircle.setAntiAlias(false);
        mPaintText.setAntiAlias(false);
        canvas.restore();


    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
