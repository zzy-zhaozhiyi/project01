import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @author zzy
 * @create 2019-11-18 19:18
 */
public class ZkTest {
    public static void main(String[] args) throws Exception {
        String connectString = "192.168.191.128:2181";
        int sessionTimeout = 60000;
            ZooKeeper zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("正在监控监控中");
            }
        });
        Stat stat = zk.exists("/jintian", false);
        if (stat != null) {
            System.out.println("即将删除数据");
            zk.delete("/jintian", stat.getVersion());

        } else {
            zk.create("/jintian", "真的想妮了".getBytes("UTF-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            byte[] data = zk.getData("/jintian", false, null);
            System.out.println("得到的数据是" + new String(data));
        }


    }
}
