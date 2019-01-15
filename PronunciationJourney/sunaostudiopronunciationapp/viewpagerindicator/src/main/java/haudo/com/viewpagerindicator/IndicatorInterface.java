package haudo.com.viewpagerindicator;

import android.support.v4.view.ViewPager;

/**
 * Created by HauDo on 3/2/2018.
 */

public interface IndicatorInterface {

    void setViewPager(ViewPager viewPager);

    void setAnimateDuration(long duration);

    /**
     * @param radius: radius in pixel
     */
    void setRadiusSelected(int radius);

    /**
     * @param radius: radius in pixel
     */
    void setRadiusUnselected(int radius);

    /**
     * @param distance: distance in pixel
     */
    void setDistanceDot(int distance);
}
