/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hmilyylimh.cloud.cache.config;

import org.apache.dubbo.cache.Cache;
import org.apache.dubbo.cache.CacheFactory;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

import java.io.Serializable;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;
import static org.apache.dubbo.common.constants.FilterConstants.CACHE_KEY;

@Activate(group = {CONSUMER, PROVIDER})
public class CustomCacheFilter implements Filter {

    private CacheFactory cacheFactory;

    public void setCacheFactory(CacheFactory cacheFactory) {
        this.cacheFactory = cacheFactory;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 先看下有没有 cacheFactory 这个扩展点
        // 然后看下方法级别上有没有一个叫做 cache 的标识键
        if (cacheFactory != null &&
                "true".equals(invoker.getUrl().getMethodParameter(invocation.getMethodName(), "cache.enable"))) {
            // 既然有 cache 标识键的话，那么就弄出一个 Cache 对象出来，后续需要用这个 Cache 对象来装结果的
            Cache cache = cacheFactory.getCache(invoker.getUrl(), invocation);
            if (cache != null) {
                // 将方法接口调用的参数值想办法变成一个组合的字符串 key，作为标识缓存结果是否存在的 key
                // nodes样例值情况：
                // 1、id
                // 2、id,name
                // 3、id,name,sex
                // 4、list[0].id,list[1].name,list[2].sex
                String nodes = invoker.getUrl().getMethodParameter
                        (invocation.getMethodName(), "cache.unique.nodes");
                String key = null;
                if(StringUtils.isBlank(nodes)){
                    key = StringUtils.toArgumentString(invocation.getArguments());
                } else {
                    // 该方法的重点逻辑就是根据 nodes 里面的路径，
                    // 从 invocation.getArguments() 取出对应的路径值
                    // 然后将参数值拼接在一起构成key
                    // key = extraNodeValueToArgumentString(nodes);
                }

                // 通过 key 从缓存对象 cache 里面获取缓存结果
                Object value = cache.get(key);
                // 如果获取到的 value 缓存结果不为空，说明是有缓存的，那么就直接返回
                if (value != null) {
                    if (value instanceof ValueWrapper) {
                        return AsyncRpcResult.newDefaultAsyncResult(((ValueWrapper) value).get(), invocation);
                    } else {
                        return AsyncRpcResult.newDefaultAsyncResult(value, invocation);
                    }
                }
                // 如果 value 为空说明并没有缓存起来，于是继续发起下一步远程调用，并拿到结果
                Result result = invoker.invoke(invocation);
                // 如果拿到的 result 结果没有异常的话，那么就把缓存结果放到 cache 中去
                if (!result.hasException()) {
                    cache.put(key, new ValueWrapper(result.getValue()));
                }
                return result;
            }
        }
        // 如果连 cacheFactory 这个扩展点都没有，说明并不需要缓存，原来该怎么调用就还是接着怎么调用
        return invoker.invoke(invocation);
    }

    static class ValueWrapper implements Serializable {

        private static final long serialVersionUID = -1777337318019193256L;

        private final Object value;

        public ValueWrapper (Object value) {
            this.value = value;
        }

        public Object get() {
            return this.value;
        }
    }
}
