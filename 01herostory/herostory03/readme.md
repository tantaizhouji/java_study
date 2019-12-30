## 重构GameMsgHandler

GameMsgHandler类下具有额外广播，用户字典，类中的功能太多，代码行太长，需要分块重构

新建Broadcaster类来管理ChannelGroup的广播功能，管理channel的添加，删除和广播

新建UserManager类并与User类放到model包下，来管理用户字典，管理用户的添加，查找和删除

