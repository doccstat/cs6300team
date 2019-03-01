package edu.gatech.seclass.crypto6300.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChooseCryptogram(
        @ColumnInfo(name = "cryptogram_id")
        val cryptogramId: Long,
        val name: String,
        val difficulty: Int,
        @ColumnInfo(name = "is_completed")
        val isCompleted: Boolean,
        @ColumnInfo(name = "attempts_remaining")
        val attemptsRemaining: Int
) : Parcelable