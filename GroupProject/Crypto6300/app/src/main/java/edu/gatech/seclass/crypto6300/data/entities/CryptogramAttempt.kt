package edu.gatech.seclass.crypto6300.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        tableName = "CryptogramAttempt",
        foreignKeys = [
            ForeignKey(entity = Cryptogram::class, parentColumns = ["id"], childColumns = ["cryptogram_id"], onDelete = ForeignKey.CASCADE),
            ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE)
        ]
)
data class CryptogramAttempt(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "user_id", index = true)
        val userId: Long,
        @ColumnInfo(name = "cryptogram_id", index = true)
        val cryptogramId: Long,

        @ColumnInfo(name = "attempts_remaining")
        val attemptsRemaining: Int,
        @ColumnInfo(name = "submission")
        val currentSubmissionState: String = "",
        @ColumnInfo(name = "encrypted_phrase")
        val encryptedPhrase: String = "",
        @ColumnInfo(name = "is_completed")
        val isCompleted: Boolean = false,
        @ColumnInfo(name = "is_solved")
        val isSolved: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readLong(),
            parcel.readLong(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte())

    constructor(
            userId: Long,
            cryptogramId: Long,
            attemptsRemaining: Int,
            currentSubmissionState: String,
            encryptedPhrase: String,
            isCompleted: Boolean,
            isSolved: Boolean
    ) : this(null, userId, cryptogramId, attemptsRemaining, currentSubmissionState, encryptedPhrase, isCompleted, isSolved)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CryptogramAttempt

        if (userId != other.userId) return false
        if (cryptogramId != other.cryptogramId) return false
        if (attemptsRemaining != other.attemptsRemaining) return false
        if (currentSubmissionState != other.currentSubmissionState) return false
        if (encryptedPhrase != other.encryptedPhrase) return false
        if (isCompleted != other.isCompleted) return false
        if (isSolved != other.isSolved) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + cryptogramId.hashCode()
        result = 31 * result + attemptsRemaining
        result = 31 * result + currentSubmissionState.hashCode()
        result = 31 * result + encryptedPhrase.hashCode()
        result = 31 * result + isCompleted.hashCode()
        result = 31 * result + isSolved.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeLong(userId)
        parcel.writeLong(cryptogramId)
        parcel.writeInt(attemptsRemaining)
        parcel.writeString(currentSubmissionState)
        parcel.writeString(encryptedPhrase)
        parcel.writeByte(if (isCompleted) 1 else 0)
        parcel.writeByte(if (isSolved) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CryptogramAttempt> {
        override fun createFromParcel(parcel: Parcel): CryptogramAttempt {
            return CryptogramAttempt(parcel)
        }

        override fun newArray(size: Int): Array<CryptogramAttempt?> {
            return arrayOfNulls(size)
        }
    }
}