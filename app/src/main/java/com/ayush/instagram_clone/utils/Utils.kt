package com.ayush.instagram_clone.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import javax.security.auth.callback.Callback

fun uploadImage(uri : Uri,folderName:String,callback:(String?) -> Unit){

    var imageUrl:String?= null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString()).putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUrl = it.toString()
                callback(imageUrl)
            }
        }
}

fun uploadVideo(uri : Uri, folderName:String, progressDialog: ProgressDialog, callback:(String?) -> Unit){

    var videoUrl:String?= null

    progressDialog.setTitle("Uploading . . . ")
    progressDialog.show()

    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString()).putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                videoUrl = it.toString()
                progressDialog.dismiss()
                callback(videoUrl)
            }
        }.addOnProgressListener {
            val uploadvalue:Long = (it.bytesTransferred / it.totalByteCount)*100
            progressDialog.setMessage("uploaded $uploadvalue $")
        }

}