package com.example.android.study.push.core.annotation;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.android.study.push.core.annotation.ResultCode.RESULT_ERROR;
import static com.example.android.study.push.core.annotation.ResultCode.RESULT_OK;


/**
 * 结果码
 *
 * @author xuexiang
 * @since 2019-08-17 11:34
 */
@IntDef({RESULT_OK, RESULT_ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface ResultCode {
    /**
     * 成功
     */
    int RESULT_OK = 0;
    /**
     * 失败
     */
    int RESULT_ERROR = 1;

}
