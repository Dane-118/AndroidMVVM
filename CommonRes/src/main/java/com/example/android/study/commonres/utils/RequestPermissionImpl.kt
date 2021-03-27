package com.example.android.study.commonres.utils

import android.content.Context
import com.example.android.study.commonsdk.permission.PermissionUtil


/**
 *
 *
 *
 *
 *
 *
 *
 */
open class RequestPermissionImpl(val context: Context): PermissionUtil.RequestPermission {

        override fun onRequestPermissionSuccess() {
        }

        override fun onRequestPermissionFailure(permissions: List<String>) {
            onRequestPermissionFailurePopView(permissions)
        }

        override fun onRequestPermissionFailureWithAskNeverAgain(permissions: List<String>) {
            onRequestPermissionFailureWithAskNeverAgainPopView(context, permissions)

        }

    }