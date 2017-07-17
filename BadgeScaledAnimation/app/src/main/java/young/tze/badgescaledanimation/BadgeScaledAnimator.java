package young.tze.badgescaledanimation;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2017/7/13.
 */

public class BadgeScaledAnimator {
    private BadgeDrawable mBadgeDrawable;
    private ValueAnimator mAnimator;
    public BadgeScaledAnimator(BadgeDrawable badgeDrawable) {
        this.mBadgeDrawable = badgeDrawable;
        initAnimator();
    }
    public void initAnimator() {
        mAnimator = ValueAnimator.ofFloat(0.5f,0.8f,1.0f,0.5f,0.8f,1.0f);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float)animation.getAnimatedValue();
                mBadgeDrawable.update(scale);
            }
        });
        mAnimator.setDuration(5*1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setInterpolator(new LinearInterpolator());
    }
    public void start() {
        mAnimator.start();
    }
    public void stop() {
        mAnimator.cancel();
    }

}
