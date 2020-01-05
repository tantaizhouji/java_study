# Nginx

## 1.Linux下的安装

使用淘宝的Tengine，http://tengine.taobao.org/	下载文件tengine-2.3.2.tar.gz，将文件移动到linux系统里

```shell
//Tengine依赖组件:gcc openssl-devel pcre-devel zlib-devel
//Linux系统中为安装组件需要安装
yum install gcc openssl-devel pcre-devel zlib-devel -y

//解压文件
tar -zxvf tengine-2.3.2.tar.gz
//进入到解压的文件中
cd tengine-2.3.2
//运行配置文件,将tengine安装在指定目录下/usr/local/tengine
./configure --prefix=/usr/local/tengine
//安装tengine
make && make install

//启动tengine
//移动到安装好的目录下
cd /usr/local/tengine
//进入sbin目录
cd sbin/
//启动nginx,启动之后就可以网页上输入机器的IP地址进行访问
./nginx

//停止nginx，只能通过kill进程的方式
//查看nginx的进程，有两个，一个master，一个worker
ps -ef | grep nginx
//杀死进程，number为nginx的进程数字
kill -9 number
```

## 2.使用脚本启动tengine

### 脚本内容

```shell
#!/bin/sh
#
# nginx - this script starts and stops the nginx daemon
#
# chkconfig:   - 85 15 
# description:  Nginx is an HTTP(S) server, HTTP(S) reverse \
#               proxy and IMAP/POP3 proxy server
# processname: nginx
# config:      /etc/nginx/nginx.conf
# config:      /etc/sysconfig/nginx
# pidfile:     /var/run/nginx.pid
 
# Source function library.
. /etc/rc.d/init.d/functions
 
# Source networking configuration.
. /etc/sysconfig/network
 
# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0
 
nginx="/usr/local/tengine/sbin/nginx"
prog=$(basename $nginx)
 
NGINX_CONF_FILE="/usr/local/tengine/conf/nginx.conf"
 
[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx
 
lockfile=/var/lock/subsys/nginx
 
make_dirs() {
   # make required directories
   user=`nginx -V 2>&1 | grep "configure arguments:" | sed 's/[^*]*--user=\([^ ]*\).*/\1/g' -`
   options=`$nginx -V 2>&1 | grep 'configure arguments:'`
   for opt in $options; do
       if [ `echo $opt | grep '.*-temp-path'` ]; then
           value=`echo $opt | cut -d "=" -f 2`
           if [ ! -d "$value" ]; then
               # echo "creating" $value
               mkdir -p $value && chown -R $user $value
           fi
       fi
   done
}
 
start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
    make_dirs
    echo -n $"Starting $prog: "
    daemon $nginx -c $NGINX_CONF_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}
 
stop() {
    echo -n $"Stopping $prog: "
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}
 
restart() {
    configtest || return $?
    stop
    sleep 1
    start
}
 
reload() {
    configtest || return $?
    echo -n $"Reloading $prog: "
    killproc $nginx -HUP
    RETVAL=$?
    echo
}
 
force_reload() {
    restart
}
 
configtest() {
  $nginx -t -c $NGINX_CONF_FILE
}
 
rh_status() {
    status $prog
}
 
rh_status_q() {
    rh_status >/dev/null 2>&1
}
 
case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart|configtest)
        $1
        ;;
    reload)
        rh_status_q || exit 7
        $1
        ;;
    force-reload)
        force_reload
        ;;
    status)
        rh_status
        ;;
    condrestart|try-restart)
        rh_status_q || exit 0
            ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}"
        exit 2
esac
```

```shell
// 移动目录
cd /etc/init.d
//新建个脚本文件,将脚本内容复制到新建文件中
vi nginx
//给文件权限
chmod 777 ./nginx

//设置好脚本之后就可以以服务方式启动nginx
service nginx start
```

## 3.基本的概念

### sendfile on/off

sendfile on	应用程序给内核发个指令，内核从磁盘里读数据给网关发出去，速度快，大文件发送太快接收端来不及解析

sendfile off    应用程序从磁盘里读数据，在通过内核给网关发送出去，速度慢，用来发送大文件，配合接收端解析

```shell
//默认的location配置
location / {
    root    html;
    index   index.html index.htm;
}
```

### 开机启动nginx

```shell
//查看开机启动项
chkconfig --list
//将nginx添加到开机启动项中
chkconfig --add nginx
//nginx开机启动
chkconfig nginx on
```



## 4.session共享

```shell
1.安装Memcached
yum -y install memcached
//启动memcached
memcached -d -m 128 -u root -l 192.168.74.111 -p 11211 -c 256 -P /tmp/memcached.pid
//查看指令
memcached-tool 192.168.74.111:11211
```

每台服务器里面的tomcat配置文件context.xml中加入

```xml
<Manager className="de.javakaffee.web.msm.MemcachedBackupSessionManager" 
	memcachedNodes="n1:192.168.74.111:11211" 
    sticky="false" 
    lockingMode="auto"
    sessionBackupAsync="false"
	requestUriIgnorePattern=".*\.(ico|png|gif|jpg|css|js)$"
    sessionBackupTimeout="1000" transcoderFactoryClass="de.javakaffee.web.msm.serializer.kryo.KryoTranscoderFactory" />
```

## 5.Linux时间同步

```shell
//为安装ntp需要安装ntp
yum install ntp

//修改ntp的配置文件，按照下方,服务器
vi /etc/ntp.conf

# Hosts on local network are less restricted.
# 允许内网中其他机器同步时间
restrict 192.168.0.0 mask 255.255.255.0 nomodify notrap

# Use public servers from the pool.ntp.org project.
# Please consider joining the pool (http://www.pool.ntp.org/join.html).
# 上层时间服务器，有外网的情况下可以使用 :  
server 210.72.145.44 perfer      # 中国国家受时中心
server 202.112.10.36             # 1.cn.pool.ntp.org
server 59.124.196.83             # 0.asia.pool.ntp.org

#broadcast 192.168.1.255 autokey        # broadcast server
#broadcastclient                        # broadcast client
#broadcast 224.0.1.1 autokey            # multicast server
#multicastclient 224.0.1.1              # multicast client
#manycastserver 239.255.254.254         # manycast server
#manycastclient 239.255.254.254 autokey # manycast client

# allow update time by the upper server 
# 允许上层时间服务器主动修改本机时间
restrict 210.72.145.44 nomodify notrap noquery
restrict 202.112.10.36 nomodify notrap noquery
restrict 59.124.196.83 nomodify notrap noquery

//配置好后
service ntpd start

//一台作为服务器安装上面的配置，集群的其他作为客户端，同样修改ntp.confpeizhiwen
server 192.168.74.111
restrict 192.168.74.111 nomodify notrap noquery
```

## 6.动静分离



```shell
//nginx配置里面设置代理,1.3:8080是主机的地址，74.111:8081是虚拟机跑jar包的地址
upstream tomcats {
	server 192.168.1.3:8080;
	server 192.168.74.111:8081;
}

server {
    location / {
    	proxy_pass http://tomcats/;
    }
    
    //过滤格式是.css/.js等格式的请求，这些请求直接nginx处理，不需要打到tomcat上
    //需要在nginx本地服务骑上指定的目录/var/data/static/下有相应的静态文件，请求直接在这里找
    location ~ .*\.(gif|jpg|jpeg|png|bmp|swf|html|htm|css|js)$ {
    	root /var/data/static/;
    }
}
//注意:要使用root指定根目录，不能使用alias，使用alias会有问题,如下图
```

![alias造成的问题](D:\笔记\images\alias造成的问题.jpg)



























































