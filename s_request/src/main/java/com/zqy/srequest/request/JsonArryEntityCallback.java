package com.zqy.srequest.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zqy.srequest.RequestManage;
import com.zqy.sutils.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 * <p>
 * json为数组时使用
 *
 * @param <T> json对象实体类
 */

public abstract class JsonArryEntityCallback<T> extends BaseCallback {
    private Class<T> classOfBean;//json对象实体

    public JsonArryEntityCallback(Class<T> classOfBean) {
        this.classOfBean = classOfBean;

    }

    public JsonArryEntityCallback(Class<T> classOfBean, String requestName) {
        super(requestName);
        this.classOfBean = classOfBean;
    }

    @Override
    public void onFinish(String msg) {

    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        super.onSuccess(response);
        try {
            //泛型转换
            Type type = new ParameterizedTypeImpl(classOfBean);
            List<T> list = new Gson().fromJson(response.body(), type);
            onSuccess(list);
        } catch (JsonSyntaxException e) {

            if (RequestManage.getApiCallback() != null) {
                response.setException(new JsonSyntaxException("json数据格式错误:" + e.getMessage()));
                RequestManage.getApiCallback().onError(getBaseUrl(), getEndUrl(), response);
            }

        }

    }


    /**
     * 对返回数据进行操作的回调， UI线程
     */
    public abstract void onSuccess(List<T> tList);


}
