package com.nfctransaction.sdk.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`peerDeviceId`,`amount`,`timestamp`,`direction`,`synced`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPeerDeviceId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPeerDeviceId());
        }
        statement.bindDouble(3, entity.getAmount());
        final String _tmp = __converters.dateToTimestamp(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindString(5, __TransactionDirection_enumToString(entity.getDirection()));
        final int _tmp_1 = entity.getSynced() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transactions SET synced = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTransaction(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransaction.insert(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final String transactionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        if (transactionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, transactionId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkAsSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Transaction>> getAllTransactions() {
    final String _sql = "SELECT * FROM transactions ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPeerDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "peerDeviceId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPeerDeviceId;
            if (_cursor.isNull(_cursorIndexOfPeerDeviceId)) {
              _tmpPeerDeviceId = null;
            } else {
              _tmpPeerDeviceId = _cursor.getString(_cursorIndexOfPeerDeviceId);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final LocalDateTime _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = __converters.fromTimestamp(_tmp);
            final TransactionDirection _tmpDirection;
            _tmpDirection = __TransactionDirection_stringToEnum(_cursor.getString(_cursorIndexOfDirection));
            final boolean _tmpSynced;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp_1 != 0;
            _item = new Transaction(_tmpId,_tmpPeerDeviceId,_tmpAmount,_tmpTimestamp,_tmpDirection,_tmpSynced);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUnsyncedTransactions(final Continuation<? super List<Transaction>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE synced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPeerDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "peerDeviceId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPeerDeviceId;
            if (_cursor.isNull(_cursorIndexOfPeerDeviceId)) {
              _tmpPeerDeviceId = null;
            } else {
              _tmpPeerDeviceId = _cursor.getString(_cursorIndexOfPeerDeviceId);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final LocalDateTime _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = __converters.fromTimestamp(_tmp);
            final TransactionDirection _tmpDirection;
            _tmpDirection = __TransactionDirection_stringToEnum(_cursor.getString(_cursorIndexOfDirection));
            final boolean _tmpSynced;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp_1 != 0;
            _item = new Transaction(_tmpId,_tmpPeerDeviceId,_tmpAmount,_tmpTimestamp,_tmpDirection,_tmpSynced);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __TransactionDirection_enumToString(@NonNull final TransactionDirection _value) {
    switch (_value) {
      case SENT: return "SENT";
      case RECEIVED: return "RECEIVED";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private TransactionDirection __TransactionDirection_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "SENT": return TransactionDirection.SENT;
      case "RECEIVED": return TransactionDirection.RECEIVED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
