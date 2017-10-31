package com.itcast.yb.packelves.fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * fragment工厂类
 * Created by yb on 2017/10/29.
 */

public class FragmentFactory {
    private static Map<Integer,BaseFragment> map=new HashMap<>();
    private static Map<Integer,BaseFragment> featureMap = new HashMap<>();
    private static Map<Integer,BaseFragment> opengameMap = new HashMap<>();

    public static BaseFragment createFragment(int pos) {
        BaseFragment fragment = map.get(pos);
        if(fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new GiftFragment();
                    break;
                case 1:
                    fragment = new OpenGameFragment();
                    break;
                case 2:
                    fragment = new HotFragment();
                    break;
                case 3:
                    fragment = new FeatureFragment();
                    break;
                default:
                    break;
            }
            map.put(pos,fragment);
        }
        return fragment;
    }

    public static BaseFragment createFeatureFg(int pos) {
        BaseFragment fragment = featureMap.get(pos);
        if(fragment == null) {
            switch (pos) {
                case 0 :
                    fragment = new BeatenFragment();
                    break;
                case 1:
                    fragment = new NewGameFragment();
                    break;
                default:
                    break;
            }
            featureMap.put(pos,fragment);
        }
        return fragment;
    }


    /**
     * 开服开测
     * @param pos
     * @return
     */
    public static BaseFragment createOpengameFg(int pos) {
        BaseFragment fragment = opengameMap.get(pos);
        if(fragment == null) {
            switch (pos) {
                case 0 :
                    fragment = new KaiFuFragment();
                    break;
                case 1:
                    fragment = new KaiCeFragment();
                    break;
                default:
                    break;
            }
            featureMap.put(pos,fragment);
        }
        return fragment;
    }
}
