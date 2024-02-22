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
package com.hmilyylimh.cloud.rpcctx.config;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.log4j.MDC;

import java.util.Map;
import java.util.UUID;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;


@Activate(group = PROVIDER, order = -9000)
public class ReqNoProviderFilter implements Filter {

    public static final String TRACE_ID = "TRACE-ID";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 获取入参的跟踪序列号值
        Map<String, Object> attachments = invocation.getObjectAttachments();
        String reqTraceId = attachments != null ? (String) attachments.get(TRACE_ID) : null;

        // 若 reqTraceId 为空则重新生成一个序列号值
        reqTraceId = reqTraceId == null ? generateTraceId() : reqTraceId;

        // 将序列号值设置到上下文对象中
        RpcContext.getServerAttachment().setObjectAttachment(TRACE_ID, reqTraceId);
        // 并且将序列号设置到日志打印器中，方便在日志中体现出来
        MDC.put(TRACE_ID, reqTraceId);

        // 继续后面过滤器的调用
        return invoker.invoke(invocation);
    }

    private String generateTraceId() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
