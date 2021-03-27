package com.example.android.study.commonres.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * @author mo
 */
object FileUtil {
    const val CUSTOM_FILE_NAME_GRAY = "custom_map_config.json"
    fun getSaveFile(context: Context): File {
        return File(context.filesDir, "pic.jpg")
    }

    fun makeRootDirectory(filePath: String): String? {
        var file: File? = null
        try {
            var path = Environment.getExternalStorageDirectory().toString() + File.separator
            val dirList = filePath.split("/".toRegex()).toTypedArray()
            for (dir in dirList) {
                if (dir == null || dir.length <= 0) {
                    continue
                }
                path = path + dir + File.separator
                file = File(path)
                if (!file.exists()) {
                    file.mkdir()
                }
            }
            return path
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取图片的URI，根据不同版本生成相应的URI
     *
     * @param file
     * @return
     */
    fun getUriForFile(file: File?): Uri? {
        if (file == null) {
            throw NullPointerException()
        }
        var uri: Uri? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // TODO 获取application待完善
            // uri = FileProvider.getUriForFile(TrainTravelApplication.getContext().getApplicationContext(),
            // BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            uri = Uri.fromFile(file)
        }
        return uri
    }

    /**
     * 根据文件路径判断是否是视频文件
     *
     * @param filePath
     * @return
     */
    fun isVideo(filePath: String?): Boolean {
        return checkSuffix(filePath, arrayOf("wmv", "rmvb", "avi", "mp4"))
    }

    fun checkSuffix(fileName: String?,
                    fileSuffix: Array<String>): Boolean {
        for (suffix in fileSuffix) {
            if (fileName != null) {
                if (fileName.toLowerCase().endsWith(suffix)) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 读取json路径
     */
    fun getCustomStyleFilePath(context: Context, customStyleFileName: String): String {
        var outputStream: FileOutputStream? = null
        var inputStream: InputStream? = null
        var parentPath: String? = null
        try {
            inputStream = context.assets.open("" + customStyleFileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            parentPath = context.filesDir.absolutePath
            val customStyleFile = File("$parentPath/$customStyleFileName")
            if (customStyleFile.exists()) {
                customStyleFile.delete()
            }
            customStyleFile.createNewFile()
            outputStream = FileOutputStream(customStyleFile)
            outputStream.write(buffer)
        } catch (e: IOException) {
            Log.e("CustomMapDemo", "Copy custom style file failed", e)
        } finally {
            try {
                inputStream?.close()
                outputStream?.close()
            } catch (e: IOException) {
            }
        }
        return "$parentPath/$customStyleFileName"
    }

    fun byte2FitMemorySize(byteNum: Long): String {
        return if (byteNum < 0) {
            "shouldn't be less than zero!"
        } else if (byteNum < 1024) {
            String.format(Locale.getDefault(), "%.1fB", byteNum.toDouble())
        } else if (byteNum < 1048576) {
            String.format(Locale.getDefault(), "%.1fKB", byteNum.toDouble() / 1024)
        } else if (byteNum < 1073741824) {
            String.format(Locale.getDefault(), "%.1fMB", byteNum.toDouble() / 1048576)
        } else {
            String.format(Locale.getDefault(), "%.1fGB", byteNum.toDouble() / 1073741824)
        }
    }
}