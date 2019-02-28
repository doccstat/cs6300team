package edu.gatech.seclass.crypto6300.data.entities

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
) {
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
}