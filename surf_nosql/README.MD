NOSQL 的总结
redis/memcache/mongdb

redis
 Redis is an in-memory database that persists on disk. 
 The data model is key-value, but many different kind of values are supported:
  Strings, Lists, Sets, Sorted Sets, Hashes, HyperLogLogs, Bitmaps.

  memcached
  当一个key:value要被放入到Memcached中，首先Memcached会根据key的hash算法获取到hash值来选择被分配的slab，
  然后根据value选择适合的dump区。所谓dump区其实就是根据value的大小来将内存按照存储单元内容大小分页。
  这个是可以配置Memcached的，例如Memcached将slab中的内存划分成4个dump，第一dump区存储0-50k大小的数据，第二dump区存储50-100k的数据，第三dump区存储100-500k的数据,第四dump区存储500-1000K的数据。
  那么当key:value需要被写入的时候，很容易定位到value所处的dump，分配内存给value。
  这种分dump模式简化内存管理，加速了内存回收和分配。但是这里需要注意的几点就是，首先当你的应用场景中保存的数据大小离散度很高，那么就不是很适合Memcached的这种分配模式，容易造成浪费，例如第一dump区已经满了，第二第三dump区都还是只有一个数据，那么第二第三dump区不会被回收，第二第三dump区的空间就浪费了。
  同时Memcached对于value的大小支持到1M,大于1M的内容不适合Memcached存储。其实在Cache的设计中这样的情况发生本来就证明设计有问题，Cache只是加速，一般保存都是较小的id或者小对象，用来验证以及为数据定位作精准细化，而大数据量的内容还是在数据库等存储中。
  