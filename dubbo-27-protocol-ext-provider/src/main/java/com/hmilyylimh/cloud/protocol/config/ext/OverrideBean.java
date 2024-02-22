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
package com.hmilyylimh.cloud.protocol.config.ext;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.StringUtils;

import java.util.Map;

public class OverrideBean extends Entity {

    private static final long serialVersionUID = 114828505391757846L;

    private String service;

    private String params;

    private String application;

    private String address;

    private String username;

    private boolean enabled;

    public OverrideBean() {
    }

    public OverrideBean(long id) {
        super(id);
    }

    public String getService() {
        return service;
    }


    public void setService(String service) {
        this.service = service;
    }


    public String getParams() {
        return params;
    }


    public void setParams(String params) {
        this.params = params;
    }


    public String getApplication() {
        return application;
    }


    public void setApplication(String application) {
        this.application = application;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return "Override [service=" + service + ", params=" + params + ", application="
                + application + ", address=" + address + ", username=" + username + ", enabled=" + enabled + "]";
    }

    public URL toUrl() {
        String group = Tool.getGroup(service);
        String version = Tool.getVersion(service);
        String interfaze = Tool.getInterface(service);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.OVERRIDE_PROTOCOL);
        sb.append("://");
        if (!StringUtils.isBlank(address) && !Constants.ANY_VALUE.equals(address)) {
            sb.append(address);
        } else {
            sb.append(Constants.ANYHOST_VALUE);
        }
        sb.append("/");
        sb.append(interfaze);
        sb.append("?");
        Map<String, String> param = StringUtils.parseQueryString(params);
        param.put(Constants.CATEGORY_KEY, Constants.CONFIGURATORS_CATEGORY);
        param.put(Constants.ENABLED_KEY, String.valueOf(isEnabled()));
        param.put(Constants.DYNAMIC_KEY, "false");
        if (!StringUtils.isBlank(application) && !Constants.ANY_VALUE.equals(application)) {
            param.put(Constants.APPLICATION_KEY, application);
        }
        if (group != null) {
            param.put(Constants.GROUP_KEY, group);
        }
        if (version != null) {
            param.put(Constants.VERSION_KEY, version);
        }
        sb.append(StringUtils.toQueryString(param));
        return URL.valueOf(sb.toString());
    }

}
