package com.example.android.study.commonres.utils

import com.blankj.utilcode.util.StringUtils.getString
import com.example.android.study.commonres.R
import java.util.*

/**
 *
 * Permissions.
 * Created by Wang Peng on 2017/8/4.
 */
object Permission {
    const val READ_CALENDAR = "android.permission.READ_CALENDAR"
    const val WRITE_CALENDAR = "android.permission.WRITE_CALENDAR"
    const val CAMERA = "android.permission.CAMERA"
    const val READ_CONTACTS = "android.permission.READ_CONTACTS"
    const val WRITE_CONTACTS = "android.permission.WRITE_CONTACTS"
    const val GET_ACCOUNTS = "android.permission.GET_ACCOUNTS"
    const val ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION"
    const val ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION"
    const val RECORD_AUDIO = "android.permission.RECORD_AUDIO"
    const val READ_PHONE_STATE = "android.permission.READ_PHONE_STATE"
    const val CALL_PHONE = "android.permission.CALL_PHONE"
    const val READ_CALL_LOG = "android.permission.READ_CALL_LOG"
    const val WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG"
    const val ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL"
    const val USE_SIP = "android.permission.USE_SIP"
    const val PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS"
    const val BODY_SENSORS = "android.permission.BODY_SENSORS"
    const val SEND_SMS = "android.permission.SEND_SMS"
    const val RECEIVE_SMS = "android.permission.RECEIVE_SMS"
    const val READ_SMS = "android.permission.READ_SMS"
    const val RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH"
    const val RECEIVE_MMS = "android.permission.RECEIVE_MMS"
    const val READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
    const val WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE"
    const val MOUNT_UNMOUNT_FILESYSTEMS = "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"

    /**
     * Turn permissions into text.
     */
    fun transformText(vararg permissions: String?): List<String> {
        return transformText(Arrays.asList(*permissions))
    }

    /**
     * Turn permissions into text.
     */
    fun transformText(vararg groups: Array<String?>): List<String> {
        val permissionList: MutableList<String?> = ArrayList()
        for (group in groups) {
            permissionList.addAll(Arrays.asList<String>(*group))
        }
        return transformText(permissionList)
    }

    private fun addPermissions(textList: MutableList<String>, message: String) {
        if (!textList?.contains(message)) {
            textList.add(message)
        }
    }

    fun transformText(permissions: List<String?>): List<String> {
        val textList: MutableList<String> = ArrayList(permissions.size)
        for (permission in permissions) {
            val message: String
            when (permission) {
                WRITE_CALENDAR -> {
                    message = getString(R.string.permission_name_calendar)
                    addPermissions(textList, message)
                }
                CAMERA -> {
                    message = getString(R.string.permission_name_camera)
                    addPermissions(textList, message)
                }
                GET_ACCOUNTS -> {
                    message = getString(R.string.permission_name_contacts)
                    addPermissions(textList, message)
                }
                ACCESS_COARSE_LOCATION -> {
                    message = getString(R.string.permission_name_location)
                    addPermissions(textList, message)
                }
                RECORD_AUDIO -> {
                    message = getString(R.string.permission_name_microphone)
                    addPermissions(textList, message)
                }
                PROCESS_OUTGOING_CALLS -> {
                    message = getString(R.string.permission_name_phone)
                    addPermissions(textList, message)
                }
                BODY_SENSORS -> {
                    message = getString(R.string.permission_name_sensors)
                }
                RECEIVE_MMS -> {
                    message = getString(R.string.permission_name_sms)
                    addPermissions(textList, message)
                }
                WRITE_EXTERNAL_STORAGE -> {
                    message = getString(R.string.permission_name_storage)
                    addPermissions(textList, message)
                }

            }
        }
        return textList
    }

    object Group {
        val CALENDAR = arrayOf(
                READ_CALENDAR,
                WRITE_CALENDAR)
        val CAMERA = arrayOf(Permission.CAMERA)
        val CONTACTS = arrayOf(
                READ_CONTACTS,
                WRITE_CONTACTS,
                GET_ACCOUNTS)
        val LOCATION = arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION)
        val MICROPHONE = arrayOf(RECORD_AUDIO)
        val PHONE = arrayOf(
                READ_PHONE_STATE,
                CALL_PHONE,
                READ_CALL_LOG,
                WRITE_CALL_LOG,
                ADD_VOICEMAIL,
                USE_SIP,
                PROCESS_OUTGOING_CALLS)
        val SENSORS = arrayOf(BODY_SENSORS)
        val SMS = arrayOf(
                SEND_SMS,
                RECEIVE_SMS,
                READ_SMS,
                RECEIVE_WAP_PUSH,
                RECEIVE_MMS)
        val STORAGE = arrayOf(
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE)
    }
}