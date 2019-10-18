package com.zp.chapter01.controller;

import io.swagger.annotations.ApiModelProperty;

/**
 * 请求公共参数 放在header里
 *
 * @version 0.1
 */
public class CommonRequestData {

    /**
     * 请求头参数前缀
     */
    public static final String HEADER_PREFIX = "c-x-";

    /**
     * 神策的前缀
     */
    public static final String SENSOR_PREFIX = "c-sensor-";

    /**
     * 平台识别
     */
    @ApiModelProperty(value = "平台识别", dataType = "string", example = "iOS/android")
    private String platform;

    /**
     * 客户端包名
     */
    @ApiModelProperty(value = "客户端包名", dataType = "string", example = "ios")
    private String packageName;

    /**
     * token
     */
    private String token;

    /**
     * version 版本号 示例:1.2.3
     */
    @ApiModelProperty(value = "应用版本号", dataType = "string", example = "1.0.0")
    private String version;

    /**
     * c_id渠道id
     */
    @ApiModelProperty(value = "渠道ID", dataType = "string", example = "1")
    private String cid;

    /**
     * 设备Id 设备编号 3887678564564656
     */
    @ApiModelProperty(value = "设备ID", dataType = "string", example = "3887678564564656")
    private String did;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户ID", dataType = "string", example = "3887678564564656")
    private String uid;

    /**
     * 设备品牌
     */
    @ApiModelProperty(value = "设备品牌", dataType = "string", example = "iphone")
    private String dbd;

    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号", dataType = "string", example = "iphone-x")
    private String dml;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", dataType = "string", example = "120.0732234700521")
    private String lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "维度", dataType = "string", example = "30.28172770182292")
    private String lat;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址", dataType = "string", example = "127.0.0.1")
    private String ip;

    /**
     * 定位城市
     */
    @ApiModelProperty(value = "定位城市", dataType = "string", example = "HangZhou")
    private String location;

    /**
     * 请求来源应用标示
     */
    @ApiModelProperty(value = "应用来源", dataType = "string", example = "appstore")
    private String source;

    /**
     * 马甲编码：代表具体的马甲信息
     */
    @ApiModelProperty(value = "马甲编号", dataType = "string", example = "majia001")
    private String vest;

    /**
     * 请求时间戳
     */
    @ApiModelProperty(value = "请求时间戳", dataType = "string", example = "1514365447")
    private String timestamp;

    /**
     * 国家
     */
    @ApiModelProperty(value = "国家", dataType = "string", example = "CHN")
    private String country;

    /**
     * 神策相关信息:或者统计相关的信息
     */
    private SensorsCommonParam sensors;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDbd() {
        return dbd;
    }

    public void setDbd(String dbd) {
        this.dbd = dbd;
    }

    public String getDml() {
        return dml;
    }

    public void setDml(String dml) {
        this.dml = dml;
    }

    public String getVest() {
        return vest;
    }

    public void setVest(String vest) {
        this.vest = vest;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SensorsCommonParam getSensors() {
        return sensors;
    }

    public void setSensors(SensorsCommonParam sensors) {
        this.sensors = sensors;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public static class SensorsCommonParam {

        /**
         * 匿名id
         */
        @ApiModelProperty(value = "匿名id", dataType = "string", example = "")
        private String anonymous_id;

        /**
         * 产品
         */
        @ApiModelProperty(value = "产品", dataType = "string", example = "SMART-LINK")
        private String product;

        /**
         * 业务子产品
         */
        @ApiModelProperty(value = "产品子模块", dataType = "string", example = "jietiao")
        private String module;

        /**
         * 操作系统
         */
        @ApiModelProperty(value = "操作系统", dataType = "string", example = "ios")
        private String os;

        /**
         * 操作系统版本
         */
        @ApiModelProperty(value = "操作系统版本", dataType = "string", example = "10.2")
        private String os_version;

        /**
         * 屏幕高度
         */
        @ApiModelProperty(value = "屏幕高度", dataType = "string", example = "768.0000")
        private Integer screen_height;

        /**
         * 屏幕宽度
         */
        @ApiModelProperty(value = "屏幕宽度", dataType = "string", example = "365.0000")
        private Integer screen_width;

        /**
         * 是否wifi
         */
        @ApiModelProperty(value = "是否wifi", dataType = "boolean", example = "true")
        private Boolean wifi;

        /**
         * 浏览器
         */
        @ApiModelProperty(value = "浏览器", dataType = "string", example = "safari")
        private String browser;

        /**
         * 浏览器版本
         */
        @ApiModelProperty(value = "浏览器版本", dataType = "string", example = "11.0.2")
        private String browser_version;

        /**
         * 运营商名称
         */
        @ApiModelProperty(value = "运营商名称", dataType = "string", example = "mobile")
        private String carrier;

        /**
         * 网络类型
         */
        @ApiModelProperty(value = "网络类型", dataType = "string", example = "4g")
        private String network_type;

        /**
         * 广告系列来源
         */
        @ApiModelProperty(value = "广告系列来源", dataType = "string", example = "")
        private String utm_source;

        /**
         * 广告系列媒介
         */
        @ApiModelProperty(value = "广告系列媒介", dataType = "string", example = "")
        private String utm_medium;

        /**
         * 广告系列字词
         */
        @ApiModelProperty(value = "广告系列字词", dataType = "string", example = "")
        private String utm_term;

        /**
         * 广告系列内容
         */
        @ApiModelProperty(value = "广告系列内容", dataType = "string", example = "")
        private String utm_content;

        /**
         * 广告系列名称
         */
        @ApiModelProperty(value = "广告系列名称", dataType = "string", example = "")
        private String utm_campaign;

        public String getAnonymous_id() {
            return anonymous_id;
        }

        public void setAnonymous_id(String anonymous_id) {
            this.anonymous_id = anonymous_id;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getOs_version() {
            return os_version;
        }

        public void setOs_version(String os_version) {
            this.os_version = os_version;
        }

        public Integer getScreen_height() {
            return screen_height;
        }

        public void setScreen_height(Integer screen_height) {
            this.screen_height = screen_height;
        }

        public Integer getScreen_width() {
            return screen_width;
        }

        public void setScreen_width(Integer screen_width) {
            this.screen_width = screen_width;
        }

        public Boolean getWifi() {
            return wifi;
        }

        public void setWifi(Boolean wifi) {
            this.wifi = wifi;
        }

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public String getBrowser_version() {
            return browser_version;
        }

        public void setBrowser_version(String browser_version) {
            this.browser_version = browser_version;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getNetwork_type() {
            return network_type;
        }

        public void setNetwork_type(String network_type) {
            this.network_type = network_type;
        }

        public String getUtm_source() {
            return utm_source;
        }

        public void setUtm_source(String utm_source) {
            this.utm_source = utm_source;
        }

        public String getUtm_medium() {
            return utm_medium;
        }

        public void setUtm_medium(String utm_medium) {
            this.utm_medium = utm_medium;
        }

        public String getUtm_term() {
            return utm_term;
        }

        public void setUtm_term(String utm_term) {
            this.utm_term = utm_term;
        }

        public String getUtm_content() {
            return utm_content;
        }

        public void setUtm_content(String utm_content) {
            this.utm_content = utm_content;
        }

        public String getUtm_campaign() {
            return utm_campaign;
        }

        public void setUtm_campaign(String utm_campaign) {
            this.utm_campaign = utm_campaign;
        }
    }

}
