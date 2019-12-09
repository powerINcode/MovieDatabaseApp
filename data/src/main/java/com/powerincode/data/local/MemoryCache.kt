package com.powerincode.data.local

@Suppress("UNCHECKED_CAST")
class MemoryCache constructor(val name: String): Storage {
    private val storage: HashMap<String, Any> = hashMapOf()
    override fun put(key: String, value: Any) {
        storage[key] = value
    }

    override fun <T> get(key: String): T? {
        return storage[key] as? T
    }

    override fun remove(key: String) {
        storage.remove(key)
    }
}