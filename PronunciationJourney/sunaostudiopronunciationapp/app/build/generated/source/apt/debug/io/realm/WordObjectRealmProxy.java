package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WordObjectRealmProxy extends com.betterlife.pronunciationjourney.model.WordObject
    implements RealmObjectProxy, WordObjectRealmProxyInterface {

    static final class WordObjectColumnInfo extends ColumnInfo
        implements Cloneable {

        public long wordIndex;
        public long splellingIndex;
        public long meanIndex;
        public long exampleIndex;
        public long urlAudioIndex;

        WordObjectColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.wordIndex = getValidColumnIndex(path, table, "WordObject", "word");
            indicesMap.put("word", this.wordIndex);
            this.splellingIndex = getValidColumnIndex(path, table, "WordObject", "splelling");
            indicesMap.put("splelling", this.splellingIndex);
            this.meanIndex = getValidColumnIndex(path, table, "WordObject", "mean");
            indicesMap.put("mean", this.meanIndex);
            this.exampleIndex = getValidColumnIndex(path, table, "WordObject", "example");
            indicesMap.put("example", this.exampleIndex);
            this.urlAudioIndex = getValidColumnIndex(path, table, "WordObject", "urlAudio");
            indicesMap.put("urlAudio", this.urlAudioIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final WordObjectColumnInfo otherInfo = (WordObjectColumnInfo) other;
            this.wordIndex = otherInfo.wordIndex;
            this.splellingIndex = otherInfo.splellingIndex;
            this.meanIndex = otherInfo.meanIndex;
            this.exampleIndex = otherInfo.exampleIndex;
            this.urlAudioIndex = otherInfo.urlAudioIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final WordObjectColumnInfo clone() {
            return (WordObjectColumnInfo) super.clone();
        }

    }
    private WordObjectColumnInfo columnInfo;
    private ProxyState<com.betterlife.pronunciationjourney.model.WordObject> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("word");
        fieldNames.add("splelling");
        fieldNames.add("mean");
        fieldNames.add("example");
        fieldNames.add("urlAudio");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    WordObjectRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (WordObjectColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.betterlife.pronunciationjourney.model.WordObject>(com.betterlife.pronunciationjourney.model.WordObject.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$word() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.wordIndex);
    }

    public void realmSet$word(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'word' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$splelling() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.splellingIndex);
    }

    public void realmSet$splelling(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.splellingIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.splellingIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.splellingIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.splellingIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$mean() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.meanIndex);
    }

    public void realmSet$mean(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.meanIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.meanIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.meanIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.meanIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$example() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.exampleIndex);
    }

    public void realmSet$example(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.exampleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.exampleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.exampleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.exampleIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$urlAudio() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.urlAudioIndex);
    }

    public void realmSet$urlAudio(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.urlAudioIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.urlAudioIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.urlAudioIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.urlAudioIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("WordObject")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("WordObject");
            realmObjectSchema.add(new Property("word", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("splelling", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("mean", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("example", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("urlAudio", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("WordObject");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_WordObject")) {
            Table table = sharedRealm.getTable("class_WordObject");
            table.addColumn(RealmFieldType.STRING, "word", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "splelling", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mean", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "example", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "urlAudio", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("word"));
            table.setPrimaryKey("word");
            return table;
        }
        return sharedRealm.getTable("class_WordObject");
    }

    public static WordObjectColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_WordObject")) {
            Table table = sharedRealm.getTable("class_WordObject");
            final long columnCount = table.getColumnCount();
            if (columnCount != 5) {
                if (columnCount < 5) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final WordObjectColumnInfo columnInfo = new WordObjectColumnInfo(sharedRealm.getPath(), table);

            if (!table.hasPrimaryKey()) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'word' in existing Realm file. @PrimaryKey was added.");
            } else {
                if (table.getPrimaryKey() != columnInfo.wordIndex) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field word");
                }
            }

            if (!columnTypes.containsKey("word")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'word' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("word") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'word' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.wordIndex) && table.findFirstNull(columnInfo.wordIndex) != TableOrView.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'word'. Either maintain the same type for primary key field 'word', or remove the object with null value before migration.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("word"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'word' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("splelling")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'splelling' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("splelling") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'splelling' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.splellingIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'splelling' is required. Either set @Required to field 'splelling' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("mean")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'mean' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mean") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'mean' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.meanIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'mean' is required. Either set @Required to field 'mean' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("example")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'example' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("example") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'example' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.exampleIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'example' is required. Either set @Required to field 'example' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("urlAudio")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'urlAudio' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("urlAudio") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'urlAudio' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.urlAudioIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'urlAudio' is required. Either set @Required to field 'urlAudio' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'WordObject' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_WordObject";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.betterlife.pronunciationjourney.model.WordObject createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.betterlife.pronunciationjourney.model.WordObject obj = null;
        if (update) {
            Table table = realm.getTable(com.betterlife.pronunciationjourney.model.WordObject.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (!json.isNull("word")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("word"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.betterlife.pronunciationjourney.model.WordObject.class), false, Collections.<String> emptyList());
                    obj = new io.realm.WordObjectRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("word")) {
                if (json.isNull("word")) {
                    obj = (io.realm.WordObjectRealmProxy) realm.createObjectInternal(com.betterlife.pronunciationjourney.model.WordObject.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.WordObjectRealmProxy) realm.createObjectInternal(com.betterlife.pronunciationjourney.model.WordObject.class, json.getString("word"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'word'.");
            }
        }
        if (json.has("splelling")) {
            if (json.isNull("splelling")) {
                ((WordObjectRealmProxyInterface) obj).realmSet$splelling(null);
            } else {
                ((WordObjectRealmProxyInterface) obj).realmSet$splelling((String) json.getString("splelling"));
            }
        }
        if (json.has("mean")) {
            if (json.isNull("mean")) {
                ((WordObjectRealmProxyInterface) obj).realmSet$mean(null);
            } else {
                ((WordObjectRealmProxyInterface) obj).realmSet$mean((String) json.getString("mean"));
            }
        }
        if (json.has("example")) {
            if (json.isNull("example")) {
                ((WordObjectRealmProxyInterface) obj).realmSet$example(null);
            } else {
                ((WordObjectRealmProxyInterface) obj).realmSet$example((String) json.getString("example"));
            }
        }
        if (json.has("urlAudio")) {
            if (json.isNull("urlAudio")) {
                ((WordObjectRealmProxyInterface) obj).realmSet$urlAudio(null);
            } else {
                ((WordObjectRealmProxyInterface) obj).realmSet$urlAudio((String) json.getString("urlAudio"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.betterlife.pronunciationjourney.model.WordObject createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.betterlife.pronunciationjourney.model.WordObject obj = new com.betterlife.pronunciationjourney.model.WordObject();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("word")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((WordObjectRealmProxyInterface) obj).realmSet$word(null);
                } else {
                    ((WordObjectRealmProxyInterface) obj).realmSet$word((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("splelling")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((WordObjectRealmProxyInterface) obj).realmSet$splelling(null);
                } else {
                    ((WordObjectRealmProxyInterface) obj).realmSet$splelling((String) reader.nextString());
                }
            } else if (name.equals("mean")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((WordObjectRealmProxyInterface) obj).realmSet$mean(null);
                } else {
                    ((WordObjectRealmProxyInterface) obj).realmSet$mean((String) reader.nextString());
                }
            } else if (name.equals("example")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((WordObjectRealmProxyInterface) obj).realmSet$example(null);
                } else {
                    ((WordObjectRealmProxyInterface) obj).realmSet$example((String) reader.nextString());
                }
            } else if (name.equals("urlAudio")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((WordObjectRealmProxyInterface) obj).realmSet$urlAudio(null);
                } else {
                    ((WordObjectRealmProxyInterface) obj).realmSet$urlAudio((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'word'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.betterlife.pronunciationjourney.model.WordObject copyOrUpdate(Realm realm, com.betterlife.pronunciationjourney.model.WordObject object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.betterlife.pronunciationjourney.model.WordObject) cachedRealmObject;
        } else {
            com.betterlife.pronunciationjourney.model.WordObject realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.betterlife.pronunciationjourney.model.WordObject.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstString(pkColumnIndex, ((WordObjectRealmProxyInterface) object).realmGet$word());
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.betterlife.pronunciationjourney.model.WordObject.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.WordObjectRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.betterlife.pronunciationjourney.model.WordObject copy(Realm realm, com.betterlife.pronunciationjourney.model.WordObject newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.betterlife.pronunciationjourney.model.WordObject) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.betterlife.pronunciationjourney.model.WordObject realmObject = realm.createObjectInternal(com.betterlife.pronunciationjourney.model.WordObject.class, ((WordObjectRealmProxyInterface) newObject).realmGet$word(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((WordObjectRealmProxyInterface) realmObject).realmSet$splelling(((WordObjectRealmProxyInterface) newObject).realmGet$splelling());
            ((WordObjectRealmProxyInterface) realmObject).realmSet$mean(((WordObjectRealmProxyInterface) newObject).realmGet$mean());
            ((WordObjectRealmProxyInterface) realmObject).realmSet$example(((WordObjectRealmProxyInterface) newObject).realmGet$example());
            ((WordObjectRealmProxyInterface) realmObject).realmSet$urlAudio(((WordObjectRealmProxyInterface) newObject).realmGet$urlAudio());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.betterlife.pronunciationjourney.model.WordObject object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.betterlife.pronunciationjourney.model.WordObject.class);
        long tableNativePtr = table.getNativeTablePointer();
        WordObjectColumnInfo columnInfo = (WordObjectColumnInfo) realm.schema.getColumnInfo(com.betterlife.pronunciationjourney.model.WordObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((WordObjectRealmProxyInterface) object).realmGet$word();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$splelling = ((WordObjectRealmProxyInterface)object).realmGet$splelling();
        if (realmGet$splelling != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.splellingIndex, rowIndex, realmGet$splelling, false);
        }
        String realmGet$mean = ((WordObjectRealmProxyInterface)object).realmGet$mean();
        if (realmGet$mean != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.meanIndex, rowIndex, realmGet$mean, false);
        }
        String realmGet$example = ((WordObjectRealmProxyInterface)object).realmGet$example();
        if (realmGet$example != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.exampleIndex, rowIndex, realmGet$example, false);
        }
        String realmGet$urlAudio = ((WordObjectRealmProxyInterface)object).realmGet$urlAudio();
        if (realmGet$urlAudio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlAudioIndex, rowIndex, realmGet$urlAudio, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.betterlife.pronunciationjourney.model.WordObject.class);
        long tableNativePtr = table.getNativeTablePointer();
        WordObjectColumnInfo columnInfo = (WordObjectColumnInfo) realm.schema.getColumnInfo(com.betterlife.pronunciationjourney.model.WordObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.betterlife.pronunciationjourney.model.WordObject object = null;
        while (objects.hasNext()) {
            object = (com.betterlife.pronunciationjourney.model.WordObject) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((WordObjectRealmProxyInterface) object).realmGet$word();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$splelling = ((WordObjectRealmProxyInterface)object).realmGet$splelling();
                if (realmGet$splelling != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.splellingIndex, rowIndex, realmGet$splelling, false);
                }
                String realmGet$mean = ((WordObjectRealmProxyInterface)object).realmGet$mean();
                if (realmGet$mean != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.meanIndex, rowIndex, realmGet$mean, false);
                }
                String realmGet$example = ((WordObjectRealmProxyInterface)object).realmGet$example();
                if (realmGet$example != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.exampleIndex, rowIndex, realmGet$example, false);
                }
                String realmGet$urlAudio = ((WordObjectRealmProxyInterface)object).realmGet$urlAudio();
                if (realmGet$urlAudio != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.urlAudioIndex, rowIndex, realmGet$urlAudio, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.betterlife.pronunciationjourney.model.WordObject object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.betterlife.pronunciationjourney.model.WordObject.class);
        long tableNativePtr = table.getNativeTablePointer();
        WordObjectColumnInfo columnInfo = (WordObjectColumnInfo) realm.schema.getColumnInfo(com.betterlife.pronunciationjourney.model.WordObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((WordObjectRealmProxyInterface) object).realmGet$word();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        String realmGet$splelling = ((WordObjectRealmProxyInterface)object).realmGet$splelling();
        if (realmGet$splelling != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.splellingIndex, rowIndex, realmGet$splelling, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.splellingIndex, rowIndex, false);
        }
        String realmGet$mean = ((WordObjectRealmProxyInterface)object).realmGet$mean();
        if (realmGet$mean != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.meanIndex, rowIndex, realmGet$mean, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.meanIndex, rowIndex, false);
        }
        String realmGet$example = ((WordObjectRealmProxyInterface)object).realmGet$example();
        if (realmGet$example != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.exampleIndex, rowIndex, realmGet$example, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.exampleIndex, rowIndex, false);
        }
        String realmGet$urlAudio = ((WordObjectRealmProxyInterface)object).realmGet$urlAudio();
        if (realmGet$urlAudio != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlAudioIndex, rowIndex, realmGet$urlAudio, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.urlAudioIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.betterlife.pronunciationjourney.model.WordObject.class);
        long tableNativePtr = table.getNativeTablePointer();
        WordObjectColumnInfo columnInfo = (WordObjectColumnInfo) realm.schema.getColumnInfo(com.betterlife.pronunciationjourney.model.WordObject.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.betterlife.pronunciationjourney.model.WordObject object = null;
        while (objects.hasNext()) {
            object = (com.betterlife.pronunciationjourney.model.WordObject) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((WordObjectRealmProxyInterface) object).realmGet$word();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                String realmGet$splelling = ((WordObjectRealmProxyInterface)object).realmGet$splelling();
                if (realmGet$splelling != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.splellingIndex, rowIndex, realmGet$splelling, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.splellingIndex, rowIndex, false);
                }
                String realmGet$mean = ((WordObjectRealmProxyInterface)object).realmGet$mean();
                if (realmGet$mean != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.meanIndex, rowIndex, realmGet$mean, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.meanIndex, rowIndex, false);
                }
                String realmGet$example = ((WordObjectRealmProxyInterface)object).realmGet$example();
                if (realmGet$example != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.exampleIndex, rowIndex, realmGet$example, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.exampleIndex, rowIndex, false);
                }
                String realmGet$urlAudio = ((WordObjectRealmProxyInterface)object).realmGet$urlAudio();
                if (realmGet$urlAudio != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.urlAudioIndex, rowIndex, realmGet$urlAudio, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.urlAudioIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.betterlife.pronunciationjourney.model.WordObject createDetachedCopy(com.betterlife.pronunciationjourney.model.WordObject realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.betterlife.pronunciationjourney.model.WordObject unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.betterlife.pronunciationjourney.model.WordObject)cachedObject.object;
            } else {
                unmanagedObject = (com.betterlife.pronunciationjourney.model.WordObject)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.betterlife.pronunciationjourney.model.WordObject();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((WordObjectRealmProxyInterface) unmanagedObject).realmSet$word(((WordObjectRealmProxyInterface) realmObject).realmGet$word());
        ((WordObjectRealmProxyInterface) unmanagedObject).realmSet$splelling(((WordObjectRealmProxyInterface) realmObject).realmGet$splelling());
        ((WordObjectRealmProxyInterface) unmanagedObject).realmSet$mean(((WordObjectRealmProxyInterface) realmObject).realmGet$mean());
        ((WordObjectRealmProxyInterface) unmanagedObject).realmSet$example(((WordObjectRealmProxyInterface) realmObject).realmGet$example());
        ((WordObjectRealmProxyInterface) unmanagedObject).realmSet$urlAudio(((WordObjectRealmProxyInterface) realmObject).realmGet$urlAudio());
        return unmanagedObject;
    }

    static com.betterlife.pronunciationjourney.model.WordObject update(Realm realm, com.betterlife.pronunciationjourney.model.WordObject realmObject, com.betterlife.pronunciationjourney.model.WordObject newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((WordObjectRealmProxyInterface) realmObject).realmSet$splelling(((WordObjectRealmProxyInterface) newObject).realmGet$splelling());
        ((WordObjectRealmProxyInterface) realmObject).realmSet$mean(((WordObjectRealmProxyInterface) newObject).realmGet$mean());
        ((WordObjectRealmProxyInterface) realmObject).realmSet$example(((WordObjectRealmProxyInterface) newObject).realmGet$example());
        ((WordObjectRealmProxyInterface) realmObject).realmSet$urlAudio(((WordObjectRealmProxyInterface) newObject).realmGet$urlAudio());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("WordObject = [");
        stringBuilder.append("{word:");
        stringBuilder.append(realmGet$word());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{splelling:");
        stringBuilder.append(realmGet$splelling() != null ? realmGet$splelling() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mean:");
        stringBuilder.append(realmGet$mean() != null ? realmGet$mean() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{example:");
        stringBuilder.append(realmGet$example() != null ? realmGet$example() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{urlAudio:");
        stringBuilder.append(realmGet$urlAudio() != null ? realmGet$urlAudio() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordObjectRealmProxy aWordObject = (WordObjectRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aWordObject.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aWordObject.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aWordObject.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
