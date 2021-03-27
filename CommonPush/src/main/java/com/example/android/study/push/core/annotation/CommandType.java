
package com.example.android.study.push.core.annotation;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 命令类型
 *
 * @author xuexiang
 * @since 2019-08-17 11:27
 */
@IntDef({CommandType.TYPE_REGISTER, CommandType.TYPE_UNREGISTER, CommandType.TYPE_ADD_TAG, CommandType.TYPE_DEL_TAG, CommandType.TYPE_GET_TAG, CommandType.TYPE_BIND_ALIAS, CommandType.TYPE_UNBIND_ALIAS, CommandType.TYPE_GET_ALIAS, CommandType.TYPE_AND_OR_DEL_TAG})
@Retention(RetentionPolicy.SOURCE)
public @interface CommandType {

    /**
     * 注册推送
     */
    int TYPE_REGISTER = 2000;
    /**
     * 取消注册推送
     */
    int TYPE_UNREGISTER = 2001;

    /**
     * 添加标签
     */
    int TYPE_ADD_TAG = 2002;
    /**
     * 删除标签
     */
    int TYPE_DEL_TAG = 2003;
    /**
     * 获取标签
     */
    int TYPE_GET_TAG = 2004;

    /**
     * 绑定别名
     */
    int TYPE_BIND_ALIAS = 2005;
    /**
     * 解绑别名
     */
    int TYPE_UNBIND_ALIAS = 2006;
    /**
     * 获取别名
     */
    int TYPE_GET_ALIAS = 2007;

    /**
     * 添加或删除标签
     */
    int TYPE_AND_OR_DEL_TAG = 2008;

}
