package com.example.android.study.commonres.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.widget.Toast
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述：图片上传Model提取类
 *
 * @author JiaoPeng by 2020/7/8
 */
//用户头像
const val GL_USER_AVATAR = "gl_user_avatar"

//广告图片
const val GL_AD = "gl_ad"

//商品图片
const val GL_PRODUCT = "gl_product"

//活动图片
const val GL_ACTIVITY = "gl_activity"

//商品评价图片
const val GL_PRODUCT_EVALUATION = "gl_product_evaluation"

//失物招领工单图片
const val GL_LOST_ORDER = "gl_lost_order"

//行李卡绑定失物图片
const val GL_LOST_XLK_ORDER = "gl_lost_xlk_order"

object ImageUpLoadModelUtils {

    /**
     * 检查上传的图片数据
     */
    fun checkPhotos(photos: ArrayList<String>): ArrayList<MultipartBody.Part> {
        val emptyList = arrayListOf("")
        photos.removeAll(emptyList)
        if (photos.size == 0) {
            return arrayListOf()
        }
        val partList = arrayListOf<MultipartBody.Part>()
        for (item in photos) {
            val file = File(item)
            val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val partFile = MultipartBody.Part.createFormData("file", file.name, requestBody)
            partList.add(partFile)
        }
        return partList
    }


    /**
     * 保存图片到指定路径
     * @param context
     * @param *bitmap   要保存的图片
     * @param fileName 自定义图片名称
     */
    fun saveImageToGallery(context: Context, bitmapStr: String) {


        var fileName = ""
        val format: DateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        fileName = fileName + format.format(Date()) + ".JPEG"
        // 保存图片至指定路径
        val storePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
        val appDir = File(storePath)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val file = File(appDir, fileName)
        try {

            var bitmap: Bitmap? = null
            val bytes = Base64.decode(bitmapStr, Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            val fos = FileOutputStream(file)
            //通过io流的方式来压缩保存图片(80代表压缩20%)
            val isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
            //发送广播通知系统图库刷新数据
            println("发送广播通知系统图库刷新数据")
            val uri = Uri.fromFile(file)
            context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            if (isSuccess) {
                Toast.makeText(context, "图片已保存至$file", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "图片保存失败", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}